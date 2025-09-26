package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.tag.*;
import com.campestre.clube.backend_application.core.application.tag.command.DeleteTagCommand;
import com.campestre.clube.backend_application.core.application.tag.command.GetTagByIdCommand;
import com.campestre.clube.backend_application.core.domain.Tag;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.tag.TagResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.TagDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
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
    private final SaveTagUseCase saveTagUseCase;
    private final UpdateTagUseCase updateTagUseCase;
    private final DeleteTagUseCase deleteTagUseCase;
    private final GetTagByIdUseCase getTagByIdUseCase;
    private final ListTagUseCase listTagUseCase;

    public TagController(
            SaveTagUseCase saveTagUseCase,
            UpdateTagUseCase updateTagUseCase,
            DeleteTagUseCase deleteTagUseCase,
            GetTagByIdUseCase getTagByIdUseCase,
            ListTagUseCase listTagUseCase
    ) {
        this.saveTagUseCase = saveTagUseCase;
        this.updateTagUseCase = updateTagUseCase;
        this.deleteTagUseCase = deleteTagUseCase;
        this.getTagByIdUseCase = getTagByIdUseCase;
        this.listTagUseCase = listTagUseCase;
    }

    @Operation(summary = "Endpoint for create a new tag")
    @PostMapping
    public ResponseEntity<TagResponseDto> register(@RequestBody TagRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(TagDtoMapper.toResponse(
                saveTagUseCase.execute(TagDtoMapper.toCommand(dto))
        ));
    }

    @Operation(summary = "Endpoint for get tag by id")
    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(TagDtoMapper.toResponse(
                getTagByIdUseCase.execute(new GetTagByIdCommand(id))
        ));
    }

    @Operation(summary = "Endpoint for list all tags")
    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAll() {
        List<Tag> tags = listTagUseCase.execute();
        if(tags.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(TagDtoMapper.toResponse(tags));
    }

    @Operation(summary = "Endpoint for update tag by id")
    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDto> update(@PathVariable Integer id, @Valid @RequestBody TagRequestDto tag){
        return ResponseEntity.status(HttpStatus.OK).body(TagDtoMapper.toResponse(
                updateTagUseCase.execute(TagDtoMapper.toCommand(tag, id))
        ));
    }

    @Operation(summary = "Endpoint for remove tag by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteTagUseCase.execute(new DeleteTagCommand(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
