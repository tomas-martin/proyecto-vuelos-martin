package com.example.ProyectoFinalMartin.controller;


import com.example.ProyectoFinalMartin.model.Persona;
import com.example.ProyectoFinalMartin.service.PersonaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/personas")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> {

    public PersonaController(PersonaServiceImpl servicio) {
        super(servicio);
    }
}

