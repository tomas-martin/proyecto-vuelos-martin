package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Reserva;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import com.example.ProyectoFinalMartin.repository.ReservaRepository;
import com.example.ProyectoFinalMartin.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long> implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaServiceImpl(BaseRepository<Reserva, Long> baseRepository, ReservaRepository reservaRepository) {
        super(baseRepository);
        this.reservaRepository = reservaRepository;
    }
}
