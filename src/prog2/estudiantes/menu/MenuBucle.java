package prog2.estudiantes.menu;

import prog2.estudiantes.data.Registro;

import java.util.Scanner;

public class MenuBucle implements Menu {
    private Menu menu;

    public MenuBucle(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String getNombre() {
        return menu.getNombre();
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        while(menu.seleccionar(registro, scanner));
        return true;
    }
}
