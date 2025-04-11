package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.TagRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TagResponseDto;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.mapper.TagMapper;
import com.campestre.clube.backend_application.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    public TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponseDto> register(@RequestBody TagRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(TagResponseDto.toResponse(
                tagService.register(TagMapper.toEntity(dto))
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(TagResponseDto.toResponse(tagService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAll() {
        List<Tag> tags = tagService.getAll();
        if(tags.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(tags.stream().map(TagResponseDto::toResponse)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDto> update(@PathVariable Integer id, @Valid @RequestBody TagRequestDto tag){
        return ResponseEntity.status(HttpStatus.OK).body(TagResponseDto.toResponse(
                tagService.update(TagMapper.toEntity(tag), id)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
