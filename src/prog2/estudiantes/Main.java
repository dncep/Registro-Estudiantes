package prog2.estudiantes;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.MenuBucle;
import prog2.estudiantes.menu.MenuInicio;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.err.close();
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
