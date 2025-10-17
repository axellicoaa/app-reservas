package com.example.proyecto.controllers;

import com.example.proyecto.dtos.AprobacionDTO;
import com.example.proyecto.models.AprobacionesModel;
import com.example.proyecto.services.AprobacionService;
import org.springframework.web.bind.annotation.*;

// AprobacionController.java
import java.util.List;

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

    @PostMapping("/reserva/{reservaId}/coordinador/{coordId}")
    public AprobacionDTO aprobar(@PathVariable Long reservaId, @PathVariable Long coordId) {
        return AprobacionDTO.fromEntity(service.aprobarReserva(reservaId, coordId));
    }



    @GetMapping("/{id}")
    public AprobacionesModel obtener(@PathVariable Long id) {
        return service.getById(id);
    }
}

