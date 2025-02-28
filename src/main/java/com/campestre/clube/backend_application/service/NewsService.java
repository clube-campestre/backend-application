package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.model.News;
import com.campestre.clube.backend_application.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News save(News news) {
        return newsRepository.save(news);
    }

    public ResponseEntity<List<News>> listAll() {
        List newsList = newsRepository.findAll();
        if (newsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    public ResponseEntity<News> update(News news, int id) {
        if(newsRepository.existsById(id)){
            news.setId(id);
            News updated = newsRepository.save(news);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<News> delete(int id) {
        if(newsRepository.existsById(id)){
            newsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
