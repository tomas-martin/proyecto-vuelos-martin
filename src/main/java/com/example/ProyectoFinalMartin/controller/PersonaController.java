package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.model.Persona;
import com.example.ProyectoFinalMartin.service.PersonaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/personas")  // Cambiado de "api/personas" a "/personas" para consistencia
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> {

    public PersonaController(PersonaServiceImpl servicio) {
        super(servicio);
    }

    // Endpoint para buscar persona por DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable Long dni) {
        try {
            Optional<Persona> persona = Optional.ofNullable(servicio.findByDni(dni));
            if (persona.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(persona.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\":\"No se encontró ninguna persona con DNI: " + dni + "\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al buscar la persona: " + e.getMessage() + "\"}");
        }
    }
}

