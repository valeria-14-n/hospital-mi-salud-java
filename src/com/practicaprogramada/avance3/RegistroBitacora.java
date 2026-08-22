package com.practicaprogramada.avance3;

import java.util.Date;

/**
 * Registro de una cita atendida durante el día. Es lo que se guarda
 * dentro de la Lista Simple (BitacoraCitas).
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class RegistroBitacora {
    private String numeroFicha;
    private String cedula;
    private String nombrePaciente;
    private Date fechaLlegada;
    private Date fechaAtencion;

    /**
     * Crea un registro de bitácora.
     *
     * @param numeroFicha número de ficha del paciente atendido
     * @param cedula cédula del paciente
     * @param nombrePaciente nombre del paciente
     * @param fechaLlegada fecha y hora en que el paciente sacó ficha
     * @param fechaAtencion fecha y hora en que fue atendido
     */
    public RegistroBitacora(String numeroFicha, String cedula, String nombrePaciente,
            Date fechaLlegada, Date fechaAtencion) {
        this.numeroFicha = numeroFicha;
        this.cedula = cedula;
        this.nombrePaciente = nombrePaciente;
        this.fechaLlegada = fechaLlegada;
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * Obtiene el número de ficha.
     *
     * @return el número de ficha
     */
    public String getNumeroFicha() {
        return numeroFicha;
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
     * Obtiene el nombre del paciente.
     *
     * @return el nombre
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Obtiene la fecha de llegada (timestamp).
     *
     * @return la fecha de llegada
     */
    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Obtiene la fecha de atención (timestamp).
     *
     * @return la fecha de atención
     */
    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * Calcula el tiempo de espera del paciente, en segundos, entre la
     * llegada y la atención.
     *
     * @return el tiempo de espera en segundos
     */
    public long getTiempoEsperaSegundos() {
        return (fechaAtencion.getTime() - fechaLlegada.getTime()) / 1000;
    }

    /**
     * Retorna el distintivo de color según el tiempo de espera:
     * de 1 a 30 segundos verde, de 30 a 60 segundos amarillo,
     * más de 60 segundos rojo.
     *
     * @return el distintivo de color
     */
    public String getDistintivoColor() {
        long segundos = getTiempoEsperaSegundos();
        if (segundos <= 30)
            return "[VERDE]";
        else if (segundos <= 60)
            return "[AMARILLO]";
        else
            return "[ROJO]";
    }

    /**
     * Representación en texto del registro de bitácora.
     *
     * @return cadena con los datos del registro
     */
    @Override
    public String toString() {
        return getDistintivoColor() + " Ficha # " + numeroFicha
                + ", Cedula: " + cedula
                + ", Nombre: " + nombrePaciente
                + ", Llegada: " + fechaLlegada.toString()
                + ", Atencion: " + fechaAtencion.toString()
                + ", Espera: " + getTiempoEsperaSegundos() + " seg.";
    }
}
