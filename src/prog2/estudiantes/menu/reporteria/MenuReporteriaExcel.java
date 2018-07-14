package prog2.estudiantes.menu.reporteria;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import prog2.estudiantes.data.*;
import prog2.estudiantes.menu.Menu;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class MenuReporteriaExcel implements Menu {
    private final Seccion seccion;

    public MenuReporteriaExcel(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String getNombre() {
        return "Excel";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        File file = new File(System.getProperty("user.home") + File.separator + seccion.codigo + " " + seccion.trimestre + ".xlsx");

        List<Estudiante> estudiantes = seccion.estudiantes;
        try (InputStream is = MenuReporteriaExcel.class.getResourceAsStream("/reporte.xlsx")) {
            try (OutputStream os = new FileOutputStream(file)) {
                Context context = new Context();
                context.putVar("estudiantes", estudiantes);
                context.putVar("seccion", seccion);
                context.putVar("materia", seccion.materia);

                for(Map.Entry<DiaSemana, HoraDia> entry : seccion.horario.getMapa().entrySet()) {
                    if(entry.getValue() != null) {
                        context.putVar("horario" + entry.getKey().corto, entry.getValue().toString());
                    }
                }

                JxlsHelper.getInstance().processTemplate(is, os, context);
            }

            System.out.println("Tabla Excel exportada correctamente");
            Desktop.getDesktop().open(file);
        } catch(IOException x) {
            System.out.println("Ocurrió un error al exportar el reporte: " + x.getMessage());
        }

        return true;
    }
}
