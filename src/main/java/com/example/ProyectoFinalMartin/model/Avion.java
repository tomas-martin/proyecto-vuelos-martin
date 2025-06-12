package com.example.ProyectoFinalMartin.model;

import com.example.ProyectoFinalMartin.model.Asiento;
import com.example.ProyectoFinalMartin.model.Base;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aviones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Avion extends Base implements Especificacion {
    @Column(unique = true, nullable = false)
    private String numeroAvion;

    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Asiento> asientos = new ArrayList<>();

    private String tipoTurbina;
    private String tipoAvion;

    // Implementación de la interfaz
    @Override
    public String getTipoTurbina() {
        return tipoTurbina;
    }

    @Override
    public String getTipoAvion() {
        return tipoAvion;
    }
}

// Interfaz
interface Especificacion {
    String getTipoTurbina();
    String getTipoAvion();
}
