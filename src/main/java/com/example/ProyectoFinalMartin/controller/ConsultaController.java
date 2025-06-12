package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Consulta;
import com.example.ProyectoFinalMartin.service.ConsultaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/consultas")
public class ConsultaController extends BaseControllerImpl<Consulta, ConsultaServiceImpl> {
    public ConsultaController(ConsultaServiceImpl servicio) {
        super(servicio);
    }
}