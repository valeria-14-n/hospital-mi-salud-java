
package com.practicaprogramada.avance3;

/**
 * Arbol Binario de Busqueda visto en clase. Esta es la clase BASE.
 * Las clases del proyecto (ArbolExpedientes) heredan de esta. Solo
 * tiene un puntero a la raiz y trabaja con datos enteros.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ArbolBinario {
 
    private Nodo raiz;
 
    /**
     * Crea un arbol vacio.
     */
    public ArbolBinario() {
        this.raiz = null;
    }
 
    /**
     * Obtiene la raiz del arbol.
     *
     * @return el nodo raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }
 
    /**
     * Asigna la raiz del arbol.
     *
     * @param raiz nuevo nodo raiz
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
 
    /**
     * Metodo wrapper que envuelve el llamado al metodo recursivo de
     * insercion.
     *
     * @param valor valor a insertar en el arbol
     */
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }
 
    /**
     * Metodo recursivo que inserta un valor en el arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param valor valor a insertar
     * @return el nodo actual ya con el nuevo valor amarrado
     */
    private Nodo insertarRec(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return new Nodo(valor);
        } else {
            if (valor < nodoActual.getDato()) {
                Nodo nodoAux = insertarRec(nodoActual.getNodoIzq(), valor);
                nodoActual.setNodoIzq(nodoAux);
            } else if (valor > nodoActual.getDato()) {
                Nodo nodoAux = insertarRec(nodoActual.getNodoDer(), valor);
                nodoActual.setNodoDer(nodoAux);
            }
            return nodoActual;
        }
    }
 
    /**
     * Metodo wrapper del recorrido inOrden.
     */
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }
 
    /**
     * Recorrido inOrden recursivo (izquierda, padre, derecha).
     *
     * @param nodoActual nodo por donde va el recorrido
     */
    private void inOrdenRec(Nodo nodoActual) {
        if (nodoActual != null) {
            inOrdenRec(nodoActual.getNodoIzq());
            System.out.print(nodoActual.getDato() + ", ");
            inOrdenRec(nodoActual.getNodoDer());
        }
    }
 
    /**
     * Metodo wrapper del recorrido preOrden.
     */
    public void preOrden() {
        preOrdenRec(raiz);
        System.out.println();
    }
 
    /**
     * Recorrido preOrden recursivo (padre, izquierda, derecha).
     *
     * @param nodoActual nodo por donde va el recorrido
     */
    private void preOrdenRec(Nodo nodoActual) {
        if (nodoActual != null) {
            System.out.print(nodoActual.getDato() + ", ");
            preOrdenRec(nodoActual.getNodoIzq());
            preOrdenRec(nodoActual.getNodoDer());
        }
    }
 
    /**
     * Metodo wrapper del recorrido postOrden.
     */
    public void postOrden() {
        postOrdenRec(raiz);
        System.out.println();
    }
 
    /**
     * Recorrido postOrden recursivo (izquierda, derecha, padre).
     *
     * @param nodoActual nodo por donde va el recorrido
     */
    private void postOrdenRec(Nodo nodoActual) {
        if (nodoActual != null) {
            postOrdenRec(nodoActual.getNodoIzq());
            postOrdenRec(nodoActual.getNodoDer());
            System.out.print(nodoActual.getDato() + ", ");
        }
    }
 
    /**
     * Metodo wrapper que envuelve el llamado al metodo recursivo de
     * eliminacion.
     *
     * @param valor valor que se desea eliminar
     */
    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }
 
    /**
     * Metodo recursivo que implementa los 3 casos de eliminacion.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param valor valor que se desea eliminar
     * @return el nodo actual ya con la eliminacion aplicada
     */
    private Nodo eliminarRec(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return null;
        }
 
        if (valor < nodoActual.getDato()) {
            nodoActual.setNodoIzq(eliminarRec(nodoActual.getNodoIzq(), valor));
        } else if (valor > nodoActual.getDato()) {
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(), valor));
        } else {
            // Caso 1: nodo sin hijos (hoja).
            if (nodoActual.getNodoIzq() == null && nodoActual.getNodoDer() == null) {
                return null;
            }
            // Caso 2: nodo con un solo hijo.
            if (nodoActual.getNodoIzq() == null) {
                return nodoActual.getNodoDer();
            } else if (nodoActual.getNodoDer() == null) {
                return nodoActual.getNodoIzq();
            }
            // Caso 3: nodo con 2 hijos.
            Nodo sucesor = minValorNodo(nodoActual.getNodoDer());
            nodoActual.setDato(sucesor.getDato());
            nodoActual.setNodoDer(eliminarRec(nodoActual.getNodoDer(), sucesor.getDato()));
        }
        return nodoActual;
    }
 
    /**
     * Metodo miscelaneo que retorna el sucesor inOrden de un nodo.
     *
     * @param nodo nodo desde donde se empieza a buscar
     * @return el nodo con el menor valor del subarbol
     */
    private Nodo minValorNodo(Nodo nodo) {
        while (nodo.getNodoIzq() != null) {
            nodo = nodo.getNodoIzq();
        }
        return nodo;
    }
 
    /**
     * Indica si el arbol esta vacio.
     *
     * @return true si no tiene raiz, false en caso contrario
     */
    public boolean estaVacio() {
        return raiz == null;
    }
 
    /**
     * Metodo wrapper que calcula la altura del arbol.
     *
     * @return la altura del arbol
     */
    public int obtenerAltura() {
        return obtenerAlturaRec(raiz);
    }
 
    /**
     * Metodo recursivo que calcula la altura del arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @return la altura desde el nodo actual
     */
    private int obtenerAlturaRec(Nodo nodoActual) {
        if (nodoActual == null) {
            return -1;
        } else {
            int alturaIzq = obtenerAlturaRec(nodoActual.getNodoIzq());
            int alturaDer = obtenerAlturaRec(nodoActual.getNodoDer());
            if (alturaIzq > alturaDer) {
                return alturaIzq + 1;
            } else {
                return alturaDer + 1;
            }
        }
    }
}