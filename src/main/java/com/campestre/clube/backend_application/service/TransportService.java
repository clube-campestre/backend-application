package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.entity.Transport;
import com.campestre.clube.backend_application.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {
    @Autowired
    private TransportRepository transportRepository;

    public Transport register(Transport transport) {
        if (transportRepository.existsTransportByEnterpriseContainsIgnoreCase(transport.getEnterprise()))
            throw new ConflictException("Enterprise already registered");

        return transportRepository.save(transport);
    }

    public List<Transport> getAllOrderedByRating() {
        return transportRepository.findAllByOrderByRatingDesc();
    }

    public Transport getById(Integer id) {
        Optional<Transport> transport = transportRepository.findById(id);
        transportNotFoundValidation(transport, id);

        return transport.get();
    }

    public Transport update(Integer id, Transport updateTransport){
        Optional<Transport> transport = transportRepository.findById(id);

        transportNotFoundValidation(transport, id);
        if(transportRepository.existsTransportByEnterpriseAndIdNot(updateTransport.getEnterprise(), id))
            throw new ConflictException("Enterprise already registered");

        updateTransport.setId(id);
        return transportRepository.save(updateTransport);
    }

    public void delete(Integer id){
        if(!transportRepository.existsById(id))
            throw new NotFoundException("Transport not found");

        transportRepository.deleteById(id);
    }



    private void transportNotFoundValidation(Optional<Transport> transport, Integer id) {
        if (transport.isEmpty())
            throw new NotFoundException("Transport by id [%s] not found".formatted(id));
    }
}
