package com.practicaprogramada.avance3;

/**
 * Nodo utilizado por la clase Pila. Guarda un valor y una referencia
 * al nodo anterior (el que está debajo en la pila). 
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class NodoPila {
    private Object valor;
    private NodoPila anterior;

    public NodoPila() {
    }

    public NodoPila(Object valor) {
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }

    public NodoPila getAnterior() {
        return anterior;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setAnterior(NodoPila anterior) {
        this.anterior = anterior;
    }
}
