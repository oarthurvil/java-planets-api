package com.api.planets.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.planets.models.Planet;
import com.api.planets.repositories.PlanetRepository;

@Service
public class PlanetService {
    
    @Autowired
    PlanetRepository planetRepository;

    @Transactional
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }
}
