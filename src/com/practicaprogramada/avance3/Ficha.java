package com.practicaprogramada.avance3;

/**
 * Ficha de atención generada al llegar el paciente a emergencias.
 * Es lo que se guarda dentro de la Cola de pacientes.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Ficha {
    private String numeroFicha;
    private String tipoFicha;
    private Paciente paciente;

    /**
     * Crea una ficha para un paciente.
     *
     * @param numeroFicha número consecutivo de la ficha (ej: R1, P2)
     * @param tipoFicha "Regular" o "Preferencial"
     * @param paciente paciente asociado a la ficha
     */
    public Ficha(String numeroFicha, String tipoFicha, Paciente paciente) {
        this.numeroFicha = numeroFicha;
        this.tipoFicha = tipoFicha;
        this.paciente = paciente;
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
     * Asigna el número de ficha.
     *
     * @param numeroFicha nuevo número de ficha
     */
    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    /**
     * Obtiene el tipo de ficha.
     *
     * @return "Regular" o "Preferencial"
     */
    public String getTipoFicha() {
        return tipoFicha;
    }

    /**
     * Asigna el tipo de ficha.
     *
     * @param tipoFicha nuevo tipo de ficha
     */
    public void setTipoFicha(String tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    /**
     * Obtiene el paciente asociado a la ficha.
     *
     * @return el paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Asigna el paciente asociado a la ficha.
     *
     * @param paciente nuevo paciente
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Retorna el distintivo visual según el tipo de ficha.
     *
     * @return "[R - verde]" o "[P - naranja]"
     */
    public String getDistintivo() {
        if (tipoFicha.equals("Regular"))
            return "[R - verde]";
        else
            return "[P - naranja]";
    }

    /**
     * Representación en texto de la ficha.
     *
     * @return cadena con los datos de la ficha
     */
    @Override
    public String toString() {
        return "Ficha: " + numeroFicha
                + ", Cedula: " + paciente.getCedula()
                + ", Nombre: " + paciente.getNombre()
                + ", Llegada: " + paciente.getFechaLlegadaFormato();
    }
}
