// SalaDTO.java (SALIDA con IDs de equipos para que sepas qué mandar en el POST)
package com.example.proyecto.dtos;

import com.example.proyecto.models.SalaModel;
import java.util.List;

public record SalaDTO(
        Long id,
        String nombreSala,
        int capacidadMaxima,
        List<EquipamientoDTO> equipamientos
) {
    public static SalaDTO fromEntity(SalaModel s) {
        return new SalaDTO(
                s.getId(),
                s.getNombreSala(),
                s.getCapacidadMaxima(),
                s.getEquipamientos() == null ? List.of()
                        : s.getEquipamientos().stream()
                          .map(e -> new EquipamientoDTO(e.getId(), e.getTipoEquipo()))
                          .toList()
        );
    }

    public record EquipamientoDTO(Long id, String tipoEquipo) {}
}
