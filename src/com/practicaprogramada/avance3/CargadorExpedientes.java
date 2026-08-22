package com.practicaprogramada.avance3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;

/**
 * Clase que se encarga de leer el archivo JSON de pacientes que da el
 * profesor y con esa informacion construir el Arbol Binario de
 * Busqueda del Expediente Unico de Pacientes (Avance 3).
 *
 * El archivo se lee tal cual como texto y se va recortando con los
 * metodos de String, porque no se pueden usar librerias externas ni
 * arreglos. Los nombres de los campos estan como constantes al inicio
 * para poder cambiarlos facil si el archivo viene distinto.
 *
 * @author Grupo 4 - Kenneth Amador Fernandez, Valeria Barboza Chaves, Kyle Guzman Martinez, Brandon Soto Salas
 */
public class CargadorExpedientes {

    // Nombres de los campos que trae el archivo JSON.
    private static final String CAMPO_CEDULA = "CEDULA";
    private static final String CAMPO_NOMBRE = "NOMBRE";
    private static final String CAMPO_EDAD = "EDAD";
    private static final String CAMPO_GENERO = "GENERO";
    private static final String CAMPO_CITAS = "CITAS";
    private static final String CAMPO_FECHA = "FECHA";
    private static final String CAMPO_DOCTOR = "MEDICO";
    private static final String CAMPO_DIAGNOSTICO = "DIAGNOSTICO";
    private static final String CAMPO_MEDICAMENTOS = "MEDICAMENTOS";
    private static final String CAMPO_MEDICAMENTO = "MEDICAMENTO";

    /**
     * Lee el archivo JSON y va insertando cada paciente en el arbol.
     *
     * @param ruta ruta del archivo JSON que se quiere cargar
     * @param arbol arbol donde se van a guardar los expedientes
     * @return la cantidad de pacientes que se cargaron, o -1 si hubo error
     */
    public int cargarArchivo(String ruta, ArbolExpedientes arbol) {
        String texto = leerArchivo(ruta);

        if (texto == null) {
            return -1;
        }

        int cantidad = 0;
        int posicion = 0;

        while (true) {
            int inicio = buscarCampo(texto, CAMPO_CEDULA, posicion);
            if (inicio == -1) {
                break;
            }

            // El paciente termina donde empieza la cedula del siguiente.
            int fin = buscarCampo(texto, CAMPO_CEDULA, inicio + 1);
            if (fin == -1) {
                fin = texto.length();
            }

            String bloque = texto.substring(inicio, fin);

            if (procesarPaciente(bloque, arbol)) {
                cantidad = cantidad + 1;
            }

            posicion = fin;
        }

        return cantidad;
    }

    /**
     * Toma el pedazo de texto de un paciente, arma su expediente con
     * sus citas y sus medicamentos, y lo inserta en el arbol.
     *
     * @param bloque texto del JSON que corresponde a un solo paciente
     * @param arbol arbol donde se inserta el expediente
     * @return true si el paciente se pudo cargar, false si no
     */
    private boolean procesarPaciente(String bloque, ArbolExpedientes arbol) {
        String cedula = valorTexto(bloque, CAMPO_CEDULA, 0);
        String nombre = valorTexto(bloque, CAMPO_NOMBRE, 0);
        String edadTexto = valorTexto(bloque, CAMPO_EDAD, 0);
        String genero = valorTexto(bloque, CAMPO_GENERO, 0);

        if (cedula.equals("") || nombre.equals("")) {
            return false;
        }

        int edad = 0;
        try {
            edad = Integer.parseInt(edadTexto.trim());
        } catch (Exception e) {
            edad = 0;
        }

        if (genero.equals("")) {
            genero = "No indicado";
        }

        // Si el paciente ya estaba cargado no lo vuelvo a meter.
        if (arbol.buscarPorCedula(cedula) != null) {
            return false;
        }

        ExpedientePaciente expediente = new ExpedientePaciente(cedula, nombre, edad, genero);

        cargarCitas(bloque, expediente);
        cargarMedicamentos(bloque, expediente);

        arbol.insertarExpediente(expediente);
        return true;
    }

    /**
     * Recorre el arreglo de CITAS del paciente y por cada objeto que
     * encuentra saca la fecha, el medico y el diagnostico, y lo mete
     * en su historico de citas.
     *
     * @param bloque texto del JSON que corresponde a un solo paciente
     * @param expediente expediente al que se le agregan las citas
     */
    private void cargarCitas(String bloque, ExpedientePaciente expediente) {
        String lista = sacarArreglo(bloque, CAMPO_CITAS);
        if (lista.equals("")) {
            return;
        }

        int posicion = 0;
        while (true) {
            int abre = lista.indexOf("{", posicion);
            if (abre == -1) {
                break;
            }
            int cierra = lista.indexOf("}", abre);
            if (cierra == -1) {
                break;
            }

            String objeto = lista.substring(abre, cierra);

            String fechaTexto = valorTexto(objeto, CAMPO_FECHA, 0);
            String doctor = valorTexto(objeto, CAMPO_DOCTOR, 0);
            String diagnostico = valorTexto(objeto, CAMPO_DIAGNOSTICO, 0);

            if (doctor.equals("")) {
                doctor = "No indicado";
            }
            if (diagnostico.equals("")) {
                diagnostico = "No indicado";
            }

            expediente.getHistoricoCitas().insertarAlFinal(
                    new Cita(convertirFecha(fechaTexto), doctor, diagnostico));

            posicion = cierra + 1;
        }
    }

    /**
     * Recorre el arreglo de MEDICAMENTOS del paciente y los mete en su
     * historico. Soporta que vengan como objetos con fecha o como
     * simples textos entre comillas.
     *
     * @param bloque texto del JSON que corresponde a un solo paciente
     * @param expediente expediente al que se le agregan los medicamentos
     */
    private void cargarMedicamentos(String bloque, ExpedientePaciente expediente) {
        String lista = sacarArreglo(bloque, CAMPO_MEDICAMENTOS);
        if (lista.equals("")) {
            return;
        }

        if (buscarCampo(lista, CAMPO_MEDICAMENTO, 0) != -1) {
            // Vienen como objetos con el campo medicamento adentro.
            int posicion = 0;
            while (true) {
                int abre = lista.indexOf("{", posicion);
                if (abre == -1) {
                    break;
                }
                int cierra = lista.indexOf("}", abre);
                if (cierra == -1) {
                    break;
                }

                String objeto = lista.substring(abre, cierra);

                String fechaTexto = valorTexto(objeto, CAMPO_FECHA, 0);
                String nombre = valorTexto(objeto, CAMPO_MEDICAMENTO, 0);

                if (!nombre.trim().equals("")) {
                    expediente.getHistoricoMedicamentos().insertarAlFinal(
                            new Medicamento(convertirFecha(fechaTexto), nombre));
                }

                posicion = cierra + 1;
            }
        } else {
            // Vienen como textos sueltos entre comillas.
            int posicion = 0;
            while (true) {
                int comillaInicio = lista.indexOf("\"", posicion);
                if (comillaInicio == -1) {
                    break;
                }
                int comillaFin = lista.indexOf("\"", comillaInicio + 1);
                if (comillaFin == -1) {
                    break;
                }
                String nombre = lista.substring(comillaInicio + 1, comillaFin);
                if (!nombre.trim().equals("")) {
                    expediente.getHistoricoMedicamentos().insertarAlFinal(new Medicamento(nombre));
                }
                posicion = comillaFin + 1;
            }
        }
    }

