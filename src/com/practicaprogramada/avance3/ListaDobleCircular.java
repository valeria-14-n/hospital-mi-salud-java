package com.practicaprogramada.avance3;

/**
 * Lista Doble Circular ordenada ascendentemente vista en clase. Esta
 * es la clase BASE. Las clases del proyecto (ExpedientePacientes)
 * heredan de esta.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ListaDobleCircular {

    private NodoDoble primero;
    private NodoDoble ultimo;

    public ListaDobleCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    public NodoDoble getPrimero() {
        return primero;
    }

    public NodoDoble getUltimo() {
        return ultimo;
    }

    public void setPrimero(NodoDoble primero) {
        this.primero = primero;
    }

    public void setUltimo(NodoDoble ultimo) {
        this.ultimo = ultimo;
    }

    protected long obtenerClave(Object dato) {
        return 0;
    }

    public void insertaOrdenado(Object dato, long clave) {
        NodoDoble nuevoNodo = new NodoDoble(dato);

        if (this.getPrimero() == null) {
            primero = nuevoNodo;
            ultimo = primero;
            ultimo.setSiguiente(primero);
            primero.setAnterior(ultimo);

        } else if (clave <= obtenerClave(primero.getDato())) {
            nuevoNodo.setSiguiente(primero);
            primero.setAnterior(nuevoNodo);
            nuevoNodo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevoNodo);
            primero = nuevoNodo;

        } else if (clave >= obtenerClave(ultimo.getDato())) {
            ultimo.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(primero);
            nuevoNodo.setAnterior(ultimo);
            primero.setAnterior(nuevoNodo);
            ultimo = nuevoNodo;

        } else {
            NodoDoble temp = primero;
            while (obtenerClave(temp.getSiguiente().getDato()) < clave) {
                temp = temp.getSiguiente();
            }
            nuevoNodo.setSiguiente(temp.getSiguiente());
            nuevoNodo.setAnterior(temp);
            nuevoNodo.getSiguiente().setAnterior(nuevoNodo);
            temp.setSiguiente(nuevoNodo);
        }
    }

    public Object buscarPorClave(long clave) {
        if (this.estaVacia())
            return null;

        NodoDoble aux = primero;
        do {
            if (obtenerClave(aux.getDato()) == clave)
                return aux.getDato();
            aux = aux.getSiguiente();
        } while (aux != primero);

        return null;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}
