package com.practicaprogramada.avance3;

/**
 * Lista donde se van acumulando los conteos del modulo de BI. Hereda
 * de la clase ListaSimple vista en clase y le agrega el metodo para
 * registrar un texto: si ya existe le suma uno y si no existe lo
 * agrega a la lista.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ListaConteos extends ListaSimple {

    /**
     * La clave de esta lista es la cantidad de veces que aparecio el
     * texto, asi la lista queda ordenada por cantidad.
     *
     * @param dato conteo guardado en el nodo
     * @return la cantidad del conteo
     */
    @Override
    protected long obtenerClave(Object dato) {
        ConteoTexto c = (ConteoTexto) dato;
        return c.getCantidad();
    }

    /**
     * Registra un texto en la lista de conteos.
     *
     * @param texto diagnostico o medicamento que se quiere contar
     */
    public void registrar(String texto) {
        NodoSimple aux = this.getPrimero();

        while (aux != null) {
            ConteoTexto c = (ConteoTexto) aux.getDato();
            if (c.getTexto().equalsIgnoreCase(texto)) {
                c.sumarUno();
                return;
            }
            aux = aux.getSiguiente();
        }

        // Si no lo encontro, entonces es la primera vez que aparece y
        // entra con cantidad 1.
        ConteoTexto nuevo = new ConteoTexto(texto);
        this.insertaOrdenado(nuevo, nuevo.getCantidad());
    }

    /**
     * Imprime los conteos de mayor a menor cantidad, o sea el que mas
     * se repite primero.
     */
    public void imprimirOrdenadoPorCantidad() {
        if (this.estaVacia()) {
            System.out.println("No hay informacion para mostrar.");
            return;
        }

        int mayorImpreso = -1;
        boolean quedan = true;

        while (quedan) {
            // Busco cual es la cantidad mas alta que todavia no he impreso.
            int cantidadActual = -1;
            NodoSimple aux = this.getPrimero();
            while (aux != null) {
                ConteoTexto c = (ConteoTexto) aux.getDato();
                if ((mayorImpreso == -1 || c.getCantidad() < mayorImpreso)
                        && c.getCantidad() > cantidadActual) {
                    cantidadActual = c.getCantidad();
                }
                aux = aux.getSiguiente();
            }

            if (cantidadActual == -1) {
                quedan = false;
            } else {
                // Imprimo todos los que tengan esa cantidad.
                aux = this.getPrimero();
                while (aux != null) {
                    ConteoTexto c = (ConteoTexto) aux.getDato();
                    if (c.getCantidad() == cantidadActual) {
                        System.out.println("  " + c.toString());
                    }
                    aux = aux.getSiguiente();
                }
                mayorImpreso = cantidadActual;
            }
        }
    }
}