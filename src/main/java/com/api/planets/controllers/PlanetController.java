package com.api.planets.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.planets.dtos.PlanetDto;
import com.api.planets.models.Planet;
import com.api.planets.services.PlanetService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/planets")
public class PlanetController {
    
    @Autowired
    PlanetService planetService;

    @PostMapping
    public ResponseEntity<Object> savePlanet(@RequestBody @Valid PlanetDto planetDto) {
        Planet planet = new Planet();
        BeanUtils.copyProperties(planetDto, planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.save(planet));
    }
}
