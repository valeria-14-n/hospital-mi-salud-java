package com.practicaprogramada.avance3;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ExpedientePaciente {
    private String cedula;
    private String nombre;
    private int edad;
    private String genero;
    private ListaCircular historicoCitas;
    private ListaCircular historicoMedicamentos;

    /**
     * Crea un expediente nuevo para un paciente.
     *
     * @param cedula cédula del paciente
     * @param nombre nombre del paciente
     * @param edad edad del paciente
     * @param genero género del paciente
     */
    public ExpedientePaciente(String cedula, String nombre, int edad, String genero) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.historicoCitas = new ListaCircular();
        this.historicoMedicamentos = new ListaCircular();
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
     * Obtiene la edad del paciente.
     *
     * @return la edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Asigna la edad del paciente.
     *
     * @param edad nueva edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el género del paciente.
     *
     * @return el género
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Asigna el género del paciente.
     *
     * @param genero nuevo género
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el histórico de citas del paciente.
     *
     * @return el histórico de citas
     */
    public ListaCircular getHistoricoCitas() {
        return historicoCitas;
    }

    /**
     * Obtiene el histórico de medicamentos del paciente.
     *
     * @return el histórico de medicamentos
     */
    public ListaCircular getHistoricoMedicamentos() {
        return historicoMedicamentos;
    }

    /**
     * Representación en texto de la información plana del expediente.
     *
     * @return cadena con cédula, nombre, edad y género
     */
    @Override
    public String toString() {
        return "Cedula: " + cedula
                + ", Nombre: " + nombre
                + ", Edad: " + edad
                + ", Genero: " + genero;
    }
}
