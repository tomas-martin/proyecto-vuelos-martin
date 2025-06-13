package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Reserva extends Base {
    @Column(nullable = false)
    private int numeroReserva;

    @ManyToOne
    @JoinColumn(name = "vuelo_id", nullable = false)
    private Vuelo vuelo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id", referencedColumnName = "id")
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonBackReference
    private Persona persona;
}