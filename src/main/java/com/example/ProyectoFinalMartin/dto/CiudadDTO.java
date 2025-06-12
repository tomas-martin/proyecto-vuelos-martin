package com.example.ProyectoFinalMartin.dto;

import com.example.ProyectoFinalMartin.model.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiudadDTO {
    private Long id;
    private String nombreCiudad;

    public CiudadDTO convertirCiudadDTO(Ciudad ciudad) {
        return new CiudadDTO(
                ciudad.getId(),
                ciudad.getNombreCiudad()
        );
    }
}
