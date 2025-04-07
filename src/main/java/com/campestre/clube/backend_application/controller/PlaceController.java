package com.campestre.clube.backend_application.controller;


import com.campestre.clube.backend_application.service.PlaceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {


    @Autowired
    private PlaceDataService placeDataService;


    @GetMapping
    public ResponseEntity<List<GetPlaceResponseDto>>

//      @GetMapping
//        public ResponseEntity<List<GetPlaceResponseDto>> getAll() {
//            return ResponseEntity.ok(
//                    placeDataService.getAll().stream()
//                            .map(GetPlaceResponseDto::toResponse)
//                            .collect(Collectors.toList())
//            );
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<GetPlaceResponseDto> getById(@PathVariable Long id) {
//            return ResponseEntity.ok(
//                    GetPlaceResponseDto.toResponse(placeDataService.getById(id))
//            );
//        }
//
//        @PostMapping("/register")
//        public ResponseEntity<SavePlaceResponseDto> register(@RequestBody SavePlaceRequestDto placeDto) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(
//                    SavePlaceResponseDto.toResponse(placeDataService.register(SavePlaceRequestDto.toEntity(placeDto)))
//            );
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<SavePlaceResponseDto> update(@PathVariable Long id, @RequestBody SavePlaceRequestDto placeDto) {
//            return ResponseEntity.ok(
//                    SavePlaceResponseDto.toResponse(placeDataService.update(id, SavePlaceRequestDto.toEntity(placeDto)))
//            );
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> delete(@PathVariable Long id) {
//            placeDataService.delete(id);
//            return ResponseEntity.noContent().build();
//        }
//
//        @GetMapping("/best-rated")
//        public ResponseEntity<List<GetPlaceResponseDto>> getAllOrderedByRating() {
//            return ResponseEntity.ok(
//                    placeDataService.getAllOrderedByRating().stream()
//                            .map(GetPlaceResponseDto::toResponse)
//                            .collect(Collectors.toList())
//            );
//        }
//    }
}
