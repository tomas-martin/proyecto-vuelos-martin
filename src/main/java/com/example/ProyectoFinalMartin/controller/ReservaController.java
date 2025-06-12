package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.dto.ReservaDTO;
import com.example.ProyectoFinalMartin.model.Reserva;
import com.example.ProyectoFinalMartin.service.ReservaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl> {
    public ReservaController(ReservaServiceImpl servicio) {
        super(servicio);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            Reserva nuevaReserva = servicio.save(E);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"No se pudo crear la reserva: " + e.getMessage() + "\"}");
        }
    }

}