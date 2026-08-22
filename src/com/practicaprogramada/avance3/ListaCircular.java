package com.practicaprogramada.avance3;

/**
 * Lista Simple Circular vista en clase. Esta es la clase BASE.
 * Las clases del proyecto (HistoricoCitas, HistoricoMedicamentos)
 * heredan de esta. Los elementos se insertan siempre al final,
 * manteniendo el orden cronológico en que se registran.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ListaCircular {

    private NodoCircular primero;
    private NodoCircular ultimo;

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    public NodoCircular getPrimero() {
        return primero;
    }

    public NodoCircular getUltimo() {
        return ultimo;
    }

    public void setPrimero(NodoCircular primero) {
        this.primero = primero;
    }

    public void setUltimo(NodoCircular ultimo) {
        this.ultimo = ultimo;
    }

    public void insertarAlFinal(Object dato) {
        NodoCircular nuevoNodo = new NodoCircular(dato);

        if (this.getPrimero() == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
            ultimo.setSiguiente(primero);
        } else {
            ultimo.setSiguiente(nuevoNodo);
            ultimo = nuevoNodo;
            ultimo.setSiguiente(primero);
        }
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void imprimir() {
        if (estaVacia()) {
            return;
        }
        NodoCircular aux = primero;
        do {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        } while (aux != primero);
    }
}
