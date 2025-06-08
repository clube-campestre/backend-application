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
            throw new ConflictException("Já existe um transporte cadastrado para esta empresa.");

        return transportRepository.save(transport);
    }

    public List<Transport> getAllOrderedByRating() {
        return transportRepository.findAllByOrderByRatingDesc();
    }

    public Transport getById(Integer id) {
        existsByIdOrThrow(id);
        return transportRepository.findById(id).get();
    }

    public Transport update(Integer id, Transport updateTransport){
        existsByIdOrThrow(id);

        if(transportRepository.existsTransportByEnterpriseAndIdNot(updateTransport.getEnterprise(), id))
            throw new ConflictException("Já existe um transporte cadastrado para esta empresa.");

        updateTransport.setId(id);
        return transportRepository.save(updateTransport);
    }

    public void delete(Integer id){
        existsByIdOrThrow(id);
        transportRepository.deleteById(id);
    }



    private void existsByIdOrThrow(Integer id) {
        if (!transportRepository.existsById(id))
            throw new NotFoundException("Não encontramos o transporte solicitado.");
    }
}
