package prog2.estudiantes.data;

public class Materia {

    private static int SIGUIENTE_ID = 0;

    private int id;
    public AreaAcademica area;
    public String codigo;
    public String nombre;

    public Materia(AreaAcademica area, String codigo, String nombre) {
        this.id = SIGUIENTE_ID++;
        this.area = area;
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
