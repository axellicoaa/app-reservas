package com.example.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.proyecto.models.AreaModel;
import com.example.proyecto.repositories.AreaRepository;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<AreaModel> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<AreaModel> getAreaById(Long id) {
        return areaRepository.findById(id);
    }

    public AreaModel createArea(AreaModel area) {
        return areaRepository.save(area);
    }

    public AreaModel updateArea(Long id, AreaModel area) {
        validarArea(area);
        Optional<AreaModel> existingArea = areaRepository.findById(id);
        if (existingArea.isPresent()) {
            AreaModel updatedArea = existingArea.get();
            updatedArea.setNombreArea(area.getNombreArea());
            updatedArea.setUsuarios(area.getUsuarios());
            return areaRepository.save(updatedArea);
        } else {
            throw new RuntimeException("Área no encontrada con id: " + id);
        }
    }

    public void deleteArea(Long id) {
        areaRepository.deleteById(id);
    }

    public void validarArea(AreaModel area) {
        if (area.getNombreArea() == null || area.getNombreArea().isEmpty()) {
            throw new IllegalArgumentException("El nombre del área no puede estar vacío");
        }
    }

}
