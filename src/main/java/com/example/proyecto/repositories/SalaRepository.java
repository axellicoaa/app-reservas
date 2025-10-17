package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.models.SalaModel;

@Repository
public interface SalaRepository extends JpaRepository<SalaModel, Long> {
}
