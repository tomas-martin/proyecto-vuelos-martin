package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Pago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends BaseRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.reserva.id = :reservaId")
    Optional<Pago> findByReservaId(@Param("reservaId") Long reservaId);

    @Query("SELECT p FROM Pago p WHERE p.numeroPago = :numero")
    Optional<Pago> findByNumeroPago(@Param("numero") String numeroPago);
}