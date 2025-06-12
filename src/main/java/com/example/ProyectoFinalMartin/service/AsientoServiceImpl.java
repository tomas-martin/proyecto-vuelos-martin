package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Asiento;
import com.example.ProyectoFinalMartin.repository.AsientoRepository;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import com.example.ProyectoFinalMartin.service.AsientoService;
import com.example.ProyectoFinalMartin.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsientoServiceImpl extends BaseServiceImpl<Asiento, Long> implements AsientoService {
    @Autowired
    private AsientoRepository asientoRepository;

    public AsientoServiceImpl(BaseRepository<Asiento, Long> baseRepository, AsientoRepository asientoRepository) {
        super(baseRepository);
        this.asientoRepository = asientoRepository;
    }
}
