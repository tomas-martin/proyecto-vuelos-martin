package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Persona;

public interface PersonaService extends BaseService<Persona, Long> {
    Persona findByDni(Long dni) throws Exception;
}

