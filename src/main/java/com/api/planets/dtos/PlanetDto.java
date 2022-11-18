package com.api.planets.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PlanetDto {
    
    @NotBlank
    private String name;

    @NotBlank
    private String climate;

    @NotBlank
    private String terrain;
}
