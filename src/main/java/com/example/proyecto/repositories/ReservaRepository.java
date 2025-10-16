package com.example.proyecto.repositories;

import com.example.proyecto.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findBySalaIdAndFechaBetween(Long salaId, Date desde, Date hasta);

    List<ReservaModel> findByUsuarioIdAndFechaBetween(Long usuarioId, Date desde, Date hasta);

    boolean existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqual(
            Long salaId, Date fecha, Time horaInicio, Time horaFin);
}