    /**
     * Devuelve el contenido que esta entre los corchetes de un campo
     * que trae un arreglo, por ejemplo CITAS o MEDICAMENTOS.
     *
     * @param bloque texto del JSON que corresponde a un solo paciente
     * @param campo nombre del campo que trae el arreglo
     * @return el contenido del arreglo, o cadena vacia si no aparece
     */
    private String sacarArreglo(String bloque, String campo) {
        int inicio = buscarCampo(bloque, campo, 0);
        if (inicio == -1) {
            return "";
        }

        int corcheteAbre = bloque.indexOf("[", inicio);
        int corcheteCierra = bloque.indexOf("]", inicio);

        if (corcheteAbre == -1 || corcheteCierra == -1 || corcheteCierra < corcheteAbre) {
            return "";
        }

        return bloque.substring(corcheteAbre + 1, corcheteCierra);
    }

    /**
     * Convierte la fecha que viene en el archivo, con el formato
     * aaaa-mm-dd hh:mm:ss, a un objeto Date. Si la fecha viene vacia o
     * mal escrita se usa la fecha de hoy para no quebrar la carga.
     *
     * @param texto fecha en formato de texto
     * @return la fecha convertida
     */
    private Date convertirFecha(String texto) {
        if (texto.length() < 10) {
            return new Date();
        }

        try {
            int anio = Integer.parseInt(texto.substring(0, 4));
            int mes = Integer.parseInt(texto.substring(5, 7));
            int dia = Integer.parseInt(texto.substring(8, 10));

            int hora = 0;
            int minuto = 0;
            int segundo = 0;

            if (texto.length() >= 19) {
                hora = Integer.parseInt(texto.substring(11, 13));
                minuto = Integer.parseInt(texto.substring(14, 16));
                segundo = Integer.parseInt(texto.substring(17, 19));
            }

            // El anio se cuenta desde 1900 y el mes desde 0.
            return new Date(anio - 1900, mes - 1, dia, hora, minuto, segundo);

        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * Lee todo el archivo y lo devuelve en un solo texto.
     *
     * @param ruta ruta del archivo que se va a leer
     * @return el contenido del archivo, o null si no se pudo leer
     */
    private String leerArchivo(String ruta) {
        String contenido = "";
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            String linea = lector.readLine();
            while (linea != null) {
                contenido = contenido + linea + " ";
                linea = lector.readLine();
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
            return null;
        }
        return contenido;
    }

    /**
     * Busca en que posicion aparece un campo del JSON, o sea el nombre
     * del campo entre comillas. Se pasa todo a mayusculas para que no
     * importe si el archivo trae los campos en mayuscula o minuscula.
     *
     * @param texto texto donde se busca
     * @param campo nombre del campo que se busca
     * @param desde posicion desde la cual se empieza a buscar
     * @return la posicion donde aparece el campo, o -1 si no aparece
     */
    private int buscarCampo(String texto, String campo, int desde) {
        return texto.toUpperCase().indexOf("\"" + campo.toUpperCase() + "\"", desde);
    }

    /**
     * Saca el valor de un campo del JSON. Sirve tanto si el valor
     * viene entre comillas como si viene como numero.
     *
     * @param texto texto donde se busca
     * @param campo nombre del campo del que se quiere el valor
     * @param desde posicion desde la cual se empieza a buscar
     * @return el valor del campo, o una cadena vacia si no lo encuentra
     */
    private String valorTexto(String texto, String campo, int desde) {
        int inicio = buscarCampo(texto, campo, desde);
        if (inicio == -1) {
            return "";
        }

        int dosPuntos = texto.indexOf(":", inicio);
        if (dosPuntos == -1) {
            return "";
        }

        int i = dosPuntos + 1;
        while (i < texto.length() && texto.charAt(i) == ' ') {
            i = i + 1;
        }
        if (i >= texto.length()) {
            return "";
        }

        if (texto.charAt(i) == '"') {
            int fin = texto.indexOf("\"", i + 1);
            if (fin == -1) {
                return "";
            }
            return texto.substring(i + 1, fin);
        } else {
            int fin = i;
            while (fin < texto.length()
                    && texto.charAt(fin) != ','
                    && texto.charAt(fin) != '}'
                    && texto.charAt(fin) != ']') {
                fin = fin + 1;
            }
            return texto.substring(i, fin).trim();
        }
    }
}