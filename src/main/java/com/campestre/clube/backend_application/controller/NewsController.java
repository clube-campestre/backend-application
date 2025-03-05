package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.model.News;
import com.campestre.clube.backend_application.service.GoogleDriveService;
import com.campestre.clube.backend_application.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private GoogleDriveService googleDriveService;

    // Endpoint para criar uma notícia com upload de imagem e anexos
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> criar(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam MultipartFile attachment
    ) {
        try {
            // Converter MultipartFile para File
            File imageFile = convertToFile(image);
            File attachmentFile = convertToFile(attachment);

            // Upload para Google Drive (pode informar folderId se quiser)
            String imageLink = googleDriveService.upload(imageFile, image.getContentType(), null);
            String attachmentLink = googleDriveService.upload(attachmentFile, attachment.getContentType(), null);

            // Montar a entidade News com os links para o Google Drive
            News news = new News();
            news.setTitle(title);
            news.setDescription(description);
            news.setImage(imageLink);
            news.setAttachments(attachmentLink);

            // Salvar no banco
            newsService.save(news);

            return ResponseEntity.status(HttpStatus.CREATED).body(news);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar notícia");
        }
    }

    // Método para converter MultipartFile em File
    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

    @GetMapping()
    public ResponseEntity<List<News>> listar() {

        return newsService.listAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> atualizar(@RequestBody News news, @PathVariable int id) {
        return newsService.update(news, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<News> deletar(@PathVariable int id) {
        return newsService.delete(id);
    }
}
