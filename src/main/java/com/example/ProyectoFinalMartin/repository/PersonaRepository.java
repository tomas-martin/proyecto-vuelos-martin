package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
    Persona findByDni(Long dni);

    @Query("SELECT p FROM Persona p LEFT JOIN FETCH p.reservas WHERE p.dni = :dni")
    Optional<Persona> findByDniWithReservas(@Param("dni") Long dni);

}

