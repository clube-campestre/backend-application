package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.model.News;
import com.campestre.clube.backend_application.service.AccountService;
import com.campestre.clube.backend_application.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;


    @PostMapping()
    public ResponseEntity<News> criar(@RequestBody News news) {
        return newsService.save(news) != null ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
