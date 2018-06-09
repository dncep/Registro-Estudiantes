package prog2.estudiantes.data;

import prog2.estudiantes.saveio.SaveReader;
import prog2.estudiantes.saveio.SaveWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Objeto destinado a almacenar los datos del sistema, incluyendo los estudiantes y las materias.
 * */
public class Registro {
    /**
     * Lista para los estudiantes.
     * */
    public final ArrayList<Estudiante> estudiantes = new ArrayList<>();
    /**
     * Lista para las materias.
     * */
    public final ArrayList<Materia> materias = new ArrayList<>();
    /**
     * El fichero en el cual almacenar los datos persistentes.
     * */
    private final File file;

    /**
     * El ID de la siguiente materia a crear.
     * */
    public int ID_MATERIAS = 0;

    /**
     * Construye un registro que se guarda en el fichero dado.
     *
     * @param file El fichero en el cual almacenar los datos persistentes.
     * */
    public Registro(File file) {
        this.file = file;

        if(!file.exists()) {
            try {
                boolean success = file.createNewFile();
            } catch(IOException x) {
                x.printStackTrace();
            }
        }
    }

    /**
     * Borra los datos en este objeto y los llena con los datos presentes en el fichero correspondiente a este registro.
     * */
    public void cargar() {
        estudiantes.clear();
        materias.clear();

        try(FileInputStream fis = new FileInputStream(file)) {
            SaveReader sr = new SaveReader(fis);
            int cantEstudiantes = sr.readByte();
            for(int i = 0; i < cantEstudiantes; i++) {
                Estudiante est = new Estudiante();
                est.nombre = sr.readString();
                est.apellido = sr.readString();
                est.fechaNacimiento = sr.readDate();
                est.estado = Estado.values()[sr.readByte()];
                est.id = sr.readInt();
                est.carrera = Carrera.values()[sr.readByte()];
                est.cedula = Cedula.crearCedula(sr.readString());
                est.esExtranjero = sr.readBoolean();
                estudiantes.add(est);
            }

            ID_MATERIAS = sr.readByte();
            int cantMaterias = sr.readByte();
            for(int i = 0; i < cantMaterias; i++) {
                Materia mat = new Materia();
                mat.codigo = sr.readString();
                mat.nombre = sr.readString();
                mat.area = AreaAcademica.values()[sr.readByte()];
                mat.id = sr.readByte();
                materias.add(mat);
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

    /**
     * Escribe los datos de este registro en el fichero correspondiente.
     * */
    public void guardar() {

        try(FileOutputStream fos = new FileOutputStream(file)) {
            SaveWriter sw = new SaveWriter(fos);
            //Estudiantes
            sw.writeByte(estudiantes.size());

            for(Estudiante est : estudiantes) {
                sw.writeString(est.nombre);
                sw.writeString(est.apellido);
                sw.writeDate(est.fechaNacimiento);
                sw.writeByte(est.estado.ordinal());
                sw.writeInt(est.id);
                sw.writeByte(est.carrera.ordinal());
                sw.writeString(est.cedula.toString());
                sw.writeBoolean(est.esExtranjero);
            }

            sw.writeByte(ID_MATERIAS);
            sw.writeByte(materias.size());

            for(Materia mat : materias) {
                sw.writeString(mat.codigo);
                sw.writeString(mat.nombre);
                sw.writeByte(mat.area.ordinal());
                sw.writeByte(mat.id);
            }
        } catch(IOException x) {
            x.printStackTrace();
        }
    }
}
