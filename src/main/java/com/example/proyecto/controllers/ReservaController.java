// ReservaController.java
package com.example.proyecto.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.ReservaDTO;
import com.example.proyecto.dtos.ReservaRequest;
import com.example.proyecto.dtos.ReservaResumenDTO;
import com.example.proyecto.repositories.ReservaRepository;
import com.example.proyecto.services.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    private final ReservaService service;
    private final ReservaRepository reservaRepo;

    public ReservaController(ReservaService service, ReservaRepository reservaRepo) { 
        this.service = service;
        this.reservaRepo = reservaRepo;
    }

    // ✅ POST Crear reserva
    @PostMapping
    public ReservaDTO crear(@RequestBody ReservaRequest request) {
        return service.crearReserva(request);
    }

    // ✅ GET todas las reservas
    @GetMapping
    public List<ReservaDTO> listar() {
        return service.getAll();
    }
    
    // ✅ GET reservas por sala en un rango de fechas
    @GetMapping("/sala/{id}")
    public List<ReservaDTO> porSala(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return service.getPorSala(id, desde, hasta);
    }

    // ✅ GET reservas por usuario (con o sin rango de fechas)
    @GetMapping("/usuario/{idUsuario}")
    public List<ReservaDTO> listarPorUsuario(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        if (desde != null && hasta != null) {
            return reservaRepo.findByUsuarioIdAndFechaBetween(idUsuario, desde, hasta)
                    .stream().map(ReservaDTO::fromEntity).toList();
        } else {
            return reservaRepo.findByUsuarioId(idUsuario)
                    .stream().map(ReservaDTO::fromEntity).toList();
        }
    }

    // ✅ GET resumen de reservas (para dashboard coordinador)
    @GetMapping("/resumen")
    public ReservaResumenDTO getResumen() {
        long pendientes = reservaRepo.countByEstado("PENDIENTE");
        long aprobadas = reservaRepo.countByEstado("APROBADA");
        long rechazadas = reservaRepo.countByEstado("RECHAZADA");

        return new ReservaResumenDTO(pendientes, aprobadas, rechazadas);
    }
    // ReservaController.java
    @GetMapping("/pendientes")
    public List<ReservaDTO> listarPendientes() {
        return service.getPendientes();
    }

}
