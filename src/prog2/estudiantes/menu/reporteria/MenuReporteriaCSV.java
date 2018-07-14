package prog2.estudiantes.menu.reporteria;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuReporteriaCSV implements Menu {
    private final Seccion seccion;

    public MenuReporteriaCSV(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String getNombre() {
        return "CSV";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        File file = new File(System.getProperty("user.home") + File.separator + seccion.codigo + " " + seccion.trimestre + ".csv");

        try {
            file.createNewFile();
        } catch(IOException x) {
            System.out.println("No se pudo crear el archivo: " + x.getMessage());
            return true;
        }

        try(PrintWriter pw = new PrintWriter(new FileOutputStream(file))) {
            pw.println("ID,Programa,Nombre");


            for(Estudiante est : seccion.estudiantes) {
                pw.print(est.id);
                pw.print(',');
                pw.print(est.carrera.getCodigo());
                pw.print(',');
                pw.print('"');
                pw.print(est.apellido.toUpperCase());
                pw.print(", ");
                pw.print(est.nombre.toUpperCase());
                pw.print('"');
                pw.println();
            }

            System.out.println("CSV exportado correctamente");
            Desktop.getDesktop().open(file);
        } catch(IOException x) {
            System.out.println("Ocurrió un error al exportar el reporte: " + x.getMessage());
        }
        return true;
    }
}
