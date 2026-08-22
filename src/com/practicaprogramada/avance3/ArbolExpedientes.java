package com.practicaprogramada.avance3;

/**
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class ArbolExpedientes extends ArbolBinario {

    /**
     * Metodo wrapper que inserta un expediente en el arbol.
     *
     * @param expediente expediente del paciente a insertar
     */
    public void insertarExpediente(ExpedientePaciente expediente) {
        int cedula = convertirCedula(expediente.getCedula());
        setRaiz(insertarExpedienteRec(getRaiz(), cedula, expediente));
    }

    /**
     * Metodo recursivo que inserta el expediente en la posicion que le
     * corresponde segun la cedula (menores a la izquierda, mayores a
     * la derecha).
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param cedula cedula del paciente, llave del arbol
     * @param expediente expediente del paciente
     * @return el nodo actual ya con el nuevo expediente amarrado
     */
    private Nodo insertarExpedienteRec(Nodo nodoActual, int cedula, ExpedientePaciente expediente) {
        if (nodoActual == null) {
            return new NodoExpediente(cedula, expediente);
        } else {
            if (cedula < nodoActual.getDato()) {
                nodoActual.setNodoIzq(insertarExpedienteRec(nodoActual.getNodoIzq(), cedula, expediente));
            } else if (cedula > nodoActual.getDato()) {
                nodoActual.setNodoDer(insertarExpedienteRec(nodoActual.getNodoDer(), cedula, expediente));
            }
            // Si es igual el paciente ya existe, entonces no se inserta de nuevo.
            return nodoActual;
        }
    }

    /**
     * Metodo wrapper que busca un expediente por la cedula.
     *
     * @param cedula cedula del paciente que se busca
     * @return el expediente encontrado, o null si no existe
     */
    public ExpedientePaciente buscarPorCedula(String cedula) {
        return buscarRec(getRaiz(), convertirCedula(cedula));
    }

    /**
     * Metodo recursivo que busca un expediente dentro del arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param cedula cedula que se busca
     * @return el expediente encontrado, o null si no existe
     */
    private ExpedientePaciente buscarRec(Nodo nodoActual, int cedula) {
        if (nodoActual == null) {
            return null;
        }
        if (cedula < nodoActual.getDato()) {
            return buscarRec(nodoActual.getNodoIzq(), cedula);
        } else if (cedula > nodoActual.getDato()) {
            return buscarRec(nodoActual.getNodoDer(), cedula);
        } else {
            NodoExpediente nodo = (NodoExpediente) nodoActual;
            return nodo.getExpediente();
        }
    }

    /**
     * Metodo wrapper que imprime todos los expedientes del arbol en
     * recorrido inOrden, o sea ordenados por cedula de menor a mayor.
     */
    public void imprimirExpedientes() {
        imprimirExpedientesRec(getRaiz());
    }

    /**
     * Metodo recursivo que imprime los expedientes con su historico de
     * citas y su historico de medicamentos.
     *
     * @param nodoActual nodo por donde va el recorrido
     */
    private void imprimirExpedientesRec(Nodo nodoActual) {
        if (nodoActual != null) {
            imprimirExpedientesRec(nodoActual.getNodoIzq());

            NodoExpediente nodo = (NodoExpediente) nodoActual;
            ExpedientePaciente e = nodo.getExpediente();

            System.out.println("\n--- Expediente de Paciente ---");
            System.out.println(e.toString());

            System.out.println("  Historico de Citas:");
            if (e.getHistoricoCitas().estaVacia()) {
                System.out.println("    No tiene citas registradas.");
            } else {
                e.getHistoricoCitas().imprimir();
            }

            System.out.println("  Historico de Medicamentos:");
            if (e.getHistoricoMedicamentos().estaVacia()) {
                System.out.println("    No tiene medicamentos registrados.");
            } else {
                e.getHistoricoMedicamentos().imprimir();
            }

            imprimirExpedientesRec(nodoActual.getNodoDer());
        }
    }

    /**
     * Metodo wrapper que cuenta cuantos expedientes hay en el arbol.
     *
     * @return la cantidad de expedientes
     */
    public int contarExpedientes() {
        return contarExpedientesRec(getRaiz());
    }

    /**
     * Metodo recursivo que cuenta los nodos del arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @return la cantidad de nodos desde el nodo actual
     */
    private int contarExpedientesRec(Nodo nodoActual) {
        if (nodoActual == null) {
            return 0;
        }
        return 1 + contarExpedientesRec(nodoActual.getNodoIzq())
                + contarExpedientesRec(nodoActual.getNodoDer());
    }

    // ------------------------------------------------------------------
    // De aqui para abajo van los recorridos que ocupa el modulo de BI.
    // Estan en esta clase (que hereda de ArbolBinario) y no en ModuloBI
    // ------------------------------------------------------------------

    /**
     * Metodo wrapper que recorre el arbol y arma la lista con el
     * conteo de cada diagnostico que aparece en las citas.
     *
     * @return lista con los diagnosticos y cuantas veces aparecieron
     */
    public ListaConteos contarDiagnosticos() {
        ListaConteos conteos = new ListaConteos();
        contarDiagnosticosRec(getRaiz(), conteos);
        return conteos;
    }

    /**
     * Metodo recursivo que va sumando los diagnosticos de cada
     * paciente del arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param conteos lista donde se van acumulando los conteos
     */
    private void contarDiagnosticosRec(Nodo nodoActual, ListaConteos conteos) {
        if (nodoActual != null) {
            contarDiagnosticosRec(nodoActual.getNodoIzq(), conteos);

            NodoExpediente nodo = (NodoExpediente) nodoActual;
            ListaCircular citas = nodo.getExpediente().getHistoricoCitas();

            if (!citas.estaVacia()) {
                NodoCircular aux = citas.getPrimero();
                do {
                    Cita c = (Cita) aux.getDato();
                    conteos.registrar(c.getDiagnostico());
                    aux = aux.getSiguiente();
                } while (aux != citas.getPrimero());
            }

            contarDiagnosticosRec(nodoActual.getNodoDer(), conteos);
        }
    }

    /**
     * Metodo wrapper que recorre el arbol y arma la lista con el
     * conteo de cada medicamento recetado.
     *
     * @return lista con los medicamentos y cuantas veces se recetaron
     */
    public ListaConteos contarMedicamentos() {
        ListaConteos conteos = new ListaConteos();
        contarMedicamentosRec(getRaiz(), conteos);
        return conteos;
    }

    /**
     * Metodo recursivo que va sumando los medicamentos de cada
     * paciente del arbol.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param conteos lista donde se van acumulando los conteos
     */
    private void contarMedicamentosRec(Nodo nodoActual, ListaConteos conteos) {
        if (nodoActual != null) {
            contarMedicamentosRec(nodoActual.getNodoIzq(), conteos);

            NodoExpediente nodo = (NodoExpediente) nodoActual;
            ListaCircular medicamentos = nodo.getExpediente().getHistoricoMedicamentos();

            if (!medicamentos.estaVacia()) {
                NodoCircular aux = medicamentos.getPrimero();
                do {
                    Medicamento m = (Medicamento) aux.getDato();
                    conteos.registrar(m.getMedicamentoPrescrito());
                    aux = aux.getSiguiente();
                } while (aux != medicamentos.getPrimero());
            }

            contarMedicamentosRec(nodoActual.getNodoDer(), conteos);
        }
    }

    /**
     * Metodo wrapper que cuenta cuantos pacientes del arbol estan
     * dentro de un rango de edad.
     *
     * @param edadInicial edad minima del rango
     * @param edadFinal edad maxima del rango
     * @return la cantidad de pacientes dentro del rango
     */
    public int contarPorRangoEdad(int edadInicial, int edadFinal) {
        return contarPorRangoRec(getRaiz(), edadInicial, edadFinal);
    }

    /**
     * Metodo recursivo que cuenta los pacientes dentro del rango.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param edadInicial edad minima del rango
     * @param edadFinal edad maxima del rango
     * @return la cantidad de pacientes desde el nodo actual
     */
    private int contarPorRangoRec(Nodo nodoActual, int edadInicial, int edadFinal) {
        if (nodoActual == null) {
            return 0;
        }

        NodoExpediente nodo = (NodoExpediente) nodoActual;
        int edad = nodo.getExpediente().getEdad();

        int cuenta = 0;
        if (edad >= edadInicial && edad <= edadFinal) {
            cuenta = 1;
        }

        return cuenta
                + contarPorRangoRec(nodoActual.getNodoIzq(), edadInicial, edadFinal)
                + contarPorRangoRec(nodoActual.getNodoDer(), edadInicial, edadFinal);
    }

    /**
     * Metodo wrapper que cuenta los pacientes que cumplen con todos
     * los parametros de la consulta de deteccion de patrones. Los
     * parametros de texto que vengan vacios no se toman en cuenta.
     *
     * @param edadInicial edad minima del rango
     * @param edadFinal edad maxima del rango
     * @param diagnostico diagnostico buscado, vacio si no se usa
     * @param genero genero buscado, vacio si no se usa
     * @param medicamento medicamento buscado, vacio si no se usa
     * @return la cantidad de pacientes que cumplen los parametros
     */
    public int contarPorPatron(int edadInicial, int edadFinal, String diagnostico,
            String genero, String medicamento) {
        return buscarPatronRec(getRaiz(), edadInicial, edadFinal, diagnostico, genero, medicamento);
    }

    /**
     * Metodo recursivo que recorre el arbol revisando cuales pacientes
     * cumplen con todos los parametros indicados.
     *
     * @param nodoActual nodo por donde va el recorrido
     * @param edadInicial edad minima del rango
     * @param edadFinal edad maxima del rango
     * @param diagnostico diagnostico buscado, vacio si no se usa
     * @param genero genero buscado, vacio si no se usa
     * @param medicamento medicamento buscado, vacio si no se usa
     * @return la cantidad de pacientes que cumplen desde el nodo actual
     */
    private int buscarPatronRec(Nodo nodoActual, int edadInicial, int edadFinal,
            String diagnostico, String genero, String medicamento) {

        if (nodoActual == null) {
            return 0;
        }

        NodoExpediente nodo = (NodoExpediente) nodoActual;
        ExpedientePaciente e = nodo.getExpediente();

        boolean cumple = true;

        if (e.getEdad() < edadInicial || e.getEdad() > edadFinal) {
            cumple = false;
        }
        if (cumple && !genero.equals("") && !mismoGenero(e.getGenero(), genero)) {
            cumple = false;
        }
        if (cumple && !diagnostico.equals("") && !tieneDiagnostico(e, diagnostico)) {
            cumple = false;
        }
        if (cumple && !medicamento.equals("") && !tieneMedicamento(e, medicamento)) {
            cumple = false;
        }

        int cuenta = 0;
        if (cumple) {
            cuenta = 1;
        }

        return cuenta
                + buscarPatronRec(nodoActual.getNodoIzq(), edadInicial, edadFinal,
                        diagnostico, genero, medicamento)
                + buscarPatronRec(nodoActual.getNodoDer(), edadInicial, edadFinal,
                        diagnostico, genero, medicamento);
    }

    /**
     * Compara el genero del expediente con el que busca el usuario.
     * Compara solo la primera letra para que sirva igual si escriben
     * M o Masculino.
     *
     * @param generoPaciente genero guardado en el expediente
     * @param generoBuscado genero que digito el usuario
     * @return true si son el mismo genero, false en caso contrario
     */
    private boolean mismoGenero(String generoPaciente, String generoBuscado) {
        if (generoPaciente.length() == 0 || generoBuscado.length() == 0) {
            return false;
        }
        String letraPaciente = generoPaciente.substring(0, 1);
        String letraBuscada = generoBuscado.substring(0, 1);
        return letraPaciente.equalsIgnoreCase(letraBuscada);
    }

    /**
     * Revisa si el paciente tiene en su historico de citas el
     * diagnostico buscado.
     *
     * @param e expediente del paciente
     * @param diagnostico diagnostico que se busca
     * @return true si lo tiene, false en caso contrario
     */
    private boolean tieneDiagnostico(ExpedientePaciente e, String diagnostico) {
        ListaCircular citas = e.getHistoricoCitas();
        if (citas.estaVacia()) {
            return false;
        }
        NodoCircular aux = citas.getPrimero();
        do {
            Cita c = (Cita) aux.getDato();
            if (c.getDiagnostico().equalsIgnoreCase(diagnostico)) {
                return true;
            }
            aux = aux.getSiguiente();
        } while (aux != citas.getPrimero());
        return false;
    }

    /**
     * Revisa si el paciente tiene en su historico de medicamentos el
     * medicamento buscado.
     *
     * @param e expediente del paciente
     * @param medicamento medicamento que se busca
     * @return true si lo tiene, false en caso contrario
     */
    private boolean tieneMedicamento(ExpedientePaciente e, String medicamento) {
        ListaCircular medicamentos = e.getHistoricoMedicamentos();
        if (medicamentos.estaVacia()) {
            return false;
        }
        NodoCircular aux = medicamentos.getPrimero();
        do {
            Medicamento m = (Medicamento) aux.getDato();
            if (m.getMedicamentoPrescrito().equalsIgnoreCase(medicamento)) {
                return true;
            }
            aux = aux.getSiguiente();
        } while (aux != medicamentos.getPrimero());
        return false;
    }

    /**
     * Convierte la cedula de texto a numero entero para poder usarla
     * como llave del arbol. Le quita los guiones o espacios que traiga.
     *
     * @param cedula cedula en formato de texto
     * @return la cedula convertida a entero, o 0 si viene vacia
     */
    public int convertirCedula(String cedula) {
        String limpia = "";
        for (int i = 0; i < cedula.length(); i++) {
            char c = cedula.charAt(i);
            if (c >= '0' && c <= '9') {
                limpia = limpia + c;
            }
        }
        if (limpia.length() == 0) {
            return 0;
        }
        return Integer.parseInt(limpia);
    }
}