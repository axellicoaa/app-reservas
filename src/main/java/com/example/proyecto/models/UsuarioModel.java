package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long id;

    private String nombre;
    private String email;
    private String ruc_ci;
    private String rol;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private AreaModel area;

    @OneToMany(mappedBy = "usuario")
    private List<ReservaModel> reservas;

    @OneToMany(mappedBy = "usuario")
    private List<AprobacionesModel> aprobaciones;
}
