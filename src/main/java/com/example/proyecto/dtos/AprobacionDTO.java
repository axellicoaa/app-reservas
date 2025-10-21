package com.example.proyecto.dtos;

import com.example.proyecto.models.AprobacionesModel;
import com.example.proyecto.models.ReservaModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AprobacionDTO {
    private Long id;
    private String estado;

    // Info de la reserva
    private Long reservaId;
    private String reservaEstado;
    private String salaNombre;
    private String usuarioSolicitante;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    // Info del coordinador
    private Long coordinadorId;
    private String coordinadorNombre;

    public static AprobacionDTO fromEntity(AprobacionesModel aprobacion) {
        AprobacionDTO dto = new AprobacionDTO();
        dto.setId(aprobacion.getId());
        dto.setEstado(aprobacion.getEstado());

        ReservaModel reserva = aprobacion.getReserva();
        if (reserva != null) {
            dto.setReservaId(reserva.getId());
            dto.setReservaEstado(reserva.getEstado());
            dto.setSalaNombre(reserva.getSala() != null ? reserva.getSala().getNombreSala() : null);
            dto.setUsuarioSolicitante(reserva.getUsuario() != null ? reserva.getUsuario().getNombre() : null);
            dto.setFecha(reserva.getFecha() != null ? reserva.getFecha().toString() : null);
            dto.setHoraInicio(reserva.getHoraInicio() != null ? reserva.getHoraInicio().toString() : null);
            dto.setHoraFin(reserva.getHoraFin() != null ? reserva.getHoraFin().toString() : null);
        }

        if (aprobacion.getUsuario() != null) {
            dto.setCoordinadorId(aprobacion.getUsuario().getId());
            dto.setCoordinadorNombre(aprobacion.getUsuario().getNombre());
        }

        return dto;
    }
}
