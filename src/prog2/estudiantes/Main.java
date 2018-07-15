package prog2.estudiantes;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.MenuBucle;
import prog2.estudiantes.menu.MenuInicio;

import java.io.File;
import java.util.Scanner;

public class Main {

    /*
    * Pendiente:
    *
    * Reportería (Menu 4):
    *   PDF (HECHO)
    *   Excel (HECHO)
    *   CSV (HECHO)
    *
    *   Menu:
    *       1. Listado Estudiantes por Sección (PDF, Excel, CSV) (HECHO)
    *
    * Integración:
    *   Menu:
    *       1. Estudiantes:
    *           1. Importar (XML, JSON) (HECHO)
    *           2. Exportar (XML, JSON) (HECHO)
    *
    *       2. Materias:
    *           1. Importar (XML, JSON) (HECHO)
    *           2. Exportar (XML, JSON)
    *
    * Reglas de negocio:
    *   1. Solo estudiantes activos (HECHO)
    *   2. No estudiantes duplicados en una sección (HECHO)
    *   3. Un estudiante no puede estar registrado en más de 5 secciones (HECHO)
    *   4. Un estudiante no puede registrarse en más de 2 secciones de la misma área (HECHO)
    *   5. El ID del estudiante es unico, autogenerado con formato yymmddsec, (year, month, day, sequence) (180620001) (HECHO)
    *
    * */

    public static void main(String[] args) {
        MenuBucle bucle = new MenuBucle(new MenuInicio());
        Registro registro = new Registro(new File(System.getProperty("user.home") + File.separator + "registro_estudiantes"));
        registro.cargar();
        System.out.println("\nTip: Cuando vea un símbolo * durante la entrada de datos,\n" +
                "puede introducir un '?' para visualizar las opciones.\n" +
                "Puede escribir 'atras' en cualquier momento para volver al menú anterior.\n");
        bucle.seleccionar(registro, new Scanner(System.in));
        registro.guardar();
        System.out.println("El registro ha sido guardado");
    }
}
