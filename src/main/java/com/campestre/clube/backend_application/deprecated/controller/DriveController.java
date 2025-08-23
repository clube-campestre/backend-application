package com.campestre.clube.backend_application.deprecated.controller;

import com.campestre.clube.backend_application.deprecated.exceptions.InternalServerException;
import com.campestre.clube.backend_application.deprecated.service.DriveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/drive")
@CrossOrigin("*")
@Tag(name = "Google Drive Controller", description = "Google Drive data routes")
public class DriveController {

    @Autowired
    private DriveService service;
    //TODO atualizar lançamento de exceções

    @PostMapping("/upload")
    @Operation(summary = "Endpoint for upload file to Google Drive")
    public Object handleFileUpload(
            @RequestParam("image") MultipartFile file, @RequestParam("cpf") String cpf
    ) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) throw new BadRequestException("O arquivo está vazio.");
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        return service.uploadImageToDrive(tempFile, cpf);
    }


    // 🔹 LISTAR TODOS OS ARQUIVOS NO DRIVE
    @GetMapping("/list")
    @Operation(summary = "Endpoint for list all files")
    public List<String> listFiles() throws GeneralSecurityException, IOException {
        return service.listFiles();
    }

    // 🔹 OBTER URL DO ARQUIVO POR ID
    @GetMapping
    @Operation(summary = "Endpoint for get file by id")
    public String getFile(@RequestParam String fileId) {
        return service.getFileUrl(fileId);
    }

    // 🔹 ATUALIZAR ARQUIVO EXISTENTE NO DRIVE
    @PutMapping("/update")
    @Operation(summary = "Endpoint for update file by id")
    public String updateFile(
            @RequestParam String fileId, @RequestParam("file") MultipartFile file, @RequestParam String cpf
    ) throws IOException {
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        try {
            return service.updateFile(fileId, tempFile, cpf);
        } catch (IOException | GeneralSecurityException e) {
            throw new InternalServerException(
                    "Não foi possível processar o arquivo no momento. Tente novamente mais tarde."
            );
        }
    }

    // 🔹 DELETAR ARQUIVO POR ID
    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint for remove file by id")
    public String deleteFile(@RequestParam String fileId) throws GeneralSecurityException, IOException {
        return service.deleteFile(fileId);
    }
}
