package prog2.estudiantes.menu.reporteria.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;
import java.io.IOException;

public class PDTableCell {
    private final PDTable parent;

    final int column;
    final int row;

    private boolean drawn = false;

    PDTableContent content = null;

    public PDTableCell(PDTable parent, int column, int row) {
        this.parent = parent;
        this.column = column;
        this.row = row;
    }

    public void prepareDrawing() {
        drawn = false;
        if(content != null) content.prepareDrawing();
    }

    public void drawOutline(PDPageContentStream cs, float cellLeft, float cellBottom, float cellWidth, float cellHeight) throws IOException {
        if(drawn) return;
        drawn = true;
        cs.setStrokingColor(Color.BLACK);

        if(this.content == null || parent.getContent(row+1, column) != this.content) {
            cs.moveTo(cellLeft, cellBottom);
            cs.lineTo(cellLeft+cellWidth, cellBottom);
        }
        if(this.content == null || parent.getContent(row, column-1) != this.content) {
            cs.moveTo(cellLeft, cellBottom);
            cs.lineTo(cellLeft, cellBottom+cellHeight);
        }

        if(this.content != null) this.content.updateBounds(cellLeft, cellBottom, cellWidth, cellHeight);

        //cs.addRect(cellLeft, cellBottom, cellWidth, cellHeight);
        //cs.stroke();
    }
}
