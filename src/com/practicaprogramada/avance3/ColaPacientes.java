package com.practicaprogramada.avance3;

/**
 * Cola de pacientes del hospital. Hereda de la clase Cola vista en
 * clase y le agrega los metodos que el proyecto necesita para
 * trabajar con Fichas.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ColaPacientes extends Cola {

    /**
     * Mete una ficha al final de la cola.
     *
     * @param ficha ficha del paciente que llega
     */
    public void encolarFicha(Ficha ficha) {
        this.encolar(ficha);
    }

    /**
     * Saca la ficha que va de primera en la cola.
     *
     * @return la ficha que sale de la cola
     * @throws Exception si la cola esta vacia
     */
    public Ficha desencolarFicha() throws Exception {
        return (Ficha) this.desencolar();
    }

    /**
     * Imprime las fichas pendientes con su distintivo de color, sin
     * mover el frente de la cola.
     */
    public void imprimeFichas() {
        NodoCola aux = this.getFrente();
        while (aux != null) {
            Ficha f = (Ficha) aux.getDato();
            System.out.println(f.getDistintivo() + " " + f.toString());
            aux = aux.getSiguiente();
        }
    }

    /**
     * Busca una ficha por su numero y la saca de la cola dejando el
     * resto de la cola igual. Este metodo vive aqui y no en el gestor
     * porque es el que anda moviendo los punteros de la Cola, y esos
     * solo se deben tocar desde una clase que herede de Cola.
     *
     * @param numeroFicha numero de la ficha que se quiere sacar
     * @return la ficha que se saco, o null si no estaba en la cola
     */
    public Ficha eliminarPorFicha(String numeroFicha) {
        NodoCola actual = this.getFrente();
        NodoCola anterior = null;

        while (actual != null) {
            Ficha f = (Ficha) actual.getDato();

            if (f.getNumeroFicha().equals(numeroFicha)) {
                // Si era la primera, el frente pasa a ser la siguiente.
                if (anterior == null) {
                    this.setFrente(actual.getSiguiente());
                    if (this.getFrente() == null) {
                        this.setFin(null);
                    }
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                    // Si era la ultima, el fin pasa a ser la anterior.
                    if (actual.getSiguiente() == null) {
                        this.setFin(anterior);
                    }
                }
                return f;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return null;
    }
}