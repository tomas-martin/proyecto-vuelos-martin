package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Pago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends BaseRepository<Pago, Long> {

}