package com.example.ProyectoFinalMartin.repository;


import com.example.ProyectoFinalMartin.model.Asiento;
import com.example.ProyectoFinalMartin.model.ClaseServicio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends BaseRepository<Asiento, Long> {

    List<Asiento> findByAvionId(Long avionId);

    List<Asiento> findByClaseAsiento(ClaseServicio clase);
}