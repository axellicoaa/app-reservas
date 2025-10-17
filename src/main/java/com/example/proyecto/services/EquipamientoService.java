package com.example.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.EquipamientoDTO;
import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.repositories.EquipamientoRepository;

@Service
public class EquipamientoService {
    private final EquipamientoRepository repo;

    public EquipamientoService(EquipamientoRepository repo) {
        this.repo = repo;
    }

    public List<EquipamientoDTO> getAll() {
        return repo.findAll().stream()
                .map(EquipamientoDTO::fromEntity)
                .toList();
    }

    public EquipamientoModel create(EquipamientoModel e) {
        return repo.save(e);
    }
}

