package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Ciudad;
import com.example.ProyectoFinalMartin.service.CiudadServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ciudades")
public class CiudadController extends BaseControllerImpl<Ciudad, CiudadServiceImpl> {
    public CiudadController(CiudadServiceImpl servicio) {
        super(servicio);
    }
}