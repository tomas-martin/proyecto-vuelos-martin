package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Aerolinea;
import com.example.ProyectoFinalMartin.model.Aeropuerto;
import com.example.ProyectoFinalMartin.model.Base;
import com.example.ProyectoFinalMartin.model.Tarifa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vuelos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Vuelo extends Base {

    @ManyToOne
    @JoinColumn(name = "origen_id", nullable = false)
    private Ciudad origen;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Ciudad destino;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime salida;

    @ManyToOne
    @JoinColumn(name = "avion_id", nullable = false)
    @JsonIgnore  // O usar @JsonBackReference si necesitas mostrar datos del avión
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "aerolinea_id", nullable = false)
    @JsonIgnore  // O usar @JsonBackReference si necesitas mostrar datos de aerolínea
    private Aerolinea aerolinea;

    @ManyToMany
    @JoinTable(
            name = "vuelo_aeropuertos",
            joinColumns = @JoinColumn(name = "vuelo_id"),
            inverseJoinColumns = @JoinColumn(name = "aeropuerto_id")
    )
    @JsonManagedReference
    private Set<Aeropuerto> aeropuertos;

    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Tarifa> tarifas = new HashSet<>();
}