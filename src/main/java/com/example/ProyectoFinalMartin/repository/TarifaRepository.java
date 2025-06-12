package com.example.ProyectoFinalMartin.repository;


import com.example.ProyectoFinalMartin.model.ClaseServicio;
import com.example.ProyectoFinalMartin.model.Tarifa;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaRepository extends BaseRepository<Tarifa, Long> {

    List<Tarifa> findByClaseTarifa(ClaseServicio clase);

    List<Tarifa> findByVueloId(Long vueloId);
}