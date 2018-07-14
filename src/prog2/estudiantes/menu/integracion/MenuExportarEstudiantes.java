package prog2.estudiantes.menu.integracion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuExportarEstudiantes implements MenuEstandar {

    private List<Menu> opciones = new ArrayList<>();

    {
        opciones.add(new Menu() {
            @Override
            public String getNombre() {
                return "XML";
            }

            @Override
            public boolean seleccionar(Registro registro, Scanner scanner) {
                try {
                    registro.integracionXML.exportarEstudiantes();
                } catch(IOException x) {
                    x.printStackTrace();
                }
                return true;
            }
        });
        opciones.add(new Menu() {
            @Override
            public String getNombre() {
                return "JSON";
            }

            @Override
            public boolean seleccionar(Registro registro, Scanner scanner) {
                try {
                    registro.integracionJSON.exportarEstudiantes();
                } catch(IOException x) {
                    x.printStackTrace();
                }
                return true;
            }
        });
    }

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }

    @Override
    public String getNombre() {
        return "Estudiantes";
    }
}
