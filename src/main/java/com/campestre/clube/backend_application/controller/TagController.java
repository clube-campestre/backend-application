package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTagRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TagResponseDto;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.controller.mapper.TagMapper;
import com.campestre.clube.backend_application.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin("*")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag Controller", description = "Tag data routes")
public class TagController {

    @Autowired
    public TagService tagService;

    @Operation(summary = "Endpoint for create a new tag")
    @PostMapping
    public ResponseEntity<TagResponseDto> register(@RequestBody SaveTagRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(TagMapper.toResponse(
                tagService.register(TagMapper.toEntity(dto))
        ));
    }

    @Operation(summary = "Endpoint for get tag by id")
    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(TagMapper.toResponse(tagService.getById(id)));
    }

    @Operation(summary = "Endpoint for list all tags")
    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAll() {
        List<Tag> tags = tagService.getAll();
        if(tags.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(TagMapper.toResponse(tags));
    }

    @Operation(summary = "Endpoint for update tag by id")
    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDto> update(@PathVariable Integer id, @Valid @RequestBody SaveTagRequestDto tag){
        return ResponseEntity.status(HttpStatus.OK).body(TagMapper.toResponse(
                tagService.update(TagMapper.toEntity(tag), id)
        ));
    }

    @Operation(summary = "Endpoint for remove tag by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tagService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
