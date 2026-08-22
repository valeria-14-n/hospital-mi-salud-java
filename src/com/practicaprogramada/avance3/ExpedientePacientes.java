package com.practicaprogramada.avance3;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ExpedientePacientes extends ListaDobleCircular {

    private NodoDoble actual; // Puntero de navegación, variable propia de esta subclase.

    @Override
    protected long obtenerClave(Object dato) {
        ExpedientePaciente e = (ExpedientePaciente) dato;
        return Long.parseLong(e.getCedula());
    }

    public void insertarExpediente(ExpedientePaciente expediente) {
        this.insertaOrdenado(expediente, Long.parseLong(expediente.getCedula()));
    }

    public ExpedientePaciente buscarPorCedula(String cedula) {
        return (ExpedientePaciente) this.buscarPorClave(Long.parseLong(cedula));
    }

    public boolean iniciarNavegacion() {
        if (this.estaVacia()) {
            return false;
        }
        actual = this.getPrimero();
        return true;
    }

    public ExpedientePaciente getExpedienteActual() {
        if (actual == null) {
            return null;
        }
        return (ExpedientePaciente) actual.getDato();
    }

    public void siguienteExpediente() {
        if (actual != null) {
            actual = actual.getSiguiente();
        }
    }

    public void anteriorExpediente() {
        if (actual != null) {
            actual = actual.getAnterior();
        }
    }
}
