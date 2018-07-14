package prog2.estudiantes.menu.integracion;

import java.io.File;

public interface ModuloIntegracion {

    void exportarEstudiantes();

    void exportarMaterias();

    void importarEstudiantes(File file);

    void importarMaterias(File file);
}
