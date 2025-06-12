package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aeropuerto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Aeropuerto extends Base {
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombreAeropuerto;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    @JsonBackReference
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "aeropuertos")
    @JsonIgnore
    private Set<Vuelo> vuelos = new HashSet<>();
}