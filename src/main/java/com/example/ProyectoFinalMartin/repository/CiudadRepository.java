package com.example.ProyectoFinalMartin.repository;

import com.example.ProyectoFinalMartin.model.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends BaseRepository<Ciudad, Long> {

    @Query("SELECT c FROM Ciudad c WHERE c.nombreCiudad LIKE %:filtro%")
    Page<Ciudad> search(@Param("filtro") String filtro, Pageable pageable);

    @Query("SELECT c FROM Ciudad c WHERE c.nombreCiudad LIKE %:filtro%")
    List<Ciudad> search(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM ciudades WHERE nombre_ciudad ILIKE %:filtro%",
            nativeQuery = true)
    List<Ciudad> searchNativo(@Param("filtro") String filtro);
}
