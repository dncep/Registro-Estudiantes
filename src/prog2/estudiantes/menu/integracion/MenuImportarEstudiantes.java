package prog2.estudiantes.menu.integracion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuImportarEstudiantes implements MenuEstandar {
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
                    registro.integracionXML.importar(new File(Entrada.getString("Introduzca la ruta al archivo", scanner)));
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
                    registro.integracionJSON.importar(new File(Entrada.getString("Introduzca la ruta al archivo", scanner)));
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
