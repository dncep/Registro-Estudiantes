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
        System.out.print("Introduzca el número de la opción a seleccionar: ");
        String input = scanner.nextLine();
        try {
            i = Integer.parseInt(input);
            if(i < 1 || i > getOpciones().size()) i = -1;
        } catch(NumberFormatException x) {
            i = -1;
        }
        if(i == -1) {
            System.out.println("'" + input + "' no es un valor válido");
        } else {
            Menu opcion = getOpciones().get(i-1);
            return opcion.seleccionar(registro, scanner);
        }
        return true;
    }
}
