package com.campestre.clube.backend_application.controller;

import com.campestre.clube.backend_application.model.Transport;
import com.campestre.clube.backend_application.repository.TransportRepository;
import com.campestre.clube.backend_application.service.TransportService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.coyote.BadRequestException;
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
    public ResponseEntity<Transport> register(@RequestBody Transport transportReceive) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportService.register(transportReceive));
    }

    @GetMapping
    public ResponseEntity<List<Transport>> getAll() {
        List<Transport> transports = transportService.getAll();
        if(transports.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(transports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transport> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(transportService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transport> update(@PathVariable Integer id, @RequestBody Transport updateTransport){
        return ResponseEntity.status(HttpStatus.OK).body(transportService.update(id, updateTransport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        transportService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}