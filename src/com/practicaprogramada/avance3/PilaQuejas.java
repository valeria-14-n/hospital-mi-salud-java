package com.practicaprogramada.avance3;

/**
 * Pila de Quejas del hospital. Hereda de la clase Pila vista en
 * clase, sin agregar variables nuevas, únicamente los métodos de
 * conveniencia para trabajar directamente con Quejas.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class PilaQuejas extends Pila {

    public void apilarQueja(Queja queja) {
        this.apilar(queja);
    }

    public Queja desapilarQueja() {
        return (Queja) this.desapilar();
    }

    public void mostrarQuejas() {
        if (this.esVacia()) {
            System.out.println("No hay quejas registradas.");
            return;
        }
        NodoPila aux = this.getCima();
        while (aux != null) {
            Queja q = (Queja) aux.getValor();
            System.out.println(q.toString());
            aux = aux.getAnterior();
        }
    }
}
