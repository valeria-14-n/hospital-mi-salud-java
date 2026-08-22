package com.practicaprogramada.avance3;

/**
 * Nodo utilizado por la clase ListaCircular. Guarda un dato y una
 * referencia al siguiente nodo (el último apunta de vuelta al primero).
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoCircular {
    private Object dato;
    private NodoCircular siguiente;

    public NodoCircular(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() {
        return dato;
    }

    public NodoCircular getSiguiente() {
        return siguiente;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoCircular siguiente) {
        this.siguiente = siguiente;
    }
}
