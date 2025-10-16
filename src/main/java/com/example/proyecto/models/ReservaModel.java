package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reservas")
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_seq")
    @SequenceGenerator(name = "reserva_seq", sequenceName = "reserva_seq", allocationSize = 1)
    @Column(name = "id_reserva")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Time horaInicio;
    private Time horaFin;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private SalaModel sala;

    @OneToOne(mappedBy = "reserva")
    private AprobacionesModel aprobacion;
}
