package prog2.estudiantes.menu;

import prog2.estudiantes.data.Registro;

import java.util.Scanner;

/**
 * Interfaz que representa un menú de texto
 * */
public interface Menu {
    /**
     * El nombre que mostrará este menú en caso de ser presentado como una opción de otro menú.
     *
     * @return El nombre de esta opción, cuando aplique
     * */
    String getNombre();
    /**
     * Este método será llamado cuando sea seleccionado dentro de un menú de opciones.
     *
     * @param registro La lista de usuarios siendo utilizada por el menú
     * @param scanner El objeto Scanner que utilizará el menú para la entrada de información
     *
     * @return true si el menú debe continuar, false si deberá cerrarse después de la ejecución de este método
     * */
    boolean seleccionar(Registro registro, Scanner scanner);
}
