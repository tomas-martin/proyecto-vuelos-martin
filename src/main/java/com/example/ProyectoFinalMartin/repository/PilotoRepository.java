package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Piloto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PilotoRepository extends BaseRepository<Piloto, Long> {

    @Query("SELECT p FROM Piloto p WHERE p.numeroPiloto = :numero")
    Optional<Piloto> findByNumeroPiloto(@Param("numero") String numeroPiloto);
}