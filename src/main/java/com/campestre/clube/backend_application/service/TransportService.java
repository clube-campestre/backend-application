package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.exceptions.EnterpriseConfictException;
import com.campestre.clube.backend_application.exceptions.TransportNotFoundException;
import com.campestre.clube.backend_application.model.Transport;
import com.campestre.clube.backend_application.repository.AccountRepository;
import com.campestre.clube.backend_application.repository.TransportRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Transport register(Transport transport) throws BadRequestException {
        if (transportRepository.existsTransportByEnterpriseContainsIgnoreCase(transport.getEnterprise()))
            throw new EnterpriseConfictException();

        return transportRepository.save(transport);
    }

    public List<Transport> getAll() {
        return transportRepository.findAll();
    }

    public Transport getById(Integer id) {
        Optional<Transport> transport = transportRepository.findById(id);
        transportNotFoundValidation(transport, id);

        return transport.get();
    }

    public Transport update(Integer id, Transport updateTransport){
        Optional<Transport> transport = transportRepository.findById(id);

        transportNotFoundValidation(transport, id);
        if(transportRepository.existsTransportByEnterpriseAndIdNot(id))
            throw new EnterpriseConfictException();

        updateTransport.setId(id);
        return transportRepository.save(updateTransport);
    }

    public void delete(Integer id){
        if(!transportRepository.existsById(id))
            throw new TransportNotFoundException();

        transportRepository.deleteById(id);
    }




    private void transportNotFoundValidation(Optional<Transport> transport, Integer id) {
        if (transport.isEmpty())
            throw new TransportNotFoundException(id);
    }
}
