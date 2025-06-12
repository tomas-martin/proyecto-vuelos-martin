package com.example.ProyectoFinalMartin.repository;

//package com.example.vuelos.repository;

//import com.example.vuelos.model.Aerolinea;
import com.example.ProyectoFinalMartin.model.Aerolinea;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AerolineaRepository extends BaseRepository<Aerolinea, Long> {

    Optional<Aerolinea> findByNombreAerolinea(String nombre);
}