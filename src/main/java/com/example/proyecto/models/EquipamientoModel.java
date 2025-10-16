package com.example.proyecto.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipamiento")
public class EquipamientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equip_seq")
    @SequenceGenerator(name = "equip_seq", sequenceName = "equip_seq", allocationSize = 1)
    @Column(name = "id_equipamiento")
    private Long id;

    private String tipoEquipo;

    @ManyToMany(mappedBy = "equipamientos")
    private List<SalaModel> salas;
}
