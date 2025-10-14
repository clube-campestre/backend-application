package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.transport.*;
import com.campestre.clube.backend_application.core.application.transport.command.DeleteTransportCommand;
import com.campestre.clube.backend_application.core.application.transport.command.GetTransportByIdCommand;
import com.campestre.clube.backend_application.core.application.transport.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.application.transport.command.UpdateTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.infrastructure.web.dtos.transport.TransportRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.transport.TransportResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.TransportDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transports")
@CrossOrigin("*")
@Tag(name = "Transport Controller", description = "Transport data routes")
public class TransportController {
    private final SaveTransportUseCase saveTransportUseCase;
    private final UpdateTransportUseCase updateTransportUseCase;
    private final DeleteTransportUseCase deleteTransportUseCase;
    private final GetTransportByIdUseCase getTransportByIdUseCase;
    private final ListTransportOrderedByRatingUseCase listTransportOrderedByRatingUseCase;

    public TransportController(
            SaveTransportUseCase saveTransportUseCase,
            UpdateTransportUseCase updateTransportUseCase,
            DeleteTransportUseCase deleteTransportUseCase,
            GetTransportByIdUseCase getTransportByIdUseCase,
            ListTransportOrderedByRatingUseCase listTransportOrderedByRatingUseCase
    ) {
        this.saveTransportUseCase = saveTransportUseCase;
        this.updateTransportUseCase = updateTransportUseCase;
        this.deleteTransportUseCase = deleteTransportUseCase;
        this.getTransportByIdUseCase = getTransportByIdUseCase;
        this.listTransportOrderedByRatingUseCase = listTransportOrderedByRatingUseCase;
    }

    @Operation(summary = "Endpoint for create transport")
    @PostMapping
    public ResponseEntity<TransportResponseDto> register(@Valid @RequestBody TransportRequestDto requestDto) {
        SaveTransportCommand command = TransportDtoMapper.toCommand(requestDto);
        Transport transport = saveTransportUseCase.execute(command);
        TransportResponseDto responseDto = TransportDtoMapper.toResponse(transport);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Endpoint to get transports ranked by rating")
    @GetMapping
    public ResponseEntity<List<TransportResponseDto>> getAllOrderedByRating() {
        List<Transport> transports = listTransportOrderedByRatingUseCase.execute();
        if (transports.isEmpty()) return ResponseEntity.noContent().build();
        List<TransportResponseDto> responseDtos = TransportDtoMapper.toResponse(transports);

        return ResponseEntity.ok(responseDtos);
    }

    @Operation(summary = "Endpoint for get transport by id")
    @GetMapping("/{id}")
    public ResponseEntity<TransportResponseDto> getById(@PathVariable Long id) {
        Transport transport = getTransportByIdUseCase.execute(new GetTransportByIdCommand(id));
        TransportResponseDto responseDto = TransportDtoMapper.toResponse(transport);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "Endpoint for update transport by id")
    @PutMapping("/{id}")
    public ResponseEntity<TransportResponseDto> update(
            @PathVariable Long id, @Valid @RequestBody TransportRequestDto requestDto
    ) {
        UpdateTransportCommand command = TransportDtoMapper.toCommand(requestDto, id);
        Transport transport = updateTransportUseCase.execute(command);
        TransportResponseDto responseDto = TransportDtoMapper.toResponse(transport);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "Endpoint for remove transport by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteTransportUseCase.execute(new DeleteTransportCommand(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
