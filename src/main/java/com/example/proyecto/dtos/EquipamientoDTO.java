package com.example.proyecto.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.proyecto.models.EquipamientoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamientoDTO {
    private Long id;
    private String tipoEquipo;
    private List<String> salas; 


    public EquipamientoDTO(Long id, String tipoEquipo, List<String> salas) {
        this.id = id;
        this.tipoEquipo = tipoEquipo;
        this.salas = salas;
    }

    public static EquipamientoDTO fromEntity(EquipamientoModel e) {
        return new EquipamientoDTO(
                e.getId(),
                e.getTipoEquipo(),
                e.getSalas() == null ? List.of() :
                        e.getSalas().stream()
                                .map(s -> s.getNombreSala())
                                .collect(Collectors.toList())
        );
    }
}
