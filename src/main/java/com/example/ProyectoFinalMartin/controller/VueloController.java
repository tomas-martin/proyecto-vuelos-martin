package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Vuelo;
import com.example.ProyectoFinalMartin.service.VueloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/vuelos")
public class VueloController extends BaseControllerImpl<Vuelo, VueloServiceImpl> {
    public VueloController(VueloServiceImpl servicio) {
        super(servicio);
    }

}