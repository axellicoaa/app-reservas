package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyecto.models.AreaModel;

@Repository
public interface AreaRepository extends JpaRepository<AreaModel, Long> {
}
