// ReservaController.java
package com.example.proyecto.controllers;

import com.example.proyecto.dtos.ReservaDTO;
import com.example.proyecto.dtos.ReservaRequest;
import com.example.proyecto.models.ReservaModel;
import com.example.proyecto.services.ReservaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) { this.service = service; }

    @PostMapping
    public ReservaDTO crear(@RequestBody ReservaRequest request) {
        ReservaModel saved = service.crearReserva(request.getReserva(), request.getEquipamientosRequeridos());
        return ReservaDTO.fromEntity(saved);
    }

    @GetMapping
    public List<ReservaDTO> listar() { return service.getAll(); }
    @GetMapping("/sala/{id}")
    public List<ReservaDTO> porSala(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return service.getPorSala(id, desde, hasta);
    }


    @GetMapping("/usuario/{id}")
    public List<ReservaDTO> porUsuario(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return service.getPorUsuario(id, desde, hasta);
    }
}
