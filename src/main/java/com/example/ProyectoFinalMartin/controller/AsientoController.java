package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Asiento;
import com.example.ProyectoFinalMartin.service.AsientoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/asientos")
public class AsientoController extends BaseControllerImpl<Asiento, AsientoServiceImpl> {
    public AsientoController(AsientoServiceImpl servicio) {
        super(servicio);
    }
}