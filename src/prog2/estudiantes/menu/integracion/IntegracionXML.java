package prog2.estudiantes.menu.integracion;

import prog2.estudiantes.data.Registro;

import java.io.File;

public class IntegracionXML implements ModuloIntegracion {
    private Registro registro;

    public IntegracionXML(Registro registro) {
        this.registro = registro;
    }

    @Override
    public void exportarEstudiantes() {

    }

    @Override
    public void exportarMaterias() {

    }

    @Override
    public void importarEstudiantes(File file) {

    }

    @Override
    public void importarMaterias(File file) {

    }
}
