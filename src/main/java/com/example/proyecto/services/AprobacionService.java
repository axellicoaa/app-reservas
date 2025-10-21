package com.example.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.AprobacionDTO;
import com.example.proyecto.models.AprobacionesModel;
import com.example.proyecto.models.ReservaModel;
import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.repositories.AprobacionesRepository;
import com.example.proyecto.repositories.ReservaRepository;
import com.example.proyecto.repositories.UsuarioRepository;

@Service
public class AprobacionService {
    private final AprobacionesRepository repo;
    private final ReservaRepository reservaRepo;
    private final UsuarioRepository usuarioRepo;

    public AprobacionService(AprobacionesRepository repo, ReservaRepository reservaRepo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.reservaRepo = reservaRepo;
        this.usuarioRepo = usuarioRepo;
    }

public AprobacionesModel cambiarEstadoReserva(Long idReserva, Long idUsuarioCoordinador, String nuevoEstado) {
    ReservaModel reserva = reservaRepo.findById(idReserva)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

    UsuarioModel usuario = usuarioRepo.findById(idUsuarioCoordinador)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    if (!"COORDINADOR".equalsIgnoreCase(usuario.getRol())) {
        throw new RuntimeException("Solo un coordinador puede aprobar/rechazar reservas");
    }

    if (!"PENDIENTE".equalsIgnoreCase(reserva.getEstado())) {
        throw new RuntimeException("La reserva ya fue procesada");
    }

    reserva.setEstado(nuevoEstado); // APROBADA o RECHAZADA

    AprobacionesModel aprobacion = new AprobacionesModel();
    aprobacion.setEstado(nuevoEstado);
    aprobacion.setReserva(reserva);
    aprobacion.setUsuario(usuario);

    return repo.save(aprobacion);
}

    public List<AprobacionDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(AprobacionDTO::fromEntity)
                .toList();
    }

    public AprobacionesModel getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aprobación no encontrada"));
    }
}
