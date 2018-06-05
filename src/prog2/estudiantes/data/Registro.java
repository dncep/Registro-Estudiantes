package prog2.estudiantes.data;

import prog2.estudiantes.saveio.SaveReader;
import prog2.estudiantes.saveio.SaveWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Registro {
    public final ArrayList<Estudiante> estudiantes = new ArrayList<>();
    public final ArrayList<Materia> materias = new ArrayList<>();
    private final File file;

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
        } catch(IOException x) {
            x.printStackTrace();
        }
    }

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
        } catch(IOException x) {
            x.printStackTrace();
        }
    }
}
