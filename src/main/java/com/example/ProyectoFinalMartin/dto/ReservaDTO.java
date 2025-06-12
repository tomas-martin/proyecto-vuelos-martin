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
    private String pagoNumero;

    // Datos de la reserva
    private String reservaNumero;
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
                      String pagoNumero, String reservaNumero, Long vueloId,
                      String tarjetaNumero, String tarjetaTipo, String tipoServicio) {
        this.personaDni = personaDni;
        this.personaNombre = personaNombre;
        this.personaApellido = personaApellido;
        this.personaCorreo = personaCorreo;
        this.pagoCantidad = pagoCantidad;
        this.pagoNumero = pagoNumero;
        this.reservaNumero = reservaNumero;
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

    public String getPagoNumero() {
        return pagoNumero;
    }

    public void setPagoNumero(String pagoNumero) {
        this.pagoNumero = pagoNumero;
    }

    public String getReservaNumero() {
        return reservaNumero;
    }

    public void setReservaNumero(String reservaNumero) {
        this.reservaNumero = reservaNumero;
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
        return TipoTarjeta.valueOf(tarjetaTipo);
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
                ", pagoNumero='" + pagoNumero + '\'' +
                ", reservaNumero='" + reservaNumero + '\'' +
                ", vueloId=" + vueloId +
                ", tarjetaNumero='" + tarjetaNumero + '\'' +
                ", tarjetaTipo='" + tarjetaTipo + '\'' +
                ", tipoServicio='" + tipoServicio + '\'' +
                '}';
    }
}