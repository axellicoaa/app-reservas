package com.example.proyecto.repositories;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.models.ReservaModel;

//Aqui hago uso de JPA para realizar un query y asi poder evaluar los conflictos que piden en el texto
@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findBySalaIdAndFechaBetween(Long salaId, Date desde, Date hasta);
    List<ReservaModel> findByUsuarioIdAndFechaBetween(Long usuarioId, Date desde, Date hasta);
    boolean existsBySalaIdAndFechaAndHoraInicioLessThanEqualAndHoraFinGreaterThanEqual( 
            Long salaId, Date fecha, Time horaInicio, Time horaFin);
}
