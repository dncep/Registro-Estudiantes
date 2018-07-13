package prog2.estudiantes.menu.reporteria.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public class PDFontMetrics {

    private PDFont font;
    private float fontSize;

    public PDFontMetrics(PDFont font, float fontSize) {
        this.font = font;
        this.fontSize = fontSize;
    }

    public PDFont getFont() {
        return font;
    }

    public float getFontSize() {
        return fontSize;
    }

    /*public int getAscent() {
        return font.getHeight();
    }

    public int getDescent() {
        return super.getDescent();
    }*/

    public float getHeight() {
        return fontSize;
    }

    public float stringWidth(String text) throws IOException {
        return font.getStringWidth(text) / 1000 * fontSize;
    }
}
