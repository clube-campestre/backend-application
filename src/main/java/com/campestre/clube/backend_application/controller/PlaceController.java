package com.campestre.clube.backend_application.controller;


import com.campestre.clube.backend_application.controller.dtos.requests.SavePlaceRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetPlaceResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SavePlaceResponseDto;
import com.campestre.clube.backend_application.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
//@Tag(name = "Place Controller", description = "Place data routes")
public class PlaceController {

    @Autowired
    private PlaceService placeDataService;

    @GetMapping
//    @Operation(summary = "Endpoint for get all places")
    public ResponseEntity<List<GetPlaceResponseDto>> getAll() {
        return ResponseEntity.ok(placeDataService.getAll().stream().map(GetPlaceResponseDto::toResponse).toList());
    }

    @GetMapping("/{id}")
//    @Operation(summary = "Endpoint for get place by id")
    public ResponseEntity<GetPlaceResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(GetPlaceResponseDto.toResponse(placeDataService.getById(id)));
    }

    @GetMapping("/best-rated")
//    @Operation(summary = "Endpoint to get places ranked by rating")
    public ResponseEntity<List<GetPlaceResponseDto>> getAllOrderedByRating() {
        List<GetPlaceResponseDto> places = placeDataService.getAllOrderedByRating().stream()
                .map(GetPlaceResponseDto::toResponse).collect(Collectors.toList());
        if (places.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(places);
    }

    @PostMapping
//    @Operation(summary = "Endpoint for post place")
    public ResponseEntity<SavePlaceResponseDto> register(@Valid @RequestBody SavePlaceRequestDto placeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SavePlaceResponseDto.toResponse(
                placeDataService.save(SavePlaceRequestDto.toEntity(placeDto))
        ));
    }

    @PutMapping("/{id}")
//    @Operation(summary = "Endpoint for update place by id")
    public ResponseEntity<SavePlaceResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody SavePlaceRequestDto placeDto
    ) {
        return ResponseEntity.ok(SavePlaceResponseDto.toResponse(
                placeDataService.update(id, SavePlaceRequestDto.toEntity(placeDto))
        ));
    }

    @DeleteMapping("/{id}")
//    @Operation(summary = "Endpoint for remove place by id")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        placeDataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
