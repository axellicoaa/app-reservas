package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.AprobacionDTO;
import com.example.proyecto.models.AprobacionesModel;
import com.example.proyecto.services.AprobacionService;

@RestController
@RequestMapping("/aprobaciones")
public class AprobacionController {
    private final AprobacionService service;

    public AprobacionController(AprobacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<AprobacionDTO> listar() {
        return service.getAll();
    }

    @PostMapping("/reserva/{reservaId}/coordinador/{coordId}/aprobar")
    public AprobacionDTO aprobar(@PathVariable Long reservaId, @PathVariable Long coordId) {
        return AprobacionDTO.fromEntity(service.cambiarEstadoReserva(reservaId, coordId, "APROBADA"));
    }

    @PostMapping("/reserva/{reservaId}/coordinador/{coordId}/rechazar")
    public AprobacionDTO rechazar(@PathVariable Long reservaId, @PathVariable Long coordId) {
        return AprobacionDTO.fromEntity(service.cambiarEstadoReserva(reservaId, coordId, "RECHAZADA"));
    }




    @GetMapping("/{id}")
    public AprobacionesModel obtener(@PathVariable Long id) {
        return service.getById(id);
    }
}

