package com.example.ProyectoFinalMartin.repository;
import com.example.ProyectoFinalMartin.model.Persona;
import com.example.ProyectoFinalMartin.model.Consulta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends BaseRepository<Consulta, Long> {

    List<Consulta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT c FROM Consulta c WHERE c.persona.id = :personaId")
    List<Consulta> findByPersonaId(@Param("personaId") Long personaId);


}