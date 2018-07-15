package prog2.estudiantes.menu.reporteria.pdf;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;
import java.io.IOException;

public class PDTable {
    private int rows;
    private int columns;

    private float widthPercent = 0.9f;

    private PDTableCell[][] table;
    private PDTableCell[] flat;

    private float[] horizontalWeightMultipliers;
    private float[] verticalWeightMultipliers;

    public PDTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.table = new PDTableCell[rows][columns];
        this.flat = new PDTableCell[columns*rows];

        for(int j = 0; j < rows; j++) {
            for(int i = 0; i < columns; i++) {
                PDTableCell cell = new PDTableCell(this, i, j);
                table[j][i] = cell;
                flat[rows*i + j] = cell;
            }
        }

        this.horizontalWeightMultipliers = new float[columns];
        for(int i = 0; i < columns; i++) horizontalWeightMultipliers[i] = 1;
        this.verticalWeightMultipliers = new float[rows];
        for(int j = 0; j < rows; j++) verticalWeightMultipliers[j] = 1;
    }

    public void put(int row, int column, PDTableContent content) {
        //System.out.println("putting " + content + " at row " + row + " columnn " + column);
        table[row][column].content = content;
    }

    public void put(int rowA, int columnA, int rowB, int columnB, PDTableContent content) {
        //System.out.println("putting " + content + " at row " + row + " columnn " + column);
        for(int row = rowA; row <= rowB; row++) {
            for(int column = columnA; column <= columnB; column++) {
                table[row][column].content = content;
            }
        }
    }

    public void setHorizontalWeightMultiplier(int column, float multiplier) {
        horizontalWeightMultipliers[column] = multiplier;
    }

    public void setVerticalWeightMultiplier(int row, float multiplier) {
        verticalWeightMultipliers[row] = multiplier;
    }

    public PDTableCell getCell(int row, int column) {
        if(row < 0 || row >= rows || column < 0 || column >= columns) return null;
        return table[row][column];
    }

    public PDTableContent getContent(int row, int column) {
        if(row < 0 || row >= rows || column < 0 || column >= columns) return null;
        return table[row][column].content;
    }

    private void prepareDrawing() {
        for(PDTableCell cell : flat) {
            cell.prepareDrawing();
        }
    }

    public void draw(PDPage page, PDPageContentStream cs) throws IOException {
        float tableWidth = widthPercent * page.getMediaBox().getWidth();
        float tableLeft = (page.getMediaBox().getWidth() - tableWidth)/2;

        float tableHeight = rows * 20;
        float tableBottom = (page.getMediaBox().getHeight() - tableHeight - tableLeft);

        float cellLeft = tableLeft;
        float cellBottom = tableBottom + tableHeight;


        float totalColumnWeight = 0;
        for(int j = 0; j < rows; j++) {
            totalColumnWeight += verticalWeightMultipliers[j];
        }

        for(int j = 0; j < rows; j++) {
            PDTableCell[] row = table[j];

            cellLeft = tableLeft;

            float totalRowWeight = 0;
            for(int i = 0; i < columns; i++) {
                totalRowWeight += horizontalWeightMultipliers[i];
            }

            float cellHeight = tableHeight * ((verticalWeightMultipliers[j])/totalColumnWeight);

            for(int i = 0; i < columns; i++) {
                PDTableCell cell = row[i];

                float cellWidth = tableWidth * ((horizontalWeightMultipliers[i])/totalRowWeight);

                //row is the row number, column is the column number
                //row row, column column

                cell.drawOutline(cs, cellLeft, cellBottom - cellHeight, cellWidth, cellHeight);

                cellLeft += cellWidth;
            }

            cellBottom -= cellHeight;
        }

        for(PDTableCell cell : flat) {
            if(cell.content != null) cell.content.drawContent(cs);
        }

        cs.setStrokingColor(Color.BLACK);
        cs.addRect(tableLeft, tableBottom, tableWidth, tableHeight);
        cs.stroke();
    }
}
