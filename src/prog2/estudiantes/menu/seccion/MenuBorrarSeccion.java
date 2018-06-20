package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBorrarSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Borrar Sección";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Seccion> secciones = registro.secciones;
        int id = Entrada.getInt("Introduzca el ID: ", scanner);
        for(int i = 0; i < secciones.size(); i++) {
            Seccion secc = secciones.get(i);
            if(secc.id == id) {
                secciones.remove(i);
                System.out.println("La seccion " + secc.codigo + " " + secc.trimestre + " ha sido borrada");
                return true;
            }
        }
        System.out.println("Seccion no encontrada");
        return true;
    }
}