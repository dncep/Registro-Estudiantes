package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Horario;
import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuCrearSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Crear Sección";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {

        if(registro.materias.isEmpty()) {
            System.out.println("No se puede crear una sección: no existen materias.");
            return true;
        }

        Seccion seccion = new Seccion();
        seccion.id = registro.ID_SECCION++;

        seccion.trimestre = Entrada.getTrimestre("Introduzca el trimestre", scanner);

        while(seccion.materia == null) {
            String codigo = Entrada.getString("Introduzca el código de la materia: ", scanner);
            for(Materia materia : registro.materias) {
                if(materia.codigo.equals(codigo)) {
                    seccion.materia = materia;
                    break;
                }
            }
            if(seccion.materia == null) System.out.println("No se encontró una materia con ese código");
        }

        seccion.profesor = Entrada.getString("Introduzca el nombre del profesor", scanner);
        seccion.aula = Entrada.getString("Introduzca el aula", scanner);

        seccion.horario = Entrada.editarHorario("Introduzca el horario", new Horario(), scanner);

        int numSeccion = 1;
        for(Seccion otra : registro.secciones) {
            if(otra.materia == seccion.materia && otra.trimestre.equals(seccion.trimestre)) numSeccion++;
        }

        seccion.codigo = seccion.materia.codigo + "-" + numSeccion;

        registro.secciones.add(seccion);
        System.out.println("Sección '" + seccion.codigo + "' para el trimestre '" + seccion.trimestre + "' fue agregada");

        return true;
    }
}
