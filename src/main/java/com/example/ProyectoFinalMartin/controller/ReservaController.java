package com.example.ProyectoFinalMartin.controller;

import com.example.ProyectoFinalMartin.dto.ReservaDTO;
import com.example.ProyectoFinalMartin.model.Reserva;
import com.example.ProyectoFinalMartin.service.ReservaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl> {

    public ReservaController(ReservaServiceImpl servicio) {
        super(servicio);
    }

    @PostMapping(value = "crear", consumes = "application/json;charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            Reserva nuevaReserva = servicio.crearReservaCompleta(reservaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"No se pudo crear la reserva: " + e.getMessage() + "\"}");
        }
    }

    // Sobrescribir el método save del controlador base para manejar el objeto complejo
    @PostMapping("/save-complejo")
    public ResponseEntity<?> save(@RequestBody Object entity) {
        try {
            // Si es un ReservaDTO (objeto complejo del frontend)
            if (entity instanceof java.util.Map) {
                @SuppressWarnings("unchecked")
                java.util.Map<String, Object> map = (java.util.Map<String, Object>) entity;

                // Convertir el Map a ReservaDTO
                ReservaDTO reservaDTO = convertMapToReservaDTO(map);
                Reserva nuevaReserva = servicio.crearReservaCompleta(reservaDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
            } else {
                // Si es una Reserva simple, usar el método original
                return super.save((Reserva) entity);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error al procesar la reserva: " + e.getMessage() + "\"}");
        }
    }

    private ReservaDTO convertMapToReservaDTO(Map<String, Object> map) {
        ReservaDTO dto = new ReservaDTO();

        // Extraer datos de persona
        if (map.containsKey("persona")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> personaMap = (Map<String, Object>) map.get("persona");
            dto.setPersonaDni(((Number) personaMap.get("dni")).longValue());
            dto.setPersonaNombre((String) personaMap.get("nombre"));
            dto.setPersonaApellido((String) personaMap.get("apellido"));
            dto.setPersonaCorreo((String) personaMap.get("correo"));
        }

        // Extraer datos de pago
        if (map.containsKey("pago")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> pagoMap = (Map<String, Object>) map.get("pago");
            dto.setPagoCantidad(((Number) pagoMap.get("cantidad_pago")).doubleValue());}

        // Extraer datos de reserva
        if (map.containsKey("reserva")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> reservaMap = (Map<String, Object>) map.get("reserva");
            dto.setVueloId(((Number) reservaMap.get("vuelo_id")).longValue());
        }

        // Extraer datos de tarjeta
        if (map.containsKey("tarjeta")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> tarjetaMap = (Map<String, Object>) map.get("tarjeta");
            dto.setTarjetaNumero((String) tarjetaMap.get("numero_tarjeta"));
            dto.setTarjetaTipo((String) tarjetaMap.get("tipo_tarjeta"));
        }

        // Extraer detalles adicionales si existen
        if (map.containsKey("detallesVuelo")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> detallesMap = (Map<String, Object>) map.get("detallesVuelo");
            dto.setTipoServicio((String) detallesMap.get("tipoServicio"));
        }

        return dto;
    }
}