package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceDataService {

    @Autowired
    private PlaceRepository placeRepository;

}
