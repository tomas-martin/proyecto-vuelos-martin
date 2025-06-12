package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
    Persona findByDni(Long dni);
}
