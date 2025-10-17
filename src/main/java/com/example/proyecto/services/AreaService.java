package com.example.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.AreaDTO;
import com.example.proyecto.models.AreaModel;
import com.example.proyecto.repositories.AreaRepository;

@Service
public class AreaService {
    private final AreaRepository repo;

    public AreaService(AreaRepository repo) {
        this.repo = repo;
    }

    public List<AreaDTO> getAll() {
    return repo.findAll()
                   .stream()
                   .map(AreaDTO::fromEntity)
                   .toList();
}

    public AreaModel save(AreaModel a) { return repo.save(a); }
}
