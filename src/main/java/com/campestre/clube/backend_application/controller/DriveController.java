package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.responses.DriveRes;
import com.campestre.clube.backend_application.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class DriveController {
    @Autowired
    private DriveService service;

    @PostMapping("/uploadToGoogleDrive")
    public Object handleFileUpload(@RequestParam("image") MultipartFile file, @RequestParam("cpf") String cpf) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) {
            return "O arquivo está vazio";
        }
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        DriveRes res = service.uploadImageToDrive(tempFile, cpf);
        return res;
    }


    // 🔹 LISTAR TODOS OS ARQUIVOS NO DRIVE
    @GetMapping("/listFiles")
    public List<String> listFiles() throws GeneralSecurityException, IOException {
        return service.listFiles();
    }

    // 🔹 OBTER URL DO ARQUIVO POR ID
    @GetMapping("/getFile")
    public String getFile(@RequestParam String fileId) {
        return service.getFileUrl(fileId);
    }

    // 🔹 ATUALIZAR ARQUIVO EXISTENTE NO DRIVE
    @PutMapping("/updateFile")
    public String updateFile(@RequestParam String fileId, @RequestParam("file") MultipartFile file, @RequestParam String cpf) throws IOException, GeneralSecurityException {
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        return service.updateFile(fileId, tempFile, cpf);
    }

    // 🔹 DELETAR ARQUIVO POR ID
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam String fileId) throws GeneralSecurityException, IOException {
        return service.deleteFile(fileId);
    }
}
