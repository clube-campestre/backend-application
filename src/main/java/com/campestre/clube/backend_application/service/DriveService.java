package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.responses.DriveRes;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.MemberDataRepository;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoodleCredentials();

    @Autowired
    private MemberDataRepository memberDataRepository;

    private static String getPathToGoodleCredentials(){
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "credentials.json");
        return filePath.toString();
    }


    public String getOrCreateUserFolder(String cpf) throws GeneralSecurityException, IOException {
        Drive drive = createDriveService();
        String parentFolderId = "1bEu47AuneZ7SjuiiTjRLRQob6cGtWYbc"; // Pasta raiz onde as pastas de usuários serão criadas

        // Verifica se já existe uma pasta com o nome do CPF
        String query = String.format("mimeType='application/vnd.google-apps.folder' and name='%s' and '%s' in parents and trashed=false", cpf, parentFolderId);
        Drive.Files.List request = drive.files().list().setQ(query).setFields("files(id, name)");

        var files = request.execute().getFiles();
        if (!files.isEmpty()) {
            return files.get(0).getId(); // Retorna o ID da pasta existente
        }

        // Se não existir, cria a pasta nova
        com.google.api.services.drive.model.File folderMetaData = new com.google.api.services.drive.model.File();
        folderMetaData.setName(cpf);
        folderMetaData.setMimeType("application/vnd.google-apps.folder");
        folderMetaData.setParents(Collections.singletonList(parentFolderId));

        com.google.api.services.drive.model.File folder = drive.files().create(folderMetaData)
                .setFields("id")
                .execute();

        System.out.println("Pasta criada: " + cpf + " - ID: " + folder.getId());
        return folder.getId();
    }

    public DriveRes uploadImageToDrive(File file, String cpf) throws GeneralSecurityException, IOException {
        DriveRes res = new DriveRes();
        MemberData member = validateMemberExists(cpf);

        try {
            Drive drive = createDriveService();

            // Obtém ou cria a pasta do usuário no Drive
            String folderId = getOrCreateUserFolder(cpf);

            // Configuração do arquivo
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName());
            fileMetaData.setParents(Collections.singletonList(folderId));

            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id")
                    .execute();

            String fileId = uploadedFile.getId();
            String imageUrl = "https://drive.google.com/uc?export=view&id=" + fileId;

            //Salvar link no membro
            member.setCpf(cpf);
            member.setImagePath(imageUrl);
            member.setIdImage(fileId);
            memberDataRepository.save(member);

            System.out.println("Arquivo enviado: " + file.getName() + " - ID: " + fileId);

            file.delete();

            res.setStatus(200);
            res.setMessage("Arquivo enviado com sucesso!");
            res.setUrl(imageUrl);
            res.setId(fileId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.setStatus(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }


    // 🔹 LISTAR TODOS OS ARQUIVOS
    public List<String> listFiles() throws GeneralSecurityException, IOException {
        List<String> fileList = new ArrayList<>();
        Drive drive = createDriveService();

        FileList result = drive.files().list()
                .setFields("files(id, name)")
                .execute();

        for (com.google.api.services.drive.model.File file : result.getFiles()) {
            fileList.add(file.getName() + " - " + file.getId());
        }

        return fileList;
    }

    // 🔹 OBTER URL DO ARQUIVO PELO ID
    public String getFileUrl(String fileId) {
        return "https://drive.google.com/uc?export=view&id=" + fileId;
    }

    // 🔹 ATUALIZAR UM ARQUIVO EXISTENTE
    public String updateFile(String fileId, File newFile) throws GeneralSecurityException, IOException {
        Drive drive = createDriveService();

        com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
        fileMetaData.setName(newFile.getName());

        AbstractInputStreamContent content = new FileContent("image/jpeg", newFile);

        com.google.api.services.drive.model.File updatedFile = drive.files()
                .update(fileId, fileMetaData, content)
                .setFields("id, name")
                .execute();

        return "Arquivo atualizado: " + updatedFile.getName();
    }

    // 🔹 EXCLUIR ARQUIVO DO DRIVE PELO ID
    public String deleteFile(String fileId) throws GeneralSecurityException, IOException {
        Drive drive = createDriveService();

        drive.files().delete(fileId).execute();
        return "Arquivo deletado com sucesso!";
    }


    public Drive createDriveService() throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .build();
    }

    private MemberData validateMemberExists(String cpf) {
        return memberDataRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("MemberData by cpf [%s] not found".formatted(cpf)));
    }
}
