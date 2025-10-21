package com.example.proyecto.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.models.ReservaModel;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

        // Buscar reservas de una sala entre fechas
        List<ReservaModel> findBySalaIdAndFechaBetween(Long salaId, LocalDate desde, LocalDate hasta);

        // Buscar reservas de un usuario entre fechas
        List<ReservaModel> findByUsuarioIdAndFechaBetween(Long usuarioId, LocalDate desde, LocalDate hasta);

        // Validar conflicto de horario
        boolean existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqual(
                        Long salaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);

        // Validar conflicto con estado específico (ej: APROBADA)
        boolean existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqualAndEstado(
                        Long salaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado);

        // Buscar reservas en conflicto (mismo horario y sala, estado pendiente)
        List<ReservaModel> findBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqualAndEstado(
                        Long salaId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado);

        // Contar reservas por estado
        long countByEstado(String estado);

        // Listar todas las reservas de un usuario
        List<ReservaModel> findByUsuarioId(Long usuarioId);

        // ReservaRepository.java
        List<ReservaModel> findByEstado(String estado);

}
