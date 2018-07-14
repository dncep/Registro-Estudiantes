package prog2.estudiantes.menu.reporteria;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import prog2.estudiantes.data.*;
import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.reporteria.pdf.PDTable;
import prog2.estudiantes.menu.reporteria.pdf.PDTableContent;
import prog2.estudiantes.menu.reporteria.pdf.PDTableText;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MenuReporteriaPDF implements Menu {
    private final Seccion seccion;

    public MenuReporteriaPDF(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String getNombre() {
        return "PDF";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        File file = new File(System.getProperty("user.home") + File.separator + seccion.codigo + " " + seccion.trimestre + ".pdf");

        try(PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false);

            PDTable table = new PDTable(7 + seccion.estudiantes.size(), 28);
            table.put(0, 0, 0, 27, new PDTableText("Trimestre: " + seccion.trimestre, Font.BOLD));
            table.put(1,0, new PDTableText("Asignatura:", Font.BOLD));
            table.put(1,1, 1, 27, new PDTableText(seccion.codigo + " " + seccion.materia.nombre));
            table.put(2,0, new PDTableText("Profesor(a):", Font.BOLD));
            table.put(2,1, 2, 27, new PDTableText(seccion.profesor));
            table.put(3,0,4,0, new PDTableText("Horario:", Font.BOLD));

            for(int d = 0; d < DiaSemana.values().length; d++) {
                DiaSemana dia = DiaSemana.values()[d];
                table.put(3,d+1, new PDTableText(dia.corto, PDTableText.CENTER, 10f));
                HoraDia hora = seccion.horario.getMapa().get(dia);
                if(hora != null) table.put(4,d+1, new PDTableText(hora.toString(), PDTableText.CENTER, 8f));
            }

            table.put(3, 7, 4, 27, new PDTableText("Aula: " + seccion.aula, PDTableText.CENTER));

            table.put(5, 0, new PDTableText("ID", PDTableText.CENTER, Font.BOLD));
            table.put(5, 1, 5, 2, new PDTableText("Programa", PDTableText.CENTER, Font.BOLD));
            table.put(5, 3, 5, 6, new PDTableText("Nombre", PDTableText.CENTER, Font.BOLD));
            table.put(5, 7, 5, 26, new PDTableText("Ausencias", PDTableText.CENTER, Font.BOLD));
            table.put(5, 27, new PDTableText("Total", PDTableText.CENTER, 10f, Font.BOLD));
            for(int i = 0; i < seccion.estudiantes.size(); i++) {
                Estudiante est = seccion.estudiantes.get(i);
                int row = 6 + i;
                table.put(row, 0, new PDTableText("" + est.id, 10f));
                table.put(row, 1, row, 2, new PDTableText(est.carrera.getCodigo(), 10f, PDTableText.CENTER));
                table.put(row, 3, row, 6, new PDTableText(est.apellido + ", " + est.nombre, 10f));
                table.setVerticalWeightMultiplier(row, 0.8f);
            }

            table.put(6+ seccion.estudiantes.size(), 0, 6+ seccion.estudiantes.size(), 27, new PDTableText("Total de estudiantes: " + seccion.estudiantes.size(), Font.BOLD));


            table.setHorizontalWeightMultiplier(0, 7);
            table.setHorizontalWeightMultiplier(1, 3);
            table.setHorizontalWeightMultiplier(2, 3);
            table.setHorizontalWeightMultiplier(3, 3);
            table.setHorizontalWeightMultiplier(4, 3);
            table.setHorizontalWeightMultiplier(5, 3);
            table.setHorizontalWeightMultiplier(6, 3);
            table.setHorizontalWeightMultiplier(27, 3);
            table.setVerticalWeightMultiplier(6 + seccion.estudiantes.size(), 0.7f);


            table.draw(page, contentStream);

            contentStream.close();

            doc.save(file);

            Desktop.getDesktop().open(file);
        } catch(IOException x) {
            System.out.println("Ocurrió un error: " + x.getLocalizedMessage());
        }

        return true;
    }
}
