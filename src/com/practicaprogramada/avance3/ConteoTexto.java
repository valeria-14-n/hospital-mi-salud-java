package com.practicaprogramada.avance3;

/**
 * Guarda un texto y la cantidad de veces que aparecio. Se usa en el
 * modulo de BI para contar diagnosticos y medicamentos. Es lo que se
 * guarda dentro de la lista de conteos.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ConteoTexto {

    private String texto;
    private int cantidad;

    /**
     * Crea un conteo nuevo que arranca en 1.
     *
     * @param texto texto que se esta contando (diagnostico o medicamento)
     */
    public ConteoTexto(String texto) {
        this.texto = texto;
        this.cantidad = 1;
    }

    /**
     * Obtiene el texto que se esta contando.
     *
     * @return el texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Obtiene la cantidad de veces que aparecio el texto.
     *
     * @return la cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Le suma uno a la cantidad.
     */
    public void sumarUno() {
        cantidad = cantidad + 1;
    }

    /**
     * Representacion en texto del conteo.
     *
     * @return cadena con el texto y la cantidad de casos
     */
    @Override
    public String toString() {
        return texto + ": " + cantidad + " casos";
    }
}