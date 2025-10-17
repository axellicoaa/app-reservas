// ReservaDTO.java (SALIDA - GET/POST respuesta)
package com.example.proyecto.dtos;

import com.example.proyecto.models.ReservaModel;
import java.util.List;
import java.util.stream.Collectors;

public record ReservaDTO(
        Long id,
        String fecha,
        String horaInicio,
        String horaFin,
        String estado,
        String usuarioNombre,
        String salaNombre,
        List<String> equipamientos
) {
    public static ReservaDTO fromEntity(ReservaModel r) {
        return new ReservaDTO(
                r.getId(),
                r.getFecha().toString(),
                r.getHoraInicio().toString(),
                r.getHoraFin().toString(),
                r.getEstado(),
                r.getUsuario() != null ? r.getUsuario().getNombre() : null,
                r.getSala() != null ? r.getSala().getNombreSala() : null,
                (r.getSala() != null && r.getSala().getEquipamientos() != null)
                        ? r.getSala().getEquipamientos().stream()
                            .map(eq -> eq.getTipoEquipo())
                            .collect(Collectors.toList())
                        : List.of()
        );
    }
}
