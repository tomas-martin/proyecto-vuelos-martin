package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Vuelo;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import com.example.ProyectoFinalMartin.repository.VueloRepository;
import com.example.ProyectoFinalMartin.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueloServiceImpl extends BaseServiceImpl<Vuelo, Long> implements VueloService {
    @Autowired
    private VueloRepository vueloRepository;

    public VueloServiceImpl(BaseRepository<Vuelo, Long> baseRepository, VueloRepository vueloRepository) {
        super(baseRepository);
        this.vueloRepository = vueloRepository;
    }
}
