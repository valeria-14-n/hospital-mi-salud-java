package com.practicaprogramada.avance3;

import java.util.Scanner;

/**
 * Clase principal del sistema de gestion de pacientes del hospital
 * "Su Salud" (Presentacion Final). Maneja el menu principal, el
 * submenu de llegada de pacientes, la carga del archivo y el modulo
 * de BI.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class AvanceEstructuraDatos {

    private static GestorPacientes gestor;
    private static Scanner scanner;

    /**
     * Punto de entrada del programa.
     *
     * @param args argumentos de linea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        gestor = new GestorPacientes();
        scanner = new Scanner(System.in);

        mostrarBienvenida();
        menuPrincipal();
    }

    /**
     * Muestra el mensaje de bienvenida al iniciar el sistema.
     */
    private static void mostrarBienvenida() {
        System.out.println("\n===========================================");
        System.out.println("   HOSPITAL SU SALUD - AVANCE 3");
        System.out.println("   Bienvenido al Sistema de Atencion");
        System.out.println("===========================================");
    }

    /**
     * Muestra y controla el menu principal del sistema.
     */
    private static void menuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gestionar Llegada de Pacientes");
            System.out.println("2. Consulta de Bitacora de Citas del Dia");
            System.out.println("3. Consulta de Expediente Unico ");
            System.out.println("4. Cargar Expedientes desde JSON");
            System.out.println("5. Consulta de Expediente Unico ");
            System.out.println("6. Modulo de Inteligencia Empresarial ");
            System.out.println("7. Ayuda");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuGestionPacientes();
                    break;
                case 2:
                    gestor.consultarBitacora();
                    break;
                case 3:
                    gestor.consultarExpedientes(scanner);
                    break;
                case 4:
                    gestor.cargarExpedienteDesdeArchivo(scanner);
                    break;
                case 5:
                    gestor.consultarExpedientesArbol();
                    break;
                case 6:
                    gestor.abrirModuloBI(scanner);
                    break;
                case 7:
                    mostrarAyuda();
                    break;
                case 8:
                    System.out.println("\nGracias por usar el sistema. Hasta luego!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra y controla el submenu de gestion de llegada de
     * pacientes (seleccionar ficha, atender, abandonar cola, etc).
     */
    private static void menuGestionPacientes() {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIONAR LLEGADA DE PACIENTES ---");
            System.out.println("1. Seleccionar Ficha");
            System.out.println("2. Atender Paciente");
            System.out.println("3. Abandonar Cola de Pacientes");
            System.out.println("4. Mostrar Fichas Pendientes");
            System.out.println("5. Mostrar Quejas Recibidas");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    gestor.seleccionarFicha(scanner);
                    break;
                case 2:
                    gestor.atenderPaciente(scanner);
                    break;
                case 3:
                    gestor.abandonarCola(scanner);
                    break;
                case 4:
                    gestor.mostrarFichasPendientes();
                    break;
                case 5:
                    gestor.mostrarQuejas();
                    break;
                case 6:
                    regresar = true;
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra la seccion de ayuda del sistema, con la version
     * actualizada de la Presentacion Final.
     */
    private static void mostrarAyuda() {
        System.out.println("\n--- AYUDA DEL SISTEMA ---");
        System.out.println("Avance 3 - V.3.0.0");
        System.out.println("Desarrollado por: Grupo 4");
        System.out.println("  - Amador Fernandez Kenneth Fabian");
        System.out.println("  - Barboza Chaves Valeria Niobe");
        System.out.println("  - Guzman Martinez Kyle Sebastian");
        System.out.println("  - Soto Salas Brandon Mauricio");
    }

    /**
     * Lee de forma segura la opcion que ingresa el usuario.
     *
     * @return la opcion ingresada, o -1 si hubo un error de formato
     */
    private static int obtenerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}