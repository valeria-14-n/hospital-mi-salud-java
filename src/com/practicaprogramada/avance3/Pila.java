package com.practicaprogramada.avance3;

/**
 * Clase Pila (LIFO) vista en clase. Esta es la clase BASE.
 * Las clases del proyecto (por ejemplo PilaQuejas) heredan de esta y
 * le agregan las variables o métodos que necesiten.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class Pila {

    private NodoPila cima;

    public Pila() {
    }

    public void setCima(NodoPila cima) {
        this.cima = cima;
    }

    public NodoPila getCima() {
        return cima;
    }

    public void apilar(Object valor) {
        NodoPila miNodo = new NodoPila();
        miNodo.setValor(valor);

        if (esVacia()) {
            cima = miNodo;
        } else {
            miNodo.setAnterior(cima);
            cima = miNodo;
        }
    }

    public Object desapilar() {
        if (esVacia()) {
            return null;
        } else {
            NodoPila cimaAnterior = cima;
            cima = cima.getAnterior();
            return cimaAnterior.getValor();
        }
    }

    public Object devuelveCima() {
        if (esVacia()) {
            System.out.println("La Pila está Vacía");
            return null;
        } else {
            return cima.getValor();
        }
    }

    public boolean esVacia() {
        return cima == null;
    }

    public void imprimePila() {
        NodoPila aux = cima;
        while (aux != null) {
            System.out.println(aux.getValor());
            aux = aux.getAnterior();
        }
    }

    public int retornarTamanio() {
        NodoPila aux = cima;
        int tamanio = 0;
        while (aux != null) {
            tamanio++;
            aux = aux.getAnterior();
        }
        return tamanio;
    }
}
