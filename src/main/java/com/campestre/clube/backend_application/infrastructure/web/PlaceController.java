package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.place.*;
import com.campestre.clube.backend_application.core.application.place.command.DeletePlaceCommand;
import com.campestre.clube.backend_application.core.application.place.command.GetPlaceByIdCommand;
import com.campestre.clube.backend_application.core.application.place.command.SavePlaceCommand;
import com.campestre.clube.backend_application.core.application.place.command.UpdatePlaceCommand;
import com.campestre.clube.backend_application.core.domain.Place;
import com.campestre.clube.backend_application.infrastructure.web.dtos.place.SavePlaceRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.place.UpdatePlaceRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.place.PlaceResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.PlaceDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@CrossOrigin("*")
@Tag(name = "Place Controller", description = "Place data routes")
public class PlaceController {
    private final SavePlaceUseCase savePlaceUseCase;
    private final UpdatePlaceUseCase updatePlaceUseCase;
    private final DeletePlaceUseCase deletePlaceUseCase;
    private final GetPlaceByIdUseCase getPlaceByIdUseCase;
    private final ListPlaceOrderedByRatingUseCase listPlaceOrderedByRatingUseCase;

    public PlaceController(
            SavePlaceUseCase savePlaceUseCase,
            UpdatePlaceUseCase updatePlaceUseCase,
            DeletePlaceUseCase deletePlaceUseCase,
            GetPlaceByIdUseCase getPlaceByIdUseCase,
            ListPlaceOrderedByRatingUseCase listPlaceOrderedByRatingUseCase
    ) {
        this.savePlaceUseCase = savePlaceUseCase;
        this.updatePlaceUseCase = updatePlaceUseCase;
        this.deletePlaceUseCase = deletePlaceUseCase;
        this.getPlaceByIdUseCase = getPlaceByIdUseCase;
        this.listPlaceOrderedByRatingUseCase = listPlaceOrderedByRatingUseCase;
    }

    @Operation(summary = "Endpoint for create place")
    @PostMapping
    public ResponseEntity<PlaceResponseDto> register(@Valid @RequestBody SavePlaceRequestDto requestDto) {
        SavePlaceCommand command = PlaceDtoMapper.toCommand(requestDto);
        Place place = savePlaceUseCase.execute(command);
        PlaceResponseDto responseDto = PlaceDtoMapper.toResponse(place);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Endpoint to get places ranked by rating")
    @GetMapping
    public ResponseEntity<List<PlaceResponseDto>> getAllOrderedByRating() {
        List<Place> places = listPlaceOrderedByRatingUseCase.execute();
        if (places.isEmpty()) return ResponseEntity.noContent().build();
        List<PlaceResponseDto> responseDtos = PlaceDtoMapper.toResponse(places);

        return ResponseEntity.ok(responseDtos);
    }

    @Operation(summary = "Endpoint for get place by id")
    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDto> getById(@PathVariable Integer id) {
        Place place = getPlaceByIdUseCase.execute(new GetPlaceByIdCommand(id));
        PlaceResponseDto responseDto = PlaceDtoMapper.toResponse(place);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "Endpoint for update place by id")
    @PutMapping("/{id}")
    public ResponseEntity<PlaceResponseDto> update(
            @PathVariable Integer id, @Valid @RequestBody UpdatePlaceRequestDto requestDto
    ) {
        UpdatePlaceCommand command = PlaceDtoMapper.toCommand(requestDto, id);
        Place place = updatePlaceUseCase.execute(command);
        PlaceResponseDto responseDto = PlaceDtoMapper.toResponse(place);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "Endpoint for remove place by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deletePlaceUseCase.execute(new DeletePlaceCommand(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
