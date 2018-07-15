package prog2.estudiantes.menu.integracion;

import com.google.gson.*;
import prog2.estudiantes.data.*;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class IntegracionJSON implements ModuloIntegracion {
    private final Registro registro;
    private int ID_MATERIA_TEMP;

    public IntegracionJSON(Registro registro) {
        this.registro = registro;
    }

    @Override
    public void exportarEstudiantes() throws IOException {
        File file = new File(System.getProperty("user.home") + File.separator + "estudiantes.json");

        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();

        JsonObject root = new JsonObject();

        JsonArray arr = new JsonArray();

        root.add("estudiantes", arr);

        for(Estudiante est : registro.estudiantes) {
            JsonObject elem = new JsonObject();
            elem.add("nombre", new JsonPrimitive(est.nombre));
            elem.add("apellido", new JsonPrimitive(est.apellido));
            elem.add("id", new JsonPrimitive(est.id));
            elem.add("estado", new JsonPrimitive(est.estado.name()));
            elem.add("carrera", new JsonPrimitive(est.carrera.name()));
            elem.add("cedula", new JsonPrimitive(est.cedula.toString()));
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            elem.add("fecha_nacimiento", new JsonPrimitive(df.format(est.fechaNacimiento.getTime())));
            elem.add("extranjero", new JsonPrimitive(est.esExtranjero));
            arr.add(elem);
        }

        try(FileWriter fw = new FileWriter(file)){
            fw.write(gson.toJson(root));
        }

        Desktop.getDesktop().open(file);
    }

    @Override
    public void exportarMaterias() throws IOException {
        File file = new File(System.getProperty("user.home") + File.separator + "materias.json");

        /*
        *
        * ...
        *
        * */

        Desktop.getDesktop().open(file);
    }

    @Override
    public void importar(File file) throws IOException {
        Gson gson = new Gson();

        try {
            JsonObject root = gson.fromJson(new FileReader(file), JsonElement.class).getAsJsonObject();
            JsonElement elem;
            if ((elem = root.get("estudiantes")) != null) {
                ArrayList<Estudiante> nuevosEstudiantes = new ArrayList<>();

                JsonArray arr = elem.getAsJsonArray();
                for(JsonElement inner : arr) {
                    if(inner.isJsonObject()) nuevosEstudiantes.add(parseEstudiante(inner.getAsJsonObject()));
                }

                registro.estudiantes.addAll(nuevosEstudiantes);

                System.out.println("Agregado " + nuevosEstudiantes.size() + " estudiantes al registro");
            }
            if ((elem = root.get("materias")) != null) {
                ArrayList<Materia> nuevasMaterias = new ArrayList<>();
                ID_MATERIA_TEMP = registro.ID_MATERIA;

                JsonArray arr = elem.getAsJsonArray();
                for(JsonElement inner : arr) {
                    if(inner.isJsonObject()) nuevasMaterias.add(parseMateria(inner.getAsJsonObject()));
                }

                registro.materias.addAll(nuevasMaterias);
                registro.ID_MATERIA = ID_MATERIA_TEMP;

                System.out.println("Agregado " + nuevasMaterias.size() + " materias al registro");
            }
        } catch(MalformedInputException x) {
            System.out.println("Ocurrió un error al importar: " + x.getMessage());
        }
    }

    private Estudiante parseEstudiante(JsonObject obj) {
        Estudiante est = new Estudiante();
        est.nombre = getStringElement(obj, "nombre");
        est.apellido = getStringElement(obj, "apellido");
        est.id = getIntElement(obj, "id");

        String rawDate = getStringElement(obj, "fecha_nacimiento");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            est.fechaNacimiento = Calendar.getInstance();
            est.fechaNacimiento.setTime(sdf.parse(rawDate));
        } catch(ParseException x) {
            throw new MalformedInputException("La etiqueta 'fecha_nacimiento' debe contener una fecha dd-mm-aaaa, tiene '" + rawDate + "'");
        }
        String rawCedula = getStringElement(obj, "cedula");
        try {
            est.cedula = Cedula.crearCedula(rawCedula);
        } catch(IllegalArgumentException x) {
            throw new MalformedInputException("La etiqueta 'cedula' debe contener una cédula, formato XXXXXXXXXX ó XXX-XXXXXXX-X, tiene " + rawCedula);
        }

        est.estado = getEnumElement(obj, "estado", Estado.values());
        est.carrera = getEnumElement(obj, "carrera", Carrera.values());
        est.esExtranjero = getBooleanElement(obj, "extranjero");

        return est;
    }

    private Materia parseMateria(JsonObject obj) {
        Materia mat = new Materia();
        mat.nombre = getStringElement(obj, "nombre");
        mat.codigo = getStringElement(obj, "codigo");
        mat.area = getEnumElement(obj, "area", AreaAcademica.values());
        mat.id = ID_MATERIA_TEMP++;

        return mat;
    }

    private <T extends Enum<T>> T getEnumElement(JsonObject obj, String key, T[] values) {
        String content = getStringElement(obj, key);
        for(T value : values) {
            if(value.name().equals(content)) return value;
        }
        throw new MalformedInputException("El elemento '" + key + "' debe contener un valor del enum " + values[0].getDeclaringClass().getSimpleName() + ", tiene '" + content + "'");
    }

    private String getStringElement(JsonObject obj, String key) {
        JsonElement elem = getElementByKey(obj, key);
        if(elem.isJsonPrimitive() && elem.getAsJsonPrimitive().isString()) {
            String content = elem.getAsString();
            if(content.length() == 0) throw new MalformedInputException("El elemento string '" + key + "' está vacía");
            return content;
        } else {
            throw new MalformedInputException("El elemento '" + key + "' debe contener un valor string");
        }
    }

    private int getIntElement(JsonObject obj, String key) {
        JsonElement elem = getElementByKey(obj, key);
        if(elem.isJsonPrimitive() && elem.getAsJsonPrimitive().isNumber()) {
            return elem.getAsInt();
        } else {
            throw new MalformedInputException("El elemento '" + key + "' debe contener un valor int");
        }
    }

    private boolean getBooleanElement(JsonObject obj, String key) {
        JsonElement elem = getElementByKey(obj, key);
        if(elem.isJsonPrimitive() && elem.getAsJsonPrimitive().isBoolean()) {
            return elem.getAsBoolean();
        } else {
            throw new MalformedInputException("El elemento '" + key + "' debe contener un valor boolean");
        }
    }

    private JsonElement getElementByKey(JsonObject obj, String key) {
        JsonElement elem = obj.get(key);
        if(elem == null) throw new MalformedInputException("El elemento requerido '" + key + "' no existe");
        return elem;
    }
}
