package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.transport.SaveTransportUseCase;
import com.campestre.clube.backend_application.core.application.transport.command.SaveTransportCommand;
import com.campestre.clube.backend_application.core.domain.Transport;
import com.campestre.clube.backend_application.infrastructure.web.dtos.request.SaveTransportRequestDto;
import com.campestre.clube.backend_application.infrastructure.web.dtos.response.TransportResponseDto;
import com.campestre.clube.backend_application.infrastructure.web.mappers.TransportDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transports")
@CrossOrigin("*")
@Tag(name = "Transport Controller", description = "Transport data routes")
public class TransportController {
    private final SaveTransportUseCase saveTransportUseCase;

    public TransportController(SaveTransportUseCase saveTransportUseCase) {
        this.saveTransportUseCase = saveTransportUseCase;
    }

    @Operation(summary = "Endpoint for create transport")
    @PostMapping
    public ResponseEntity<TransportResponseDto> register(@Valid @RequestBody SaveTransportRequestDto requestDto) {

        SaveTransportCommand command = TransportDtoMapper.toCommand(requestDto);
        Transport transport = saveTransportUseCase.execute(command);
        TransportResponseDto responseDto = TransportDtoMapper.toResponse(transport);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
