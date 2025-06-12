package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Vuelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VueloRepository extends BaseRepository<Vuelo, Long> {

    @Query("SELECT v FROM Vuelo v WHERE v.salida BETWEEN :inicio AND :fin")
    List<Vuelo> findByRangoFechas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("SELECT v FROM Vuelo v JOIN v.aeropuertos a WHERE a.id = :aeropuertoId")
    Page<Vuelo> findByAeropuerto(
            @Param("aeropuertoId") Long aeropuertoId,
            Pageable pageable
    );
}
