package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "salas")
public class SalaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_seq")
    @SequenceGenerator(name = "sala_seq", sequenceName = "sala_seq", allocationSize = 1)
    @Column(name = "id_sala")
    private Long id;

    private String nombreSala;
    private Integer capacidadMaxima;

    @OneToMany(mappedBy = "sala")
    private List<ReservaModel> reservas;

    @ManyToMany
    @JoinTable(name = "sala_equipamiento", joinColumns = @JoinColumn(name = "id_sala"), inverseJoinColumns = @JoinColumn(name = "id_equipamiento"))
    private List<EquipamientoModel> equipamientos;
}
