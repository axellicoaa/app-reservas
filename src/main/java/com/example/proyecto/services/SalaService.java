package com.example.proyecto.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.SalaDTO;
import com.example.proyecto.dtos.SalaUpdateRequest;
import com.example.proyecto.models.EquipamientoModel;
import com.example.proyecto.models.SalaModel;
import com.example.proyecto.repositories.EquipamientoRepository;
import com.example.proyecto.repositories.ReservaRepository;
import com.example.proyecto.repositories.SalaRepository;

@Service
public class SalaService {
    private final SalaRepository repo;
    private final EquipamientoRepository equipamientoRepo;
    private final ReservaRepository reservaRepo;

    public SalaService(SalaRepository repo, EquipamientoRepository equipamientoRepo, ReservaRepository reservaRepo) {
        this.repo = repo;
        this.equipamientoRepo = equipamientoRepo;
        this.reservaRepo = reservaRepo;
    }

    public List<SalaModel> getAll() {
        return repo.findAll();
    }

    public SalaModel save(SalaModel s) {
        return repo.save(s);
    }

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

    // ✅ Salas con disponibilidad en tiempo real
    public List<SalaDTO> getSalasConDisponibilidad() {
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        return repo.findAll().stream()
                .map(sala -> {
                    boolean ocupada = reservaRepo
                            .existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqualAndEstado(
                                    sala.getId(), hoy, ahora, ahora, "APROBADA");

                    return SalaDTO.fromEntity(sala, !ocupada);
                })
                .toList();
    }

    // ✅ Contador de disponibilidad para el dashboard
    public Map<String, Long> contarDisponibilidad() {
        List<SalaDTO> salas = getSalasConDisponibilidad();
        long disponibles = salas.stream().filter(SalaDTO::disponible).count();
        long ocupadas = salas.size() - disponibles;

        return Map.of(
                "disponibles", disponibles,
                "ocupadas", ocupadas
        );
    }
    public SalaDTO actualizar(Long idSala, SalaUpdateRequest request) {
        SalaModel sala = repo.findById(idSala)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        // 👇 NO sobreescribimos el nombre, lo dejamos como está
        sala.setCapacidadMaxima(request.getCapacidadMaxima());

        if (request.getEquipamientosIds() != null) {
            var equipos = equipamientoRepo.findAllById(request.getEquipamientosIds());
            sala.setEquipamientos(equipos);
        }

        return SalaDTO.fromEntity(repo.save(sala));
    }


}
