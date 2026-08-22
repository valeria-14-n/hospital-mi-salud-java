package com.practicaprogramada.avance3;

import java.util.Date;

/**
 * Queja generada cuando un paciente abandona la cola sin ser atendido.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Queja {
    private String numeroFicha;
    private String cedula;
    private String nombre;
    private Date fechaHora;
    private String motivo;

    /**
     * Crea una queja. La fecha y hora se calcula automáticamente con
     * la hora del sistema.
     *
     * @param numeroFicha número de ficha del paciente que se retira
     * @param cedula cédula del paciente
     * @param nombre nombre del paciente
     * @param motivo motivo por el que abandona la cola
     */
    public Queja(String numeroFicha, String cedula, String nombre, String motivo) {
        this.numeroFicha = numeroFicha;
        this.cedula = cedula;
        this.nombre = nombre;
        this.motivo = motivo;
        this.fechaHora = new Date();
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
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la fecha y hora del abandono (timestamp).
     *
     * @return la fecha y hora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Obtiene el motivo del abandono.
     *
     * @return el motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Obtiene la fecha y hora en formato de texto (timestamp).
     *
     * @return la fecha formateada
     */
    public String getFechaHoraFormato() {
        return fechaHora.toString();
    }

    /**
     * Representación en texto de la queja.
     *
     * @return cadena con los datos de la queja
     */
    @Override
    public String toString() {
        return "Ficha # " + numeroFicha
                + " con cedula " + cedula
                + " abandona la cola sin ser atendido(a) a la fecha y hora "
                + getFechaHoraFormato()
                + " por el siguiente motivo " + motivo;
    }
}
