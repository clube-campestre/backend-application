package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTransportRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.TransportResponseDto;
import com.campestre.clube.backend_application.controller.mapper.TransportMapper;
import com.campestre.clube.backend_application.entity.Transport;
import com.campestre.clube.backend_application.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transports")
@CrossOrigin("*")
@Tag(name = "Transport Controller", description = "Transport data routes")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @Operation(summary = "Endpoint for create transport")
    @PostMapping
    public ResponseEntity<TransportResponseDto> register(@RequestBody SaveTransportRequestDto transportReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TransportMapper.toResponse(
                transportService.register(TransportMapper.toEntity(transportReceive))
        ));
    }

    @Operation(summary = "Endpoint for list all transports")
    @GetMapping
    public ResponseEntity<List<TransportResponseDto>> getAll() {
        List<Transport> transports = transportService.getAll();
        if (transports.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(transports.stream().map(TransportMapper::toResponse)
                .toList());
    }

    @Operation(summary = "Endpoint for get transport by id")
    @GetMapping("/{id}")
    public ResponseEntity<TransportResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TransportMapper.toResponse(transportService.getById(id))
        );
    }

    @Operation(summary = "Endpoint for update transport by id")
    @PutMapping("/{id}")
    public ResponseEntity<TransportResponseDto> update(
            @PathVariable Integer id, @RequestBody SaveTransportRequestDto updateTransport
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(TransportMapper.toResponse(
                transportService.update(id, TransportMapper.toEntity(updateTransport))
        ));
    }

    @Operation(summary = "Endpoint for remove transport by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        transportService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}