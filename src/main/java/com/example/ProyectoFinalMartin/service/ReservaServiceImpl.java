package com.example.ProyectoFinalMartin.service;

import com.example.ProyectoFinalMartin.dto.ReservaDTO;
import com.example.ProyectoFinalMartin.model.*;
import com.example.ProyectoFinalMartin.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long> implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PersonaServiceImpl personaService;

    @Autowired
    private PagoServiceImpl pagoService;

    @Autowired
    private TarjetaServiceImpl tarjetaService;

    @Autowired
    private VueloServiceImpl vueloService;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        super(reservaRepository);
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Reserva crearReservaCompleta(ReservaDTO reservaDTO) throws Exception {
        try {
            // 1. Buscar o crear la persona
            Persona persona = buscarOCrearPersona(reservaDTO);

            // 2. Buscar el vuelo
            Vuelo vuelo = vueloService.findById(reservaDTO.getVueloId());
            if (vuelo == null) {
                throw new Exception("No se encontró el vuelo con ID: " + reservaDTO.getVueloId());
            }

            // 3. Crear el pago
            Pago pago = new Pago();
            pago.setCantidadPago(reservaDTO.getPagoCantidad());
            pago.setNumeroPago(reservaDTO.getPagoNumero());
            pago = pagoService.save(pago);

            // 4. Crear la reserva
            Reserva reserva = new Reserva();
            reserva.setNumeroReserva(reservaDTO.getReservaNumero());
            reserva.setPersona(persona);
            reserva.setPago(pago);
            reserva.setVuelo(vuelo);
            reserva = save(reserva);

            // 5. Crear la tarjeta
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setNumeroTarjeta(reservaDTO.getTarjetaNumero());
            tarjeta.setTipoTarjeta(reservaDTO.getTarjetaTipo());
            tarjeta.setPersona(persona); // Asumo que la tarjeta está relacionada con la persona
            tarjetaService.save(tarjeta);

            return reserva;

        } catch (Exception e) {
            throw new Exception("Error al crear la reserva completa: " + e.getMessage(), e);
        }
    }

    private Persona buscarOCrearPersona(ReservaDTO reservaDTO) throws Exception {
        try {
            // Buscar si ya existe una persona con ese DNI
            Optional<Persona> personaExistente = Optional.ofNullable(personaService.findByDni(reservaDTO.getPersonaDni()));

            if (personaExistente.isPresent()) {
                return personaExistente.get();
            } else {
                // Crear nueva persona
                Persona nuevaPersona = new Persona();
                nuevaPersona.setDni(reservaDTO.getPersonaDni());
                nuevaPersona.setNombre(reservaDTO.getPersonaNombre());
                nuevaPersona.setApellido(reservaDTO.getPersonaApellido());
                nuevaPersona.setCorreo(reservaDTO.getPersonaCorreo());

                return personaService.save(nuevaPersona);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar o crear la persona: " + e.getMessage(), e);
        }
    }

    // Método para buscar reservas por DNI de persona
    public Persona findPersonaWithReservasByDni(Long dni) throws Exception {
        Optional<Persona> persona = Optional.ofNullable(personaService.findByDni(dni));
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new Exception("No se encontró ninguna persona con DNI: " + dni);
        }
    }
}
