package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Aeropuerto;
import com.example.ProyectoFinalMartin.service.AeropuertoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/aeropuertos")
public class AeropuertoController extends BaseControllerImpl<Aeropuerto, AeropuertoServiceImpl> {
    public AeropuertoController(AeropuertoServiceImpl servicio) {
        super(servicio);
    }

}