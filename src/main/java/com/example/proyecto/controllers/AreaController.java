package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.AreaDTO;
import com.example.proyecto.models.AreaModel;
import com.example.proyecto.services.AreaService;

@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService service;

    public AreaController(AreaService service) { this.service = service; }

    @GetMapping
    public List<AreaDTO> listar() {
        return service.getAll();
    }

    @PostMapping public AreaModel crear(@RequestBody AreaModel a) {
        return service.save(a); }
}
