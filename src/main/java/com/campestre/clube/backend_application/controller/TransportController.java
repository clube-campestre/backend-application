package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.controller.dtos.requests.SaveTransportRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.GetTransportResponseDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveTransportResponseDto;
import com.campestre.clube.backend_application.entity.Transport;
import com.campestre.clube.backend_application.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transports")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @PostMapping
    public ResponseEntity<SaveTransportResponseDto> register(@RequestBody SaveTransportRequestDto transportReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(SaveTransportResponseDto.toResponse(
                transportService.register(SaveTransportRequestDto.toEntity(transportReceive))
        ));
    }

    @GetMapping
    public ResponseEntity<List<GetTransportResponseDto>> getAll() {
        List<Transport> transports = transportService.getAll();
        if (transports.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(transports.stream().map(GetTransportResponseDto::toResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTransportResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                GetTransportResponseDto.toResponse(transportService.getById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveTransportResponseDto> update(
            @PathVariable Integer id, @RequestBody SaveTransportRequestDto updateTransport
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(SaveTransportResponseDto.toResponse(
                transportService.update(id, SaveTransportRequestDto.toEntity(updateTransport))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        transportService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}