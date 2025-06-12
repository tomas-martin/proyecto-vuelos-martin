package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.model.Persona;
import com.example.ProyectoFinalMartin.repository.BaseRepository;
import com.example.ProyectoFinalMartin.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements PersonaService {

    private final PersonaRepository personaRepository;

        @Autowired
        public PersonaServiceImpl(PersonaRepository personaRepository) {
            super(personaRepository);
            this.personaRepository = personaRepository;
        }

        public Persona findByDni(Long dni) {
            return personaRepository.findByDni(dni);
        }
    }

