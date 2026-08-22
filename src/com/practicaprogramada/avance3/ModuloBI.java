package com.practicaprogramada.avance3;

import java.util.Scanner;

/**
 * Modulo de Inteligencia Empresarial (BI) del Avance 3. Se encarga
 * del menu, de pedir los datos al usuario y de mostrar los reportes
 * en pantalla. Los recorridos del arbol NO se hacen aqui: se le
 * piden a la clase ArbolExpedientes, que es la que hereda de
 * ArbolBinario.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ModuloBI {

    private ArbolExpedientes arbol;

    /**
     * Crea el modulo de BI amarrado al arbol de expedientes.
     *
     * @param arbol arbol del expediente unico de pacientes
     */
    public ModuloBI(ArbolExpedientes arbol) {
        this.arbol = arbol;
    }

    /**
     * Muestra y controla el menu del modulo de BI.
     *
     * @param sc Scanner para leer la opcion del usuario
     */
    public void menuBI(Scanner sc) {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- MODULO DE INTELIGENCIA EMPRESARIAL (BI) ---");
            System.out.println("1. Analisis de enfermedades mas frecuentes");
            System.out.println("2. Segmentacion de pacientes");
            System.out.println("3. Deteccion de patrones");
            System.out.println("4. Propuesta de Valor");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una opcion: ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
                opcion = -1;
            }

            if (arbol.estaVacio() && opcion >= 1 && opcion <= 4) {
                System.out.println("\nNo hay expedientes cargados. Primero cargue el archivo.");
            } else {
                switch (opcion) {
                    case 1:
                        enfermedadesMasFrecuentes();
                        break;
                    case 2:
                        segmentacionPacientes();
                        break;
                    case 3:
                        deteccionPatrones(sc);
                        break;
                    case 4:
                        propuestaDeValor();
                        break;
                    case 5:
                        regresar = true;
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
            }
        }
    }

    /**
     * Opcion 1 del BI. Le pide al arbol el conteo de diagnosticos y
     * lo muestra ordenado del que mas se repite al que menos.
     */
    public void enfermedadesMasFrecuentes() {
        ListaConteos conteos = arbol.contarDiagnosticos();

        System.out.println("\n--- Enfermedades mas frecuentes ---");
        conteos.imprimirOrdenadoPorCantidad();
    }

    /**
     * Opcion 2 del BI. Totaliza los pacientes en menores de edad,
     * adultos y adultos mayores.
     */
    public void segmentacionPacientes() {
        int menores = arbol.contarPorRangoEdad(0, 17);
        int adultos = arbol.contarPorRangoEdad(18, 64);
        int adultosMayores = arbol.contarPorRangoEdad(65, 200);

        System.out.println("\n--- Segmentacion de pacientes ---");
        System.out.println("  Menores de Edad: " + menores + " pacientes.");
        System.out.println("  Adultos: " + adultos + " pacientes.");
        System.out.println("  Adultos Mayores: " + adultosMayores + " pacientes.");
    }

    /**
     * Opcion 3 del BI. Consulta avanzada donde el usuario arma su
     * propio reporte con hasta 5 parametros. Puede dejar parametros
     * vacios pero no todos.
     *
     * @param sc Scanner para leer los parametros del usuario
     */
    public void deteccionPatrones(Scanner sc) {
        System.out.println("\n--- Deteccion de patrones ---");
        System.out.println("Deje el parametro vacio (enter) si no lo quiere usar.");

        System.out.print("Edad inicial: ");
        String edadInicialTexto = sc.nextLine().trim();

        System.out.print("Edad final: ");
        String edadFinalTexto = sc.nextLine().trim();

        System.out.print("Diagnostico: ");
        String diagnostico = sc.nextLine().trim();

        System.out.print("Genero: ");
        String genero = sc.nextLine().trim();

        System.out.print("Medicamento: ");
        String medicamento = sc.nextLine().trim();

        if (edadInicialTexto.equals("") && edadFinalTexto.equals("")
                && diagnostico.equals("") && genero.equals("") && medicamento.equals("")) {
            System.out.println("\nDebe indicar al menos un parametro de busqueda.");
            return;
        }

        int edadInicial = 0;
        int edadFinal = 200;

        try {
            if (!edadInicialTexto.equals("")) {
                edadInicial = Integer.parseInt(edadInicialTexto);
            }
            if (!edadFinalTexto.equals("")) {
                edadFinal = Integer.parseInt(edadFinalTexto);
            }
        } catch (Exception e) {
            System.out.println("\nLa edad debe ser un numero. Intente de nuevo.");
            return;
        }

        int encontrados = arbol.contarPorPatron(edadInicial, edadFinal,
                diagnostico, genero, medicamento);

        System.out.println("\n===== FICHA DE CONSULTA =====");
        System.out.println("Parametros de entrada:");
        if (!edadInicialTexto.equals("")) {
            System.out.println("  Edad inicial: " + edadInicial);
        }
        if (!edadFinalTexto.equals("")) {
            System.out.println("  Edad final: " + edadFinal);
        }
        if (!diagnostico.equals("")) {
            System.out.println("  Diagnostico: " + diagnostico);
        }
        if (!genero.equals("")) {
            System.out.println("  Genero: " + genero);
        }
        if (!medicamento.equals("")) {
            System.out.println("  Medicamento: " + medicamento);
        }
        System.out.println("Resultado de la busqueda: " + encontrados + " pacientes encontrados.");
        System.out.println("=============================");
    }

    /**
     * Opcion 4 del BI (Propuesta de Valor del grupo). Muestra cuales
     * son los medicamentos que mas se estan recetando en el hospital,
     * para que la administracion sepa cuales debe tener siempre en
     * inventario en la farmacia.
     */
    public void propuestaDeValor() {
        ListaConteos conteos = arbol.contarMedicamentos();

        System.out.println("\n--- Propuesta de Valor: Medicamentos mas recetados ---");
        System.out.println("Sirve para saber que medicamentos debe mantener la farmacia en inventario.");
        conteos.imprimirOrdenadoPorCantidad();
        System.out.println("Total de expedientes analizados: " + arbol.contarExpedientes());
    }
}