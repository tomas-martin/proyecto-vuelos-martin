package com.example.ProyectoFinalMartin.dto;

import com.example.ProyectoFinalMartin.model.TipoTarjeta;

public class ReservaDTO {
    // Datos de la persona
    private Long personaDni;
    private String personaNombre;
    private String personaApellido;
    private String personaCorreo;

    // Datos del pago
    private Double pagoCantidad;

    // Datos de la reserva
    private Long vueloId;

    // Datos de la tarjeta
    private String tarjetaNumero;
    private String tarjetaTipo;

    // Datos adicionales
    private String tipoServicio;

    // Constructores
    public ReservaDTO() {}

    public ReservaDTO(Long personaDni, String personaNombre, String personaApellido,
                      String personaCorreo, Double pagoCantidad,
                      Long vueloId,
                      String tarjetaNumero, String tarjetaTipo, String tipoServicio) {
        this.personaDni = personaDni;
        this.personaNombre = personaNombre;
        this.personaApellido = personaApellido;
        this.personaCorreo = personaCorreo;
        this.pagoCantidad = pagoCantidad;
        this.vueloId = vueloId;
        this.tarjetaNumero = tarjetaNumero;
        this.tarjetaTipo = tarjetaTipo;
        this.tipoServicio = tipoServicio;
    }

    // Getters y Setters
    public Long getPersonaDni() {
        return personaDni;
    }

    public void setPersonaDni(Long personaDni) {
        this.personaDni = personaDni;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getPersonaApellido() {
        return personaApellido;
    }

    public void setPersonaApellido(String personaApellido) {
        this.personaApellido = personaApellido;
    }

    public String getPersonaCorreo() {
        return personaCorreo;
    }

    public void setPersonaCorreo(String personaCorreo) {
        this.personaCorreo = personaCorreo;
    }

    public Double getPagoCantidad() {
        return pagoCantidad;
    }

    public void setPagoCantidad(Double pagoCantidad) {
        this.pagoCantidad = pagoCantidad;
    }

    public Long getVueloId() {
        return vueloId;
    }

    public void setVueloId(Long vueloId) {
        this.vueloId = vueloId;
    }

    public String getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(String tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }

    public TipoTarjeta getTarjetaTipo() {
        if (tarjetaTipo == null) return null;
        try {
            return TipoTarjeta.valueOf(tarjetaTipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Podés loguear el error aquí
            return null; // o lanzar una excepción custom si preferís
        }
    }

    public void setTarjetaTipo(String tarjetaTipo) {
        this.tarjetaTipo = tarjetaTipo;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "personaDni=" + personaDni +
                ", personaNombre='" + personaNombre + '\'' +
                ", personaApellido='" + personaApellido + '\'' +
                ", personaCorreo='" + personaCorreo + '\'' +
                ", pagoCantidad=" + pagoCantidad +
                ", vueloId=" + vueloId +
                ", tarjetaNumero='" + tarjetaNumero + '\'' +
                ", tarjetaTipo='" + tarjetaTipo + '\'' +
                ", tipoServicio='" + tipoServicio + '\'' +
                '}';
    }
}