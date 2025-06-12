package com.example.ProyectoFinalMartin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvionDTO {
    private Long id;
    private String numeroAvion;
    private int cantidadAsientos;

    public AvionDTO(Long id, String numeroAvion, int cantidadAsientos) {
        this.id = id;
        this.numeroAvion = numeroAvion;
        this.cantidadAsientos = cantidadAsientos;
    }

    // Getters y setters
}
