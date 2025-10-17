package com.example.proyecto.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "nombre_sala", unique=true, nullable=false)
    private String nombreSala;
    @Column(name = "capacidad_maxima",unique= true, nullable=false)
    private Integer capacidadMaxima;

    @OneToMany(mappedBy = "sala")
    private List<ReservaModel> reservas;

    @ManyToMany
    @JoinTable(name = "sala_equipamiento", joinColumns = @JoinColumn(name = "id_sala"), inverseJoinColumns = @JoinColumn(name = "id_equipamiento"))
    private List<EquipamientoModel> equipamientos= new ArrayList<>();
}
