package com.example.ProyectoFinalMartin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.Set;

@Audited
@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona extends Base {
    private String nombre;
    private String apellido;
    private String correo;
    private Long dni;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;
}


