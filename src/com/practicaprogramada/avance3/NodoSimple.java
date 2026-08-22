package com.practicaprogramada.avance3;

/**
 * Nodo utilizado por la clase ListaSimple. Guarda un dato y una
 * referencia al siguiente nodo de la lista.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoSimple {
    private Object dato;
    private NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() {
        return dato;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
}
