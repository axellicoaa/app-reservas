package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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
