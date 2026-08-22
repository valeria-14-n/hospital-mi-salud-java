package com.practicaprogramada.avance3;

/**
 * Nodo utilizado por la clase Cola. Guarda un dato y una referencia
 * al siguiente nodo de la cola.  
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoCola {
    private Object dato;
    private NodoCola siguiente;

    public NodoCola() {
    }

    public NodoCola(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public NodoCola getSiguiente() {
        return siguiente;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}
