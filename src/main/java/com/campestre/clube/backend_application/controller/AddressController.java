package com.campestre.clube.backend_application.controller;


import com.campestre.clube.backend_application.controller.dtos.requests.SaveAddressRequestDto;
import com.campestre.clube.backend_application.controller.dtos.responses.SaveAddressResponseDto;
import com.campestre.clube.backend_application.entity.Address;
import com.campestre.clube.backend_application.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

@PostMapping("/register")
    public ResponseEntity<SaveAddressResponseDto> register(@Valid @RequestBody SaveAddressRequestDto dto){
    return ResponseEntity.status(201).body(SaveAddressResponseDto.toResponse(addressService.register(SaveAddressRequestDto.toEntity(dto))));
}

@GetMapping
    public ResponseEntity<List<SaveAddressResponseDto>> getAll() {
    List<Address> enderecos = addressService.getAll();
    if (enderecos.isEmpty()){
        return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(200).body(enderecos.stream().map(SaveAddressResponseDto::toResponse).toList());
}

@GetMapping("/{id}")
    public ResponseEntity<SaveAddressResponseDto> getById(@PathVariable Integer id){
    return ResponseEntity.status(200).body(SaveAddressResponseDto.toResponse(addressService.getById(id)));
}

@PutMapping("/{id}")
    public ResponseEntity<SaveAddressResponseDto> update(@PathVariable Integer id, @Valid @RequestBody SaveAddressRequestDto dto){
    return ResponseEntity.status(200).body(SaveAddressResponseDto.toResponse(addressService.update(id, SaveAddressRequestDto.toEntity(dto))));
}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    addressService.delete(id);
    return ResponseEntity.status(204).build();
    }

}


