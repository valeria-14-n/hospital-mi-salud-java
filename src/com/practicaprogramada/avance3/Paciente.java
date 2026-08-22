package com.practicaprogramada.avance3;

import java.util.Date;

/**
 * Datos mínimos de un paciente cuando saca ficha (llegada a emergencias).
 * Los datos completos (edad, género, histórico) se guardan en
 * ExpedientePaciente una vez que el doctor lo atiende.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Paciente {
    private String cedula;
    private String nombre;
    private Date fechaLlegada;

    /**
     * Crea un paciente con su cédula y nombre. La fecha de llegada
     * se calcula automáticamente con la hora del sistema.
     *
     * @param cedula número de cédula del paciente
     * @param nombre nombre completo del paciente
     */
    public Paciente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaLlegada = new Date();
    }

    /**
     * Obtiene la cédula del paciente.
     *
     * @return la cédula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Asigna la cédula del paciente.
     *
     * @param cedula nueva cédula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el nombre del paciente.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del paciente.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha y hora de llegada (timestamp).
     *
     * @return la fecha de llegada
     */
    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Obtiene la fecha de llegada en formato de texto (timestamp).
     *
     * @return la fecha de llegada como texto
     */
    public String getFechaLlegadaFormato() {
        return fechaLlegada.toString();
    }
}
