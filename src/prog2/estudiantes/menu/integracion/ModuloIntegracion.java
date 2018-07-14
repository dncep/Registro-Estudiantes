package prog2.estudiantes.menu.integracion;

import java.io.File;
import java.io.IOException;

public interface ModuloIntegracion {

    void exportarEstudiantes() throws IOException;

    void exportarMaterias() throws IOException;

    void importar(File file) throws IOException;
}
