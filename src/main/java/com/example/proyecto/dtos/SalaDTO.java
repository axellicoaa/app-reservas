// SalaDTO.java
package com.example.proyecto.dtos;

import java.util.List;

import com.example.proyecto.models.SalaModel;

public record SalaDTO(
        Long id,
        String nombreSala,
        int capacidadMaxima,
        List<EquipamientoDTO> equipamientos,
        Boolean disponible // 👈 agregado (puede ser null si no aplicamos disponibilidad)
) {
    // Versión existente (no rompe tu código actual)
    public static SalaDTO fromEntity(SalaModel s) {
        return new SalaDTO(
                s.getId(),
                s.getNombreSala(),
                s.getCapacidadMaxima(),
                s.getEquipamientos() == null ? List.of()
                        : s.getEquipamientos().stream()
                        .map(e -> new EquipamientoDTO(e.getId(), e.getTipoEquipo()))
                        .toList(),
                null // 👈 cuando no queremos disponibilidad
        );
    }

    // Nueva versión para cuando calculemos disponibilidad
    public static SalaDTO fromEntity(SalaModel s, boolean disponible) {
        return new SalaDTO(
                s.getId(),
                s.getNombreSala(),
                s.getCapacidadMaxima(),
                s.getEquipamientos() == null ? List.of()
                        : s.getEquipamientos().stream()
                        .map(e -> new EquipamientoDTO(e.getId(), e.getTipoEquipo()))
                        .toList(),
                disponible
        );
    }

    public record EquipamientoDTO(Long id, String tipoEquipo) {}
}
