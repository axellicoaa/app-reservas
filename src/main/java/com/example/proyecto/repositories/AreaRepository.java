package com.example.proyecto.repositories;

import com.example.proyecto.models.AreaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<AreaModel, Long> {
}
