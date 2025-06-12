package com.example.ProyectoFinalMartin.dto;

import com.example.ProyectoFinalMartin.model.Aeropuerto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AeropuertoDTO {
    private Long id;
    private String nombreAeropuerto;
    private String codigoAeropuerto;

    public static AeropuertoDTO convertirAeropuertoDTO(Aeropuerto aeropuerto) {
        if (aeropuerto == null) {
            return null;
        }
        return new AeropuertoDTO(
                aeropuerto.getId(),
                aeropuerto.getNombreAeropuerto(),
                aeropuerto.getNombreAeropuerto()
        );
    }
}