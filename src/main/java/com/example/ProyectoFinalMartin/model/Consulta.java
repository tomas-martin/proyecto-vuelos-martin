package com.example.ProyectoFinalMartin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Consulta extends Base {
    @Column(unique = true)
    private String numeroConsulta;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonManagedReference
    private Persona persona;

}