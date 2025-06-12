package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Piloto;
import com.example.ProyectoFinalMartin.service.PilotoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pilotos")
public class PilotoController extends BaseControllerImpl<Piloto, PilotoServiceImpl> {
    public PilotoController(PilotoServiceImpl servicio) {
        super(servicio);
    }
}