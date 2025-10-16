package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "aprobaciones")
public class AprobacionesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aprob_seq")
    @SequenceGenerator(name = "aprob_seq", sequenceName = "aprob_seq", allocationSize = 1)
    @Column(name = "id_aprobacion")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;

    private String estado;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private ReservaModel reserva;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;
}
