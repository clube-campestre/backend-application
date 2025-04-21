package com.campestre.clube.backend_application.controller;


import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetPlaceResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SavePlaceResponseDto;
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
@Tag(name = "Place Controller", description = "Place data routes")
public class PlaceController {

    @Autowired
    private PlaceService placeDataService;

    @GetMapping
    @Operation(summary = "Endpoint for list all places")
    public ResponseEntity<List<GetPlaceResponseDto>> getAll() {
        return ResponseEntity.ok(placeDataService.getAll().stream().map(PlaceMapper::toGetResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint for get place by id")
    public ResponseEntity<GetPlaceResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(PlaceMapper.toGetResponse(placeDataService.getById(id)));
    }

    @GetMapping("/best-rated")
    @Operation(summary = "Endpoint to get places ranked by rating")
    public ResponseEntity<List<GetPlaceResponseDto>> getAllOrderedByRating() {
        List<GetPlaceResponseDto> places = placeDataService.getAllOrderedByRating().stream()
                .map(PlaceMapper::toGetResponse).collect(Collectors.toList());
        if (places.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(places);
    }

    @PostMapping
    @Operation(summary = "Endpoint for create place")
    public ResponseEntity<SavePlaceResponseDto> register(@Valid @RequestBody SavePlaceRequestDto placeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(PlaceMapper.toSaveResponse(
                placeDataService.save(PlaceMapper.toEntity(placeDto))
        ));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Endpoint for update place by id")
    public ResponseEntity<SavePlaceResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody SavePlaceRequestDto placeDto
    ) {
        return ResponseEntity.ok(PlaceMapper.toSaveResponse(
                placeDataService.update(id, PlaceMapper.toEntity(placeDto))
        ));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint for remove place by id")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        placeDataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
