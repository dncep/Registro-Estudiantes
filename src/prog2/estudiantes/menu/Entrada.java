package prog2.estudiantes.menu;

import prog2.estudiantes.data.Carrera;
import prog2.estudiantes.data.Cedula;
import prog2.estudiantes.data.Estado;
import prog2.estudiantes.data.Util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Predicate;

public class Entrada {

    private static final Collection<String> VALORES_VERDADEROS = Arrays.asList("SI", "SÍ", "S", "V", "VERDADERO", "T", "Y", "TRUE", "YES");
    private static final Collection<String> VALORES_FALSOS = Arrays.asList("NO", "N", "F", "FALSO", "FALSE");

    /**
     * Solicita una cadena de caracteres no vacía al usuario. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar la cadena
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return La cadena insertada por el usuario
     * */
    public static String getString(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = scanner.nextLine();
            if(input.trim().length() > 0) {
                return input;
            } else {
                System.out.println("Entrada '" + input + "' inválida");
            }
        }
    }

    /**
     * Solicita una fecha al usuario. Si comete un error, la solicitará de nuevo hasta
     * conseguir una fecha válida.
     * @param instruccion El mensaje que muestra al solicitar la fecha
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return La fecha insertada por el usuario
     * */
    public static Calendar getFecha(String instruccion, Scanner scanner) {
        System.out.println(instruccion + ": ");
        int a = getInt("\tIntroduzca el año", scanner, n -> (n > 0 & n <= Calendar.getInstance().get(Calendar.YEAR)));
        boolean bisiesto = (a % 4 == 0) && (a % 100 != 0 || a % 400 == 0);
        int m = getInt("\tIntroduzca el mes (01-12)", scanner, n -> (n >= 1 && n <= 12));
        int d = getInt("\tIntroduzca el día del mes", scanner, n -> n >= 1 && n <= ((m == 2) ? (bisiesto ? 29 : 28) : (30 + ((m <= 7) ? (n % 2) : ((n+1) % 2)))));
        Calendar date = Calendar.getInstance();
        date.set(a, m-1, d);
        return date;
    }

    /**
     * Solicita un entero al usuario. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar el entero
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return El entero insertado por el usuario
     * */
    public static int getInt(String instruccion, Scanner scanner) {
        return getInt(instruccion, scanner, n -> true);
    }

    /**
     * Solicita un entero al usuario. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar el entero
     * @param scanner El Scanner que utilizará para la entrada de información
     * @param validacion El predicado que determina si el valor numérico insertado es válido
     *
     * @return El entero insertado por el usuario
     * */
    public static int getInt(String instruccion, Scanner scanner, Predicate<Integer> validacion) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = scanner.nextLine();
            try {
                int n = Integer.parseInt(input);
                if(validacion.test(n)) {
                    return n;
                }
            } catch(NumberFormatException x) {
                //Salta fuera del catch y muestra un error
            }
            System.out.println("Entrada '" + input + "' inválida");
        }
    }

    /**
     * Solicita un número de cédula al usuario. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar la cédula
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return El objeto cédula insertado por el usuario
     * */
    public static Cedula getCedula(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = scanner.nextLine();

            Cedula cedula = Cedula.crearCedula(input);
            if(cedula != null) {
                return cedula;
            } else {
                System.out.println("Entrada '" + input + "' inválida");
            }
        }
    }

    /**
     * Solicita un valor verdadero o falso al usuario de una lista predeterminada de valores.
     * Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar el valor
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return El boolean representado por el valor insertado por el usuario
     * */
    public static boolean getBoolean(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = scanner.nextLine().trim().toUpperCase();

            if(VALORES_VERDADEROS.contains(input)) return true;
            else if(VALORES_FALSOS.contains(input)) return false;
            else System.out.println("Entrada '" + input + "' inválida");;
        }
    }

    /**
     * Solicita un estado académico al usuario. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar el estado
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return El estado insertado por el usuario
     * */
    public static Estado getEstado(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = Util.normalizar(scanner.nextLine().trim().toUpperCase());

            for(Estado estado : Estado.values()) {
                if(Util.normalizar(estado.toString().toUpperCase()).equals(input)) return estado;
            }

            System.out.println("Entrada '" + input + "' inválida");;
        }
    }

    /**
     * Solicita una carrera al usuario por nombre o código. Si comete un error, la solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar la carrera
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return La carrera insertada por el usuario
     * */
    public static Carrera getCarrera(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + ": ");
            String input = scanner.nextLine().trim().toUpperCase();

            for(Carrera carrera : Carrera.values()) {
                if(carrera.getCodigo().equals(input) || Util.normalizar(carrera.getNombre().toUpperCase()).equals(Util.normalizar(input))) return carrera;
            }

            System.out.println("Entrada '" + input + "' inválida");;
        }
    }
}
