package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Aerolinea;
import com.example.ProyectoFinalMartin.service.AerolineaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/aerolineas")
public class AerolineaController extends BaseControllerImpl<Aerolinea, AerolineaServiceImpl> {
    public AerolineaController(AerolineaServiceImpl servicio) {
        super(servicio);
    }
}
