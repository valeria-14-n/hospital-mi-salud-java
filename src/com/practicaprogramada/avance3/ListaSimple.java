package com.practicaprogramada.avance3;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ListaSimple {

    private NodoSimple primero;

    public ListaSimple() {
        this.primero = null;
    }

    public NodoSimple getPrimero() {
        return primero;
    }

    public void setPrimero(NodoSimple primero) {
        this.primero = primero;
    }

    public void insertaOrdenado(Object dato, long clave) {
        NodoSimple nuevoNodo = new NodoSimple(dato);

        if (this.getPrimero() == null) {
            primero = nuevoNodo;
        } else if (clave <= obtenerClave(primero.getDato())) {
            nuevoNodo.setSiguiente(primero);
            primero = nuevoNodo;
        } else {
            NodoSimple aux = primero;
            while (aux.getSiguiente() != null
                    && obtenerClave(aux.getSiguiente().getDato()) < clave) {
                aux = aux.getSiguiente();
            }
            nuevoNodo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevoNodo);
        }
    }

    protected long obtenerClave(Object dato) {
        return 0;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public void imprimir() {
        NodoSimple aux = primero;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }
}
