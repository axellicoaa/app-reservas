package com.example.proyecto.dtos;

import com.example.proyecto.models.AreaModel;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AreaDTO {
    private Long id;
    private String nombreArea;

    public AreaDTO(Long id, String nombreArea) {
        this.id = id;
        this.nombreArea = nombreArea;
    }

    public static AreaDTO fromEntity(AreaModel area) {
        return new AreaDTO(area.getId(), area.getNombreArea());
    }

    public Long getId() { return id; }
    public String getNombreArea() { return nombreArea; }
}
