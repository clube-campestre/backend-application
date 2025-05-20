package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.requests.UpdatePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.PlaceResponseDto;
import com.campestre.clube.backend_application.controller.mapper.PlaceMapper;
import com.campestre.clube.backend_application.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
@CrossOrigin("*")
@Tag(name = "Place Controller", description = "Place data routes")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    @Operation(summary = "Endpoint to get places ranked by rating")
    public ResponseEntity<List<PlaceResponseDto>> getAllOrderedByRating() {
        List<PlaceResponseDto> places = placeService.getAllOrderedByRating().stream()
                .map(PlaceMapper::toResponse).collect(Collectors.toList());
        if (places.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint for get place by id")
    public ResponseEntity<PlaceResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(PlaceMapper.toResponse(placeService.getById(id)));
    }

    @PostMapping
    @Operation(summary = "Endpoint for create place")
    public ResponseEntity<PlaceResponseDto> create(@Valid @RequestBody SavePlaceRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PlaceMapper.toResponse(placeService.save(PlaceMapper.toEntity(dto)))
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Endpoint for update place by id")
    public ResponseEntity<PlaceResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody UpdatePlaceRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                PlaceMapper.toResponse(placeService.update(id, PlaceMapper.toEntity(dto)))
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint for remove place by id")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        placeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
