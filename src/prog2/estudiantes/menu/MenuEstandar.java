package prog2.estudiantes.menu;

import prog2.estudiantes.data.Registro;

import java.util.List;
import java.util.Scanner;

public interface MenuEstandar extends Menu {

    List<Menu> getOpciones();

    default boolean seleccionar(Registro registro, Scanner scanner) {
        System.out.println(this.getNombre() + ": ");
        int i = 0;
        for(Menu menu : getOpciones()) {
            System.out.println("\t" + (i+1) + ". " + menu.getNombre());
            i++;
        }
        try {
            i = Entrada.getInt("Introduzca el número de la opción a seleccionar", scanner, n -> n >= 1 && n <= getOpciones().size());
        } catch(AtrasExcepcion x) {
            return false;
        }
        Menu opcion = getOpciones().get(i-1);
        try {
            return opcion.seleccionar(registro, scanner);
        } catch(AtrasExcepcion x) {
            return true;
        }
    }
}
