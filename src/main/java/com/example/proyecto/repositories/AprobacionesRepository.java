package com.example.proyecto.repositories;

import com.example.proyecto.models.AprobacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprobacionesRepository extends JpaRepository<AprobacionesModel, Long> {

}
