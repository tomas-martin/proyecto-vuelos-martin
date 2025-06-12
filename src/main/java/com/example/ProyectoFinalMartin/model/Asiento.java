package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Base;
import com.example.ProyectoFinalMartin.model.ClaseServicio;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "asientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Asiento extends Base {

    private int filaAsiento;

    @Column(length = 2)
    private String letraAsiento; // Usar String para soportar "A", "B", "AA", etc.

    @Enumerated(EnumType.STRING)
    private ClaseServicio claseAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avion_id", nullable = false)
    @JsonBackReference
    private Avion avion;

    private boolean ocupado = false;
}