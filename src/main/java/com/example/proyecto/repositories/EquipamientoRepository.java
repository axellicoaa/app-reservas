package com.example.proyecto.repositories;

import com.example.proyecto.models.EquipamientoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamientoRepository extends JpaRepository<EquipamientoModel, Long> {
}
