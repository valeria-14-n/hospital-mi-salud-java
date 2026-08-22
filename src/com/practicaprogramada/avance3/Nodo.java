package com.practicaprogramada.avance3;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Nodo {

    private int dato;
    private Nodo nodoIzq;
    private Nodo nodoDer;

    /**
     * Crea un nodo con el dato indicado y los punteros en nulo.
     *
     * @param dato valor entero que se guarda en el nodo
     */
    public Nodo(int dato) {
        this.dato = dato;
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    /**
     * Obtiene el dato del nodo.
     *
     * @return el dato del nodo
     */
    public int getDato() {
        return dato;
    }

    /**
     * Obtiene el hijo de la izquierda.
     *
     * @return el nodo izquierdo
     */
    public Nodo getNodoIzq() {
        return nodoIzq;
    }

    /**
     * Obtiene el hijo de la derecha.
     *
     * @return el nodo derecho
     */
    public Nodo getNodoDer() {
        return nodoDer;
    }

    /**
     * Asigna el dato del nodo.
     *
     * @param dato nuevo dato
     */
    public void setDato(int dato) {
        this.dato = dato;
    }

    /**
     * Asigna el hijo de la izquierda.
     *
     * @param nodoIzq nuevo nodo izquierdo
     */
    public void setNodoIzq(Nodo nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    /**
     * Asigna el hijo de la derecha.
     *
     * @param nodoDer nuevo nodo derecho
     */
    public void setNodoDer(Nodo nodoDer) {
        this.nodoDer = nodoDer;
    }
}