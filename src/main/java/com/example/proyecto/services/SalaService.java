package com.example.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.models.SalaModel;
import com.example.proyecto.repositories.EquipamientoRepository;
import com.example.proyecto.repositories.SalaRepository;

@Service
public class SalaService {
    private final SalaRepository repo;
    private final EquipamientoRepository equipamientoRepo;

    public SalaService(SalaRepository repo, EquipamientoRepository equipamientoRepo) {
        this.repo = repo;
        this.equipamientoRepo = equipamientoRepo;
    }

    public List<SalaModel> getAll() { return repo.findAll(); }

    public SalaModel save(SalaModel s) { return repo.save(s); }


    public SalaModel agregarEquipamiento(Long idSala, Long idEquipo) {
        SalaModel sala = repo.findById(idSala)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        EquipamientoModel equipo = equipamientoRepo.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        sala.getEquipamientos().add(equipo);
        return repo.save(sala);
    }


    public SalaModel agregarVariosEquipamientos(Long idSala, List<Long> idsEquipos) {
        SalaModel sala = repo.findById(idSala)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        List<EquipamientoModel> equipos = equipamientoRepo.findAllById(idsEquipos);
        sala.getEquipamientos().addAll(equipos);

        return repo.save(sala);
    }
    public SalaModel getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
    }

}
