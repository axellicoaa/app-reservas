package com.example.proyecto.repositories;

import com.example.proyecto.models.SalaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<SalaModel, Long> {
}
