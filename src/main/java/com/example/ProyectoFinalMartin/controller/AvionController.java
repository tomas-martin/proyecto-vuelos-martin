package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Avion;
import com.example.ProyectoFinalMartin.service.AvionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/aviones")
public class AvionController extends BaseControllerImpl<Avion, AvionServiceImpl> {
    public AvionController(AvionServiceImpl servicio) {
        super(servicio);
    }
}