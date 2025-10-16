package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.models.AreaModel;
import com.example.proyecto.services.AreaService;

@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public List<AreaModel> getAllAreas() {
        return areaService.getAllAreas();
    }

    @GetMapping("/{id}")
    public AreaModel getAreaById(@PathVariable Long id) {
        return areaService.getAreaById(id).orElse(null);
    }

    @PostMapping
    public AreaModel createArea(@RequestBody AreaModel area) {
        return areaService.createArea(area);
    }

    @PutMapping("/{id}")
    public AreaModel updateArea(@PathVariable Long id, @RequestBody AreaModel area) {
        return areaService.updateArea(id, area);
    }

    @DeleteMapping("/{id}")
    public void deleteArea(@PathVariable Long id) {
        areaService.deleteArea(id);
    }

}
