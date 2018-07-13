package prog2.estudiantes.menu.reporteria.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.IOException;

public class PDTableText extends PDTableContent {
    public static final byte LEFT = -1;
    public static final byte CENTER = 0;
    public static final byte RIGHT = 1;

    private String content;
    private byte horizontalAlign = LEFT;
    private int style;
    private float fontSize;

    private float margin = 5;

    public PDTableText(String content) {
        this(content, LEFT, Font.PLAIN);
    }

    public PDTableText(String content, byte horizontalAlign) {
        this(content, horizontalAlign, Font.PLAIN);
    }

    public PDTableText(String content, float fontSize) {
        this(content, fontSize, Font.PLAIN);
    }

    public PDTableText(String content, int style) {
        this(content, LEFT, style);
    }

    public PDTableText(String content, byte horizontalAlign, int style) {
        this(content, horizontalAlign, 11f, style);
    }

    public PDTableText(String content, byte horizontalAlign, float fontSize) {
        this(content, horizontalAlign, fontSize, Font.PLAIN);
    }

    public PDTableText(String content, float fontSize, int style) {
        this(content, LEFT, fontSize, style);
    }

    public PDTableText(String content, byte horizontalAlign, float fontSize, int style) {
        this.content = content;
        this.horizontalAlign = horizontalAlign;
        this.fontSize = fontSize;
        this.style = style;
    }

    public void setHorizontalAlign(byte horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    @Override
    void drawContent(PDPageContentStream cs) throws IOException {
        super.drawContent(cs);
        if(drawn) return;
        drawn = true;

        cs.beginText();

        PDType1Font font =
                (style == Font.BOLD) ? PDType1Font.HELVETICA_BOLD :
                        (style == Font.ITALIC) ? PDType1Font.HELVETICA_OBLIQUE :
                                (style == Font.BOLD + Font.ITALIC) ? PDType1Font.HELVETICA_BOLD_OBLIQUE :
                                        PDType1Font.HELVETICA;

        PDFontMetrics fm = new PDFontMetrics(font, fontSize);
        cs.setFont(fm.getFont(), fm.getFontSize());

        float strX = bounds.left;
        float strY = bounds.bottom;

        strY += (bounds.getHeight() - fm.getHeight())/2 + 1;

        if(horizontalAlign == LEFT) {
            strX += margin;
        } else if(horizontalAlign == CENTER) {
            strX += (bounds.getWidth() - fm.stringWidth(content))/2;
        } else if(horizontalAlign == RIGHT) {
            strX = bounds.right - fm.stringWidth(content) - margin;
        }

        cs.setTextMatrix(new Matrix(1, 0, 0, 1, strX, strY));

        cs.showText(content);

        cs.endText();
    }
}
