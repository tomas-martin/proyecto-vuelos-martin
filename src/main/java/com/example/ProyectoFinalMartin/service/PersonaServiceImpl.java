package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Persona;
import com.example.ProyectoFinalMartin.repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        super(personaRepository);
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona findByDni(Long dni) throws Exception {
        try {
            return personaRepository.findByDni(dni);
        } catch (Exception e) {
            throw new Exception("Error al buscar persona por DNI", e);
        }
    }
}

