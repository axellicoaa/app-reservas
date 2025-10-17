package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.EquipamientoDTO;
import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.services.EquipamientoService;

@RestController
@RequestMapping("/equipamiento")
public class EquipamientoController {
    private final EquipamientoService service;

    public EquipamientoController(EquipamientoService service) {
        this.service = service;
    }

    // GET con DTO
    @GetMapping
    public List<EquipamientoDTO> getAll() {
        return service.getAll();
    }

    // POST con entidad (no DTO)
    @PostMapping
    public EquipamientoModel create(@RequestBody EquipamientoModel e) {
        return service.create(e);
    }
}
