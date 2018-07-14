package prog2.estudiantes.menu.reporteria.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PDTableContent {

    boolean drawn = false;
    PDBounds bounds;

    void prepareDrawing() {
        drawn = false;
        bounds = null;
    }

    final void updateBounds(float cellLeft, float cellBottom, float cellWidth, float cellHeight) {
        PDBounds cellBounds = new PDBounds(cellLeft, cellBottom, cellLeft + cellWidth, cellBottom + cellHeight);
        if(bounds == null) bounds = cellBounds;
        else {
            bounds = bounds.union(cellBounds);
        }
    }

    void drawContent(PDPageContentStream cs) throws IOException {
    }
}
