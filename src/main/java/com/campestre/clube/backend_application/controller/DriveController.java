package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.responses.DriveRes;
import com.campestre.clube.backend_application.exceptions.InternalServerException;
import com.campestre.clube.backend_application.service.DriveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        if (file.isEmpty()) {
            return "O arquivo está vazio";
        }
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        DriveRes res = service.uploadImageToDrive(tempFile, cpf);
        return res;
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
        } catch (IOException e) {
            throw new InternalServerException("IOException: "+e.getMessage());
        } catch (GeneralSecurityException e) {
            throw new InternalServerException("GeneralSecurityException: "+e.getMessage());
        }
    }

    // 🔹 DELETAR ARQUIVO POR ID
    @DeleteMapping("/delete")
    @Operation(summary = "Endpoint for remove file by id")
    public String deleteFile(@RequestParam String fileId) throws GeneralSecurityException, IOException {
        return service.deleteFile(fileId);
    }
}
