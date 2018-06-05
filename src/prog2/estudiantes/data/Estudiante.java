package prog2.estudiantes.data;

import java.util.Calendar;

/**
 * Representación de un estudiante como objeto.
 * */
public class Estudiante {
    public String nombre;
    public String apellido;
    public Calendar fechaNacimiento;
    public Estado estado;
    public int id;
    public Carrera carrera;
    public Cedula cedula;
    public boolean esExtranjero = false;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, Calendar fechaNacimiento, Estado estado, int id, Carrera carrera, Cedula cedula, boolean esExtranjero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.id = id;
        this.carrera = carrera;
        this.cedula = cedula;
        this.esExtranjero = esExtranjero;
    }
}
