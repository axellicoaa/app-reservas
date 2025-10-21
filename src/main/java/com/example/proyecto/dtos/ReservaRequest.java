package com.example.proyecto.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaRequest {

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long usuarioId;
    private Long salaId;
    private List<Long> equipamientosRequeridos;

    // Getters y setters
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getSalaId() { return salaId; }
    public void setSalaId(Long salaId) { this.salaId = salaId; }

    public List<Long> getEquipamientosRequeridos() { return equipamientosRequeridos; }
    public void setEquipamientosRequeridos(List<Long> equipamientosRequeridos) { this.equipamientosRequeridos = equipamientosRequeridos; }
}
