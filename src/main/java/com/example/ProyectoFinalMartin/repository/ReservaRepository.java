package com.example.ProyectoFinalMartin.repository;


import com.example.ProyectoFinalMartin.model.Reserva;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.persona.id = :personaId")
    List<Reserva> findByPersonaId(@Param("personaId") Long personaId);

    @Query("SELECT r FROM Reserva r WHERE r.vuelo.id = :vueloId")
    List<Reserva> findByVueloId(@Param("vueloId") Long vueloId);
}