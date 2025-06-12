package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Tarjeta;
import com.example.ProyectoFinalMartin.model.TipoTarjeta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends BaseRepository<Tarjeta, Long> {

    List<Tarjeta> findByPersonaId(Long personaId);

    List<Tarjeta> findByTipoTarjeta(TipoTarjeta tipo);
}
