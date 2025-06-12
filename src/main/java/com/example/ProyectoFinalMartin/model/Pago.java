package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Base;
import com.example.ProyectoFinalMartin.model.Reserva;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    private double cantidadPago;
}