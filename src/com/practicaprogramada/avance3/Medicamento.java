package com.practicaprogramada.avance3;

import java.util.Date;

/**
 * Registro de un medicamento prescrito dentro del historico de
 * medicamentos de un paciente.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Medicamento {
    private Date fechaPrescripcion;
    private String medicamentoPrescrito;

    /**
     * Crea un medicamento recetado en el momento. La fecha se toma de
     * la hora del sistema.
     *
     * @param medicamentoPrescrito nombre del medicamento recetado
     */
    public Medicamento(String medicamentoPrescrito) {
        this.fechaPrescripcion = new Date();
        this.medicamentoPrescrito = medicamentoPrescrito;
    }

    /**
     * Crea un medicamento con la fecha que ya viene dada. Se usa
     * cuando el medicamento se carga desde el archivo JSON.
     *
     * @param fechaPrescripcion fecha en que se receto el medicamento
     * @param medicamentoPrescrito nombre del medicamento recetado
     */
    public Medicamento(Date fechaPrescripcion, String medicamentoPrescrito) {
        this.fechaPrescripcion = fechaPrescripcion;
        this.medicamentoPrescrito = medicamentoPrescrito;
    }

    public Date getFechaPrescripcion() { return fechaPrescripcion; }
    public String getMedicamentoPrescrito() { return medicamentoPrescrito; }

    @Override
    public String toString() {
        return "Fecha: " + fechaPrescripcion.toString()
                + ", Medicamento: " + medicamentoPrescrito;
    }
}