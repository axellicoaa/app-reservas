package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.models.EquipamientoModel;

@Repository
public interface EquipamientoRepository extends JpaRepository<EquipamientoModel, Long> {
}
