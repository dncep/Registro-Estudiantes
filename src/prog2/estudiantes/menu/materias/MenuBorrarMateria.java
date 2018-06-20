package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBorrarMateria implements Menu {
    @Override
    public String getNombre() {
        return "Borrar Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Materia> materias = registro.materias;
        String codigo = Entrada.getString("Introduzca el codigo de la materia", scanner);
        for(int i = 0; i < materias.size(); i++) {
            Materia mat = materias.get(i);
            if(mat.codigo.equals(codigo)) {
                materias.remove(i);
                System.out.println("Materia " + mat.nombre + " ha sido borrada");

                //Borrar seccion

                registro.secciones.removeIf(s -> {
                    if(s.materia == mat) {
                        System.out.println("    Fue borrada la sección " + s.codigo + " - " + s.trimestre);
                        return true;
                    }
                    return false;
                });

                return true;
            }
        }
        System.out.println("Materia no encontrada");
        return true;
    }
}