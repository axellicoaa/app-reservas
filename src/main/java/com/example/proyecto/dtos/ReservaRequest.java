// ReservaRequest.java  (ENTRADA - POST)
package com.example.proyecto.dtos;

import com.example.proyecto.models.ReservaModel;
import java.util.List;

public class ReservaRequest {
    private ReservaModel reserva;
    private List<Long> equipamientosRequeridos;

    public ReservaModel getReserva() { return reserva; }
    public void setReserva(ReservaModel reserva) { this.reserva = reserva; }

    public List<Long> getEquipamientosRequeridos() { return equipamientosRequeridos; }
    public void setEquipamientosRequeridos(List<Long> equipamientosRequeridos) { this.equipamientosRequeridos = equipamientosRequeridos; }
}
