package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.controller.BaseControllerImpl;
import com.example.ProyectoFinalMartin.model.Pago;
import com.example.ProyectoFinalMartin.service.PagoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pagos")
public class PagoController extends BaseControllerImpl<Pago, PagoServiceImpl> {
    public PagoController(PagoServiceImpl servicio) {
        super(servicio);
    }
}