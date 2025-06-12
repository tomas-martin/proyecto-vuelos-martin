package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Base;
import com.example.ProyectoFinalMartin.model.Reserva;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Pago extends Base {
    @Column(unique = true)
    private String numeroPago;
    private double cantidadPago;

    @OneToOne(mappedBy = "pago")
    @JsonBackReference
    private Reserva reserva;
}