package com.example.proyecto.dtos;

import com.example.proyecto.models.AprobacionesModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AprobacionDTO {
    private Long id;
    private String estado;
    private Long reservaId;
    private String reservaEstado;
    private Long coordinadorId;
    private String coordinadorNombre;

    public static AprobacionDTO fromEntity(AprobacionesModel aprobacion) {
        AprobacionDTO dto = new AprobacionDTO();
        dto.setId(aprobacion.getId());
        dto.setEstado(aprobacion.getEstado());

        if (aprobacion.getReserva() != null) {
            dto.setReservaId(aprobacion.getReserva().getId());
            dto.setReservaEstado(aprobacion.getReserva().getEstado());
        }

        if (aprobacion.getUsuario() != null) {
            dto.setCoordinadorId(aprobacion.getUsuario().getId());
            dto.setCoordinadorNombre(aprobacion.getUsuario().getNombre());
        }

        return dto;
    }


}
