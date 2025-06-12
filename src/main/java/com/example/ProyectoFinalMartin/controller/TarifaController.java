package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Tarifa;
import com.example.ProyectoFinalMartin.service.TarifaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tarifas")
public class TarifaController extends BaseControllerImpl<Tarifa, TarifaServiceImpl> {
    public TarifaController(TarifaServiceImpl servicio) {
        super(servicio);
    }
}