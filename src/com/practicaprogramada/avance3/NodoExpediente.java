package com.practicaprogramada.avance3;

/**
 * Nodo del arbol de expedientes. 
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoExpediente extends Nodo {

    private ExpedientePaciente expediente;

    /**
     * Crea un nodo del arbol de expedientes.
     *
     * @param cedula cedula del paciente, se usa como llave del arbol
     * @param expediente expediente completo del paciente
     */
    public NodoExpediente(int cedula, ExpedientePaciente expediente) {
        super(cedula);
        this.expediente = expediente;
    }

    /**
     * Obtiene el expediente guardado en el nodo.
     *
     * @return el expediente del paciente
     */
    public ExpedientePaciente getExpediente() {
        return expediente;
    }

    /**
     * Asigna el expediente del nodo.
     *
     * @param expediente nuevo expediente
     */
    public void setExpediente(ExpedientePaciente expediente) {
        this.expediente = expediente;
    }
}