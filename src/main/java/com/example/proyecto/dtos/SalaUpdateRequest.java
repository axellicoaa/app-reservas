// SalaUpdateRequest.java
package com.example.proyecto.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SalaUpdateRequest {
    private String nombreSala;
    private int capacidadMaxima;
    private List<Long> equipamientosIds;

    // getters & setters
}
