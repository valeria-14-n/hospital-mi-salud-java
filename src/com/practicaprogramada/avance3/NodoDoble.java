package com.practicaprogramada.avance3;

/**
 * Nodo utilizado por la clase ListaDobleCircular. Guarda un dato y
 * referencias tanto al nodo anterior como al siguiente.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoDoble {
    private Object dato;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    public NodoDoble(Object dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public Object getDato() {
        return dato;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}
