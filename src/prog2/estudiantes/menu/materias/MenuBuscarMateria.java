package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Util;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBuscarMateria implements Menu {
    @Override
    public String getNombre() {
        return "Buscar Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Materia> materias = registro.materias;
        String busca;

        while("?".equals(busca = Util.normalizar(Entrada.getString("Digite el codigo, nombre o area de la materia*", scanner)).toLowerCase())) {
            Entrada.ayudaArea();
        }

        boolean existe = false;
        for(Materia materia : materias) {
            if(materia.codigo.equalsIgnoreCase(busca) ||
                    Util.normalizar(materia.nombre).toLowerCase().contains(busca) ||
                    Util.normalizar(materia.area.getNombre()).toLowerCase().contains(busca) ||
                    materia.area.getCodigo().toLowerCase().equals(busca)) {
                existe = true;
                System.out.println(materia.codigo + " - " + materia.nombre);
                System.out.println("\tID: " + materia.id + " | Área: " + materia.area.getNombre());
            }
        }
        if(!existe) {
            System.out.println("No hay materias con esas condiciones");
        }
        return true;
    }
}