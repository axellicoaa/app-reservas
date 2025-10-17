package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aprobaciones")
public class AprobacionesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aprobacion_seq")
    @SequenceGenerator(name = "aprobacion_seq", sequenceName = "aprobacion_seq", allocationSize = 1)
    @Column(name = "id_aprobacion")
    private Long id;

    @Column(nullable = false)
    private String estado; 

    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private UsuarioModel usuario;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private ReservaModel reserva;
}
