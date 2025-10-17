package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.SalaDTO;
import com.example.proyecto.models.SalaModel;
import com.example.proyecto.services.SalaService;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService service;

    public SalaController(SalaService service) { this.service = service; }


    @GetMapping
    public List<SalaDTO> listar() {
        return service.getAll().stream()
                .map(SalaDTO::fromEntity)
                .toList();
    }
    @GetMapping("/{id}")
    public SalaDTO obtenerPorId(@PathVariable Long id) {
        SalaModel sala = service.getById(id);
        return SalaDTO.fromEntity(sala);
    }



    @PostMapping
    public SalaDTO crear(@RequestBody SalaModel s) {
        return SalaDTO.fromEntity(service.save(s));
    }


    @PostMapping("/{idSala}/equipamiento/{idEquipo}")
    public SalaDTO agregarEquipamiento(
            @PathVariable Long idSala,
            @PathVariable Long idEquipo) {
        return SalaDTO.fromEntity(service.agregarEquipamiento(idSala, idEquipo));
    }


    @PostMapping("/{idSala}/equipamientos")
    public SalaDTO agregarVariosEquipamientos(
            @PathVariable Long idSala,
            @RequestBody List<Long> idsEquipos) {
        return SalaDTO.fromEntity(service.agregarVariosEquipamientos(idSala, idsEquipos));
    }
}
