// ReservaService.java
package com.example.proyecto.services;

import com.example.proyecto.dtos.ReservaDTO;
import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.models.ReservaModel;
import com.example.proyecto.models.SalaModel;
import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.repositories.ReservaRepository;
import com.example.proyecto.repositories.SalaRepository;
import com.example.proyecto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repo;
    private final SalaRepository salaRepo;
    private final UsuarioRepository usuarioRepo; 

    public ReservaService(ReservaRepository repo, SalaRepository salaRepo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.salaRepo = salaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public ReservaModel crearReserva(ReservaModel r, List<Long> equipamientosRequeridos) {

        SalaModel sala = salaRepo.findById(r.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));


        UsuarioModel usuario = usuarioRepo.findById(r.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        int hInicio = r.getHoraInicio().toLocalTime().getHour();
        int hFin = r.getHoraFin().toLocalTime().getHour();
        if (hInicio < 8 || hFin > 18) {
            throw new RuntimeException("Fuera del horario laboral (8:00 - 18:00)");
        }


        boolean conflicto = repo.existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqual(
                sala.getId(), r.getFecha(), r.getHoraInicio(), r.getHoraFin());
        if (conflicto) {
            throw new RuntimeException("Conflicto de horario con otra reserva");
        }


        List<Long> idsSala = (sala.getEquipamientos() == null ? List.<EquipamientoModel>of() : sala.getEquipamientos())
                .stream().map(EquipamientoModel::getId).toList();

        List<Long> faltan = (equipamientosRequeridos == null ? List.<Long>of() : equipamientosRequeridos)
                .stream().filter(id -> !idsSala.contains(id)).toList();

        if (!faltan.isEmpty()) {
            throw new RuntimeException("La sala no tiene los siguientes equipos: " + faltan);
        }

        r.setSala(sala);
        r.setUsuario(usuario);
        r.setEstado("PENDIENTE");

        return repo.save(r);
    }


    public List<ReservaDTO> getAll() {
        return repo.findAll().stream().map(ReservaDTO::fromEntity).toList();
    }

    public List<ReservaDTO> getPorSala(Long salaId, Date desde, Date hasta) {
        return repo.findBySalaIdAndFechaBetween(salaId, desde, hasta).stream().map(ReservaDTO::fromEntity).toList();
    }

    public List<ReservaDTO> getPorUsuario(Long usuarioId, Date desde, Date hasta) {
        return repo.findByUsuarioIdAndFechaBetween(usuarioId, desde, hasta).stream().map(ReservaDTO::fromEntity).toList();
    }
}
