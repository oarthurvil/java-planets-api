package com.api.planets.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        if (planetService.existsByName(planetDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Planet Name is already in use!");
        }

        Planet planet = new Planet();
        BeanUtils.copyProperties(planetDto, planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.save(planet));
    }

    @GetMapping
    public ResponseEntity<Page<Planet>> getAllPlanets(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable ) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePlanet(@PathVariable(value = "id") UUID id) {
        Optional<Planet> planetOptional = planetService.findById(id);

        if (!planetOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planet not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(planetOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlanet(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid PlanetDto planetDto) {
        Optional<Planet> planetOptional = planetService.findById(id);

        if (!planetOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planet not found.");
        }

        Planet planet = new Planet();
        BeanUtils.copyProperties(planetDto, planet);
        planet.setId(planetOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(planetService.save(planet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlanet(@PathVariable(value = "id") UUID id) {
        Optional<Planet> planetOptional = planetService.findById(id);

        if (!planetOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planet not found.");
        }

        planetService.delete(planetOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Planet deleted successfully.");
    }
}
