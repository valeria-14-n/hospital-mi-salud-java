package com.practicaprogramada.avance3;

import java.util.Scanner;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class GestorPacientes {
    private ColaPacientes colaRegular;
    private ColaPacientes colaPreferencial;
    private PilaQuejas pilaQuejas;
    private ExpedientePacientes expedientes;
    private BitacoraCitas bitacora;
    private ArbolExpedientes arbolExpedientes; // Avance 3: expediente unico con ABB.
    private ModuloBI moduloBI;                 // Avance 3: modulo de inteligencia empresarial.
    private String rutaJsonPorDefecto;         // Avance 3: ruta del archivo que dio el profe.

    private int contadorRegular;
    private int contadorPreferencial;
    private int preferencialesSeguidos; // Cuenta preferenciales atendidos consecutivamente.

    /**
     * Crea el gestor de pacientes con todas sus estructuras vacias.
     */
    public GestorPacientes() {
        this.colaRegular = new ColaPacientes();
        this.colaPreferencial = new ColaPacientes();
        this.pilaQuejas = new PilaQuejas();
        this.expedientes = new ExpedientePacientes();
        this.bitacora = new BitacoraCitas();
        this.arbolExpedientes = new ArbolExpedientes();
        this.moduloBI = new ModuloBI(this.arbolExpedientes);
        this.rutaJsonPorDefecto = "pacientes.json";
        this.contadorRegular = 0;
        this.contadorPreferencial = 0;
        this.preferencialesSeguidos = 0;
    }

    /**
     * Opcion "Seleccionar Ficha". Pide los datos del paciente y genera
     * la ficha correspondiente (Regular o Preferencial).
     *
     * @param sc Scanner para leer los datos del usuario
     */
    public void seleccionarFicha(Scanner sc) {
        System.out.println("\n--- Seleccionar Ficha ---");
        System.out.println("1. Paciente Regular");
        System.out.println("2. Paciente Preferencial");
        System.out.print("Seleccione tipo de paciente: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese numero de cedula: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese nombre del paciente: ");
        String nombre = sc.nextLine();

        Paciente paciente = new Paciente(cedula, nombre);

        if (tipo == 1) {
            contadorRegular++;
            String numeroFicha = "R" + contadorRegular;
            Ficha ficha = new Ficha(numeroFicha, "Regular", paciente);
            colaRegular.encolarFicha(ficha);
            System.out.println("\nSu numero de ficha es la: " + numeroFicha);
        } else if (tipo == 2) {
            contadorPreferencial++;
            String numeroFicha = "P" + contadorPreferencial;
            Ficha ficha = new Ficha(numeroFicha, "Preferencial", paciente);
            colaPreferencial.encolarFicha(ficha);
            System.out.println("\nSu numero de ficha es la: " + numeroFicha);
        } else {
            System.out.println("Opcion invalida.");
        }
    }

    /**
     * Opcion "Atender Paciente" (Avance 2). Saca la ficha
     * correspondiente de la cola (cada 2 preferenciales se atiende 1
     * regular), muestra los datos al doctor, consulta o crea el
     * expediente unico del paciente, registra la cita y los
     * medicamentos prescritos, y alimenta la bitacora del dia.
     *
     * @param sc Scanner para leer los datos que ingresa el doctor
     */
    public void atenderPaciente(Scanner sc) {
        try {
            Ficha fichaAtendida = null;

            if (preferencialesSeguidos < 2 && !colaPreferencial.estaVacia()) {
                fichaAtendida = colaPreferencial.desencolarFicha();
                preferencialesSeguidos++;
            } else if (!colaRegular.estaVacia()) {
                fichaAtendida = colaRegular.desencolarFicha();
                preferencialesSeguidos = 0;
            } else if (!colaPreferencial.estaVacia()) {
                fichaAtendida = colaPreferencial.desencolarFicha();
                preferencialesSeguidos++;
            } else {
                System.out.println("No hay pacientes en cola.");
                return;
            }

            Paciente p = fichaAtendida.getPaciente();

            // a. Mostrarle al doctor la pantalla con los datos de la ficha.
            System.out.println("\n--- Datos de la Ficha ---");
            System.out.println(fichaAtendida.toString());

            // b/c. Consultar si el paciente ya existe en el expediente unico.
            ExpedientePaciente expediente = expedientes.buscarPorCedula(p.getCedula());

            if (expediente == null) {
                // b. Paciente nuevo.
                System.out.println("\nPaciente " + p.getNombre() + " asiste a consulta por primera vez.");

                System.out.print("Ingrese la edad del paciente: ");
                int edad = sc.nextInt();
                sc.nextLine();

                System.out.print("Ingrese el genero del paciente (M/F): ");
                String genero = sc.nextLine();

                expediente = new ExpedientePaciente(p.getCedula(), p.getNombre(), edad, genero);
                expedientes.insertarExpediente(expediente);
                // Avance 3: el mismo expediente tambien se guarda en el ABB.
                arbolExpedientes.insertarExpediente(expediente);

            } else {
                // c. Paciente ya existente, se muestran sus datos.
                System.out.println("\n--- Expediente del Paciente ---");
                System.out.println(expediente.toString());
            }

            // d. El doctor ingresa los datos de la cita actual y los medicamentos.
            System.out.print("\nIngrese el nombre del doctor que atiende: ");
            String doctor = sc.nextLine();

            System.out.print("Ingrese el diagnostico: ");
            String diagnostico = sc.nextLine();

            Cita cita = new Cita(doctor, diagnostico);
            expediente.getHistoricoCitas().insertarAlFinal(cita);

            System.out.print("Cuantos medicamentos se le van a prescribir? ");
            int cantidadMedicamentos = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < cantidadMedicamentos; i++) {
                System.out.print("Medicamento " + (i + 1) + ": ");
                String nombreMedicamento = sc.nextLine();
                Medicamento medicamento = new Medicamento(nombreMedicamento);
                expediente.getHistoricoMedicamentos().insertarAlFinal(medicamento);
            }

            // e-ii. Alimentar la bitacora de citas del dia.
            RegistroBitacora registro = new RegistroBitacora(
                    fichaAtendida.getNumeroFicha(),
                    p.getCedula(),
                    p.getNombre(),
                    p.getFechaLlegada(),
                    cita.getFecha());
            bitacora.insertarRegistro(registro);

            // g. Mensaje final.
            System.out.println("\nPaciente " + p.getNombre() + ", su cita ha concluido.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Opcion "Abandonar Cola de Pacientes". Le pide a la cola que saque
     * la ficha y registra la queja correspondiente. El gestor ya no
     * anda moviendo los punteros de la cola, eso lo hace la propia
     * clase ColaPacientes.
     *
     * @param sc Scanner para leer el numero de ficha y el motivo
     */
    public void abandonarCola(Scanner sc) {
        System.out.println("\n--- Abandonar Cola ---");
        System.out.print("Ingrese el numero de ficha: ");
        String numeroFicha = sc.nextLine().trim().toUpperCase();

        System.out.print("Ingrese el motivo de su salida: ");
        String motivo = sc.nextLine();

        Ficha fichaSacada = colaRegular.eliminarPorFicha(numeroFicha);

        if (fichaSacada == null) {
            fichaSacada = colaPreferencial.eliminarPorFicha(numeroFicha);
        }

        if (fichaSacada == null) {
            System.out.println("Ficha no encontrada.");
            return;
        }

        Paciente p = fichaSacada.getPaciente();

        Queja queja = new Queja(fichaSacada.getNumeroFicha(), p.getCedula(), p.getNombre(), motivo);
        pilaQuejas.apilarQueja(queja);

        System.out.println("\nFicha # " + fichaSacada.getNumeroFicha() + " con cedula "
                + p.getCedula() + " abandona la cola sin ser atendido(a).");
    }

    /**
     * Opcion "Mostrar Fichas Pendientes". Imprime las fichas de ambas
     * colas usando su distintivo visual.
     */
    public void mostrarFichasPendientes() {
        System.out.println("\n--- Fichas Pendientes ---");
        boolean hayFichas = false;

        if (!colaRegular.estaVacia()) {
            System.out.println("Pacientes Regulares:");
            colaRegular.imprimeFichas();
            hayFichas = true;
        }
        if (!colaPreferencial.estaVacia()) {
            System.out.println("Pacientes Preferenciales:");
            colaPreferencial.imprimeFichas();
            hayFichas = true;
        }
        if (!hayFichas) {
            System.out.println("No hay fichas pendientes.");
        }
    }

    /**
     * Opcion "Mostrar Quejas Recibidas". Imprime la pila de quejas.
     */
    public void mostrarQuejas() {
        System.out.println("\n--- Quejas Recibidas ---");
        pilaQuejas.mostrarQuejas();
    }

    /**
     * Opcion "Consulta de Bitacora de Citas del Dia" (Avance 2).
     * Imprime todas las citas atendidas en la sesion, ordenadas por
     * cedula, con su distintivo de color segun el tiempo de espera.
     */
    public void consultarBitacora() {
        System.out.println("\n--- Bitacora de Citas del Dia ---");
        bitacora.imprimirBitacora();
    }

    /**
     * Opcion "Consulta de Expediente Unico de Pacientes" (Avance 2).
     * Permite navegar uno por uno los expedientes registrados en la
     * Lista Doble Circular, hacia adelante y hacia atras.
     *
     * @param sc Scanner para leer la opcion de navegacion del usuario
     */
    public void consultarExpedientes(Scanner sc) {
        if (!expedientes.iniciarNavegacion()) {
            System.out.println("\nNo hay expedientes registrados.");
            return;
        }

        boolean salir = false;
        while (!salir) {
            ExpedientePaciente actual = expedientes.getExpedienteActual();

            System.out.println("\n--- Expediente de Paciente ---");
            System.out.println(actual.toString());

            System.out.println("  Historico de Citas:");
            if (actual.getHistoricoCitas().estaVacia()) {
                System.out.println("    No tiene citas registradas.");
            } else {
                actual.getHistoricoCitas().imprimir();
            }

            System.out.println("  Historico de Medicamentos:");
            if (actual.getHistoricoMedicamentos().estaVacia()) {
                System.out.println("    No tiene medicamentos registrados.");
            } else {
                actual.getHistoricoMedicamentos().imprimir();
            }

            System.out.println("\n1. Siguiente Expediente");
            System.out.println("2. Expediente Anterior");
            System.out.println("3. Regresar");
            System.out.print("Seleccione una opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    expedientes.siguienteExpediente();
                    break;
                case 2:
                    expedientes.anteriorExpediente();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }
    }

    /**
     * Opcion "Cargar Expediente desde Archivo" (Avance 3). Le pide al
     * usuario la ruta del archivo JSON y con el construye el Arbol
     * Binario de Busqueda del expediente unico de pacientes.
     *
     * @param sc Scanner para leer la ruta del archivo
     */
    public void cargarExpedienteDesdeArchivo(Scanner sc) {
        System.out.println("\n--- Cargar Expediente desde Archivo ---");
        System.out.println("Presione ENTER para usar el archivo por defecto (" + rutaJsonPorDefecto + ").");
        System.out.print("Ingrese la ruta del archivo JSON: ");
        String ruta = sc.nextLine().trim();

        if (ruta.equals("")) {
            ruta = rutaJsonPorDefecto;
        }

        CargadorExpedientes cargador = new CargadorExpedientes();
        int cargados = cargador.cargarArchivo(ruta, arbolExpedientes);

        if (cargados == -1) {
            System.out.println("No se pudo cargar el archivo. Revise la ruta.");
        } else if (cargados == 0) {
            System.out.println("El archivo se leyo pero no se encontraron pacientes nuevos.");
        } else {
            System.out.println("Se cargaron " + cargados + " pacientes en el arbol.");
            System.out.println("Total de expedientes en el arbol: " + arbolExpedientes.contarExpedientes());
            System.out.println("Altura del arbol: " + arbolExpedientes.obtenerAltura());
        }
    }

    /**
     * Opcion "Consulta de Expediente Unico de Pacientes (ABB)"
     * (Avance 3). Imprime todos los expedientes del arbol en recorrido
     * inOrden, o sea ordenados por cedula.
     */
    public void consultarExpedientesArbol() {
        System.out.println("\n--- Expediente Unico de Pacientes (Arbol Binario) ---");
        if (arbolExpedientes.estaVacio()) {
            System.out.println("No hay expedientes en el arbol. Cargue primero el archivo.");
            return;
        }
        arbolExpedientes.imprimirExpedientes();
        System.out.println("\nTotal de expedientes: " + arbolExpedientes.contarExpedientes());
    }

    /**
     * Opcion "Modulo de Inteligencia Empresarial (BI)" (Avance 3).
     * Abre el menu del modulo de BI.
     *
     * @param sc Scanner para leer las opciones del usuario
     */
    public void abrirModuloBI(Scanner sc) {
        moduloBI.menuBI(sc);
    }
}