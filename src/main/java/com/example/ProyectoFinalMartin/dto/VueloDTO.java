package com.example.ProyectoFinalMartin.dto;

import com.example.ProyectoFinalMartin.dto.AerolineaDTO;
import com.example.ProyectoFinalMartin.dto.AvionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VueloDTO {
    private Long id;
    private String numeroVuelo;
    private LocalDateTime salida;
    private LocalDateTime destino;
    private AvionDTO avion;
    private AerolineaDTO aerolinea;
    private List<AeropuertoDTO> aeropuertos;

    public VueloDTO(Long id, String numeroVuelo, LocalDateTime salida, LocalDateTime destino, AvionDTO avionDTO) {
    }
}