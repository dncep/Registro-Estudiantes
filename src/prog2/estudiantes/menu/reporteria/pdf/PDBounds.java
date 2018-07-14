package prog2.estudiantes.menu.reporteria.pdf;

public class PDBounds {
    float left;
    float bottom;
    float right;
    float top;

    public PDBounds(float left, float bottom, float right, float top) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }

    public PDBounds union(PDBounds other) {
        return new PDBounds(
                Math.min(left, other.left),
                Math.min(bottom, other.bottom),
                Math.max(right, other.right),
                Math.max(top, other.top)
        );
    }

    public float getWidth() {
        return right - left;
    }

    public float getHeight() {
        return top - bottom;
    }
}
