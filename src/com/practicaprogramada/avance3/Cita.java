package com.practicaprogramada.avance3;

import java.util.Date;

/**
 * Registro de una cita dentro del historico de citas de un paciente.
 * Es lo que se guarda dentro de la Lista Simple Circular.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Cita {
    private Date fecha;
    private String doctor;
    private String diagnostico;

    /**
     * Crea una cita nueva. La fecha se toma de la hora del sistema.
     * Este es el constructor que se usa cuando el doctor atiende al
     * paciente en el momento.
     *
     * @param doctor nombre del doctor que atendio
     * @param diagnostico diagnostico dado en la cita
     */
    public Cita(String doctor, String diagnostico) {
        this.fecha = new Date();
        this.doctor = doctor;
        this.diagnostico = diagnostico;
    }

    /**
     * Crea una cita con una fecha que ya viene dada. Este es el
     * constructor que se usa cuando la cita se carga desde el archivo
     * JSON, porque ahi la fecha ya viene escrita y no es la de hoy.
     *
     * @param fecha fecha en que se dio la cita
     * @param doctor nombre del doctor que atendio
     * @param diagnostico diagnostico dado en la cita
     */
    public Cita(Date fecha, String doctor, String diagnostico) {
        this.fecha = fecha;
        this.doctor = doctor;
        this.diagnostico = diagnostico;
    }

    public Date getFecha() { return fecha; }
    public String getDoctor() { return doctor; }
    public String getDiagnostico() { return diagnostico; }

    @Override
    public String toString() {
        return "Fecha: " + fecha.toString()
                + ", Doctor: " + doctor
                + ", Diagnostico: " + diagnostico;
    }
}