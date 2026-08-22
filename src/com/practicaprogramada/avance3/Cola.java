package com.practicaprogramada.avance3;

/**
 * Clase Cola (FIFO) vista en clase. Esta es la clase BASE.
 * Las clases del proyecto (por ejemplo ColaPacientes) heredan de esta
 * y le agregan las variables o métodos que necesiten.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Cola {

    private NodoCola frente;
    private NodoCola fin;

    public Cola() {
        this.frente = this.fin = null;
    }

    public void setFrente(NodoCola frente) {
        this.frente = frente;
    }

    public void setFin(NodoCola fin) {
        this.fin = fin;
    }

    public NodoCola getFrente() {
        return frente;
    }

    public NodoCola getFin() {
        return fin;
    }

    public void encolar(Object dato) {
        NodoCola nuevo = new NodoCola(dato);
        if (this.estaVacia()) {
            this.frente = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        this.fin = nuevo;
    }

    public Object desencolar() throws Exception {
        if (this.estaVacia()) {
            throw new Exception("La Cola está vacía");
        }
        Object aux = frente.getDato();
        this.frente = frente.getSiguiente();

        if (estaVacia())
            fin = null;

        return aux;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void imprimeCola() {
        NodoCola aux = frente;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }
}
