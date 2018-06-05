package prog2.estudiantes.menu.materias;

import java.util.ArrayList;
import java.util.Scanner;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Util;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

public class MenuBuscarMateria implements Menu {
    @Override
    public String getNombre() {
        return "Buscar Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Materia> materias = registro.materias;
        String busca = Entrada.getString("Digite el codigo o nombre de la materia", scanner);
        boolean existe= false;
        for(Materia materia : materias) {
            if(materia.codigo.equalsIgnoreCase(busca) || Util.normalizar(materia.nombre).toLowerCase().contains(busca.toLowerCase())) {
                existe=true;
                System.out.println(materia.codigo + " - " + materia.nombre);
                System.out.println("\tID: " + materia.id + " | Área: " + materia.area.getNombre());
            }
        }
        if(!existe) {
            System.out.println("Código o nombre no encontrado");
        }
        return true;
    }
}