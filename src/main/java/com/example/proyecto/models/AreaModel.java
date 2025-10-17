package com.example.proyecto.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "area")
public class AreaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_seq")
    @SequenceGenerator(name = "area_seq", sequenceName = "area_seq", allocationSize = 1)
    @Column(name = "id_area")
    private Long id;

    @Column(nullable = false)
    private String nombreArea;

    @OneToMany(mappedBy = "area")
    private List<UsuarioModel> usuarios;
}
