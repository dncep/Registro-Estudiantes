package prog2.estudiantes;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.MenuBucle;
import prog2.estudiantes.menu.MenuInicio;
import prog2.estudiantes.menu.estudiantes.MenuEstudiantes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    * Pendiente:
    *
    * */

    public static void main(String[] args) {
        MenuBucle bucle = new MenuBucle(new MenuInicio());
        Registro registro = new Registro(new File(System.getProperty("user.home") + File.separator + "registro_estudiantes"));
        registro.cargar();
        System.out.println("Tip: Cuando vea un símbolo * durante la entrada de datos, puede introducir un '?' para visualizar las opciones.\n");
        bucle.seleccionar(registro, new Scanner(System.in));
        registro.guardar();

    }
}
