package com.example.ProyectoFinalMartin.dto;

public class PersonaDTO {
    private Long dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String numeroPiloto;

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroPiloto() {
        return numeroPiloto;
    }

    public void setNumeroPiloto(String numeroPiloto) {
        this.numeroPiloto = numeroPiloto;
    }
}

