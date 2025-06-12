package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Tarjeta;
import com.example.ProyectoFinalMartin.service.TarjetaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tarjetas")
public class TarjetaController extends BaseControllerImpl<Tarjeta, TarjetaServiceImpl> {
    public TarjetaController(TarjetaServiceImpl servicio) {
        super(servicio);
    }
}