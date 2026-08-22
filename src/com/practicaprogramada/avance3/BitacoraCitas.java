package com.practicaprogramada.avance3;

/**
 * Bitácora de citas atendidas durante el día. Hereda de la clase
 * ListaSimple vista en clase y la ordena ascendentemente por cédula.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class BitacoraCitas extends ListaSimple {

    @Override
    protected long obtenerClave(Object dato) {
        RegistroBitacora r = (RegistroBitacora) dato;
        return Long.parseLong(r.getCedula());
    }

    public void insertarRegistro(RegistroBitacora registro) {
        this.insertaOrdenado(registro, Long.parseLong(registro.getCedula()));
    }

    public void imprimirBitacora() {
        if (this.estaVacia()) {
            System.out.println("No hay citas atendidas en la sesion.");
            return;
        }
        this.imprimir();
    }
}
