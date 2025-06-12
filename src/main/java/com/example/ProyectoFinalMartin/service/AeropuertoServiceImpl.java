package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Aeropuerto;
import com.example.ProyectoFinalMartin.repository.AeropuertoRepository;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AeropuertoServiceImpl extends BaseServiceImpl<Aeropuerto, Long> implements AeropuertoService {
    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    public AeropuertoServiceImpl(BaseRepository<Aeropuerto, Long> baseRepository, AeropuertoRepository aeropuertoRepository) {
        super(baseRepository);
        this.aeropuertoRepository = aeropuertoRepository;
    }
}
