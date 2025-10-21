package com.example.proyecto.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.ReservaDTO;
import com.example.proyecto.dtos.ReservaRequest;
import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.models.ReservaModel;
import com.example.proyecto.models.SalaModel;
import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.repositories.ReservaRepository;
import com.example.proyecto.repositories.SalaRepository;
import com.example.proyecto.repositories.UsuarioRepository;

@Service
public class ReservaService {
        private final ReservaRepository reservaRepo;
        private final SalaRepository salaRepo;
        private final UsuarioRepository usuarioRepo;

        public ReservaService(
                        ReservaRepository reservaRepo,
                        SalaRepository salaRepo,
                        UsuarioRepository usuarioRepo) {
                this.reservaRepo = reservaRepo;
                this.salaRepo = salaRepo;
                this.usuarioRepo = usuarioRepo;
        }

        // Crear reserva desde ReservaRequest
        public ReservaDTO crearReserva(ReservaRequest request) {
                // Buscar sala
                SalaModel sala = salaRepo.findById(request.getSalaId())
                                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

                // Buscar usuario
                UsuarioModel usuario = usuarioRepo.findById(request.getUsuarioId())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                if (request.getFecha().isBefore(LocalDate.now())) {
                        throw new RuntimeException("No se puede reservar en una fecha pasada");
                }
                // Validar horario laboral
                int hInicio = request.getHoraInicio().getHour();
                int hFin = request.getHoraFin().getHour();
                if (hInicio < 8 || hFin > 18) {
                        throw new RuntimeException("Fuera del horario laboral (8:00 - 18:00)");
                }
                // Validar conflictos de horario SOLO con reservas aprobadas
                boolean conflicto = reservaRepo
                                .existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqualAndEstado(
                                                sala.getId(),
                                                request.getFecha(),
                                                request.getHoraInicio(),
                                                request.getHoraFin(),
                                                "APROBADA");

                if (conflicto) {
                        throw new RuntimeException("La sala ya está reservada en ese horario");
                }

                // Validar equipamientos
                List<Long> idsSala = sala.getEquipamientos() == null ? List.of()
                                : sala.getEquipamientos().stream().map(EquipamientoModel::getId).toList();

                List<Long> faltan = (request.getEquipamientosRequeridos() == null ? List.<Long>of()
                                : request.getEquipamientosRequeridos())
                                .stream().filter(id -> !idsSala.contains(id)).toList();

                if (!faltan.isEmpty()) {
                        throw new RuntimeException("La sala no tiene los siguientes equipos: " + faltan);
                }

                // Construir y guardar Reserva
                ReservaModel reserva = new ReservaModel();
                reserva.setSala(sala);
                reserva.setUsuario(usuario);
                reserva.setFecha(request.getFecha());
                reserva.setHoraInicio(request.getHoraInicio());
                reserva.setHoraFin(request.getHoraFin());
                reserva.setEstado("PENDIENTE");

                ReservaModel saved = reservaRepo.save(reserva);

                return ReservaDTO.fromEntity(saved);
        }

        // ✅ Otros métodos
        public List<ReservaDTO> getAll() {
                return reservaRepo.findAll().stream().map(ReservaDTO::fromEntity).toList();
        }

        public List<ReservaDTO> getPorSala(Long salaId, LocalDate desde, LocalDate hasta) {
                return reservaRepo.findBySalaIdAndFechaBetween(salaId, desde, hasta)
                                .stream().map(ReservaDTO::fromEntity).toList();
        }

        public List<ReservaDTO> getPorUsuario(Long usuarioId, LocalDate desde, LocalDate hasta) {
                return reservaRepo.findByUsuarioIdAndFechaBetween(usuarioId, desde, hasta)
                                .stream().map(ReservaDTO::fromEntity).toList();
        }

        // ReservaService.java
        public List<ReservaDTO> getPendientes() {
                return reservaRepo.findByEstado("PENDIENTE")
                                .stream().map(ReservaDTO::fromEntity).toList();
        }

}
