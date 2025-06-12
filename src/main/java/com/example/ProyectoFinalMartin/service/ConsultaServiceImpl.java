package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Consulta;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import com.example.ProyectoFinalMartin.repository.ConsultaRepository;
import com.example.ProyectoFinalMartin.service.BaseServiceImpl;
import com.example.ProyectoFinalMartin.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl extends BaseServiceImpl<Consulta, Long> implements ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaServiceImpl(BaseRepository<Consulta, Long> baseRepository, ConsultaRepository consultaRepository) {
        super(baseRepository);
        this.consultaRepository = consultaRepository;
    }
}
