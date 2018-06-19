package prog2.estudiantes.menu;

import prog2.estudiantes.data.*;

import java.util.*;
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
            System.out.print(instruccion + "*: ");
            String input = scanner.nextLine().trim();

            Cedula cedula = Cedula.crearCedula(input);
            if(cedula != null) {
                return cedula;
            } else if(input.equals("?")) {
                System.out.println("Introduzca una cédula en formato XXXXXXXXXXX o XXX-XXXXXXX-X");
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
            System.out.print(instruccion + "*: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if(VALORES_VERDADEROS.contains(input)) return true;
            else if(VALORES_FALSOS.contains(input)) return false;
            else if(input.equals("?")) System.out.println("Valores válidos: si/no");
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
            System.out.print(instruccion + "*: ");
            String input = Util.normalizar(scanner.nextLine().trim().toUpperCase());

            if(input.equals("?")) {
                ayudaEstado();
            } else {
                for(Estado estado : Estado.values()) {
                    if(Util.normalizar(estado.toString().toUpperCase()).equals(input)) return estado;
                }

                System.out.println("Entrada '" + input + "' inválida");;
            }
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
            System.out.print(instruccion + "*: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if(input.equals("?")) {
                ayudaCarrera();
            } else {
                for(Carrera carrera : Carrera.values()) {
                    if(carrera.getCodigo().equals(input) || Util.normalizar(carrera.getNombre().toUpperCase()).equals(Util.normalizar(input))) return carrera;
                }

                System.out.println("Entrada '" + input + "' inválida");;
            }
        }
    }

    /**
     * Solicita un area académica al usuario por nombre o código. Si comete un error, lo solicitará de nuevo hasta
     * conseguir un valor válido.
     * @param instruccion El mensaje que muestra al solicitar el area
     * @param scanner El Scanner que utilizará para la entrada de información
     *
     * @return El area insertada por el usuario
     * */
    public static AreaAcademica getArea(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + "*: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if(input.equals("?")) {
                ayudaArea();
            } else {
                for(AreaAcademica area : AreaAcademica.values()) {
                    if(area.getCodigo().equals(input) || Util.normalizar(area.getNombre().toUpperCase()).equals(Util.normalizar(input))) return area;
                }

                System.out.println("Entrada '" + input + "' inválida");;
            }
        }
    }

    public static Trimestre getTrimestre(String instruccion, Scanner scanner) {
        //TODO
        return null;
    }

    public static HoraDia getHoraDia(String instruccion, Scanner scanner) {
        //TODO
        return null;
    }

    public static DiaSemana getDiaSemana(String instruccion, Scanner scanner) {
        while(true) {
            System.out.print(instruccion + "*: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if(input.equals("?")) {
                ayudaDiaSemana();
            } else {
                for(DiaSemana dia : DiaSemana.values()) {
                    if(dia.getNombre().toUpperCase().equals(input) || dia.getCorto().toUpperCase().equals(input))
                        return dia;
                }

                System.out.println("Entrada '" + input + "' inválida");;
            }
        }
    }

    public static Horario editarHorario(Horario horario, Scanner scanner) {
        horario = horario.duplicate();
        while(true) {
            System.out.println("Horario actual:");
            for(DiaSemana dia : DiaSemana.values()) {
                System.out.print(dia.getNombre() + ": ");
                if(horario.getMapa().containsKey(dia)) System.out.print(horario.getMapa().get(dia));
                System.out.println();
            }

            if(getBoolean("Terminar?", scanner)) break;

            DiaSemana dia = getDiaSemana("Introduzca el dia que desea editar", scanner);
            HoraDia hora = getHoraDia("Introduzca las horas para el día " + dia.getNombre(), scanner);

            horario.colocar(dia, hora);
        }

        return horario;
    }

    public static void ayudaEstado() {
        System.out.println("Estados válidos:");
        for(Estado estado : Estado.values()) {
            System.out.println("    " + estado);
        }
    }

    public static void ayudaCarrera() {
        System.out.println("Carreras válidas:");
        for(Carrera carrera : Carrera.values()) {
            System.out.println("    " + carrera.getCodigo() + " / " + carrera.getNombre());
        }
    }

    public static void ayudaArea() {
        System.out.println("Áreas académicas válidas:");
        for(AreaAcademica area : AreaAcademica.values()) {
            System.out.println("    " + area.getCodigo() + " / " + area.getNombre());
        }
    }

    public static void ayudaDiaSemana() {
        System.out.println("Días de la semana válidos:");
        for(DiaSemana dia : DiaSemana.values()) {
            System.out.println("    " + dia.getNombre() + " / " + dia.getCorto());
        }
    }
}
