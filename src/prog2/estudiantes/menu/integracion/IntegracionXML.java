package prog2.estudiantes.menu.integracion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import prog2.estudiantes.data.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class IntegracionXML implements ModuloIntegracion {
    private Registro registro;
    private int ID_MATERIA_TEMP = -1;

    public IntegracionXML(Registro registro) {
        this.registro = registro;
    }

    @Override
    public void exportarEstudiantes() throws IOException {

    }

    @Override
    public void exportarMaterias() throws IOException {

    }

    public void importar(File file) throws IOException {
        Scanner sc = new Scanner(file);

        ID_MATERIA_TEMP = registro.ID_MATERIA;

        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append('\n');
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(sb.toString())));

            Element root = doc.getDocumentElement();

            if(root.getTagName().equals("estudiantes")) {
                ArrayList<Estudiante> nuevosEstudiantes = new ArrayList<>();
                NodeList nl = root.getElementsByTagName("estudiante");
                for(int i = 0; i < nl.getLength(); i++) {
                    Node item = nl.item(i);
                    if(item instanceof Element) {
                        nuevosEstudiantes.add(parseEstudiante((Element) item));
                    }
                }

                registro.estudiantes.addAll(nuevosEstudiantes);

                System.out.println("Agregado " + nuevosEstudiantes.size() + " estudiantes al registro");
            } else if(root.getTagName().equals("materias")) {
                ArrayList<Materia> nuevasMaterias = new ArrayList<>();
                NodeList nl = root.getElementsByTagName("materia");
                for(int i = 0; i < nl.getLength(); i++) {
                    Node item = nl.item(i);
                    if(item instanceof Element) {
                        nuevasMaterias.add(parseMateria((Element) item));
                    }
                }

                registro.materias.addAll(nuevasMaterias);
                registro.ID_MATERIA = ID_MATERIA_TEMP;

                System.out.println("Agregado " + nuevasMaterias.size() + " materias al registro");
            }

        } catch (ParserConfigurationException | SAXException x) {
            x.printStackTrace();
        } catch(MalformedInputException x) {
            System.out.println("Ocurrió un error al importar: " + x.getMessage());
        }
    }

    private Estudiante parseEstudiante(Element elem) {
        Estudiante est = new Estudiante();
        est.nombre = getStringElement(elem, "nombre");
        est.apellido  = getStringElement(elem, "apellido");
        est.id = getIntegerElement(elem, "id");
        est.estado = getEnumElement(elem, "estado", Estado.values());
        est.carrera = getEnumElement(elem, "carrera", Carrera.values());
        est.esExtranjero = getBooleanElement(elem, "extranjero");
        String rawDate = getStringElement(elem, "fechaNacimiento");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            est.fechaNacimiento = Calendar.getInstance();
            est.fechaNacimiento.setTime(sdf.parse(rawDate));
        } catch(ParseException x) {
            throw new MalformedInputException("La etiqueta 'fechaNacimiento' debe contener una fecha dd-mm-aaaa, tiene '" + rawDate + "'");
        }
        String rawCedula = getStringElement(elem, "cedula");
        try {
            est.cedula = Cedula.crearCedula(rawCedula);
        } catch(IllegalArgumentException x) {
            throw new MalformedInputException("La etiqueta 'cedula' debe contener una cédula, formato XXXXXXXXXX ó XXX-XXXXXXX-X, tiene " + rawCedula);
        }
        return est;
    }

    private Materia parseMateria(Element elem) {
        Materia mat = new Materia();
        mat.nombre = getStringElement(elem, "nombre");
        mat.codigo = getStringElement(elem, "codigo");
        mat.area = getEnumElement(elem, "area", AreaAcademica.values());
        mat.id = ID_MATERIA_TEMP++;
        return mat;
    }

    private String getStringElement(Element elem, String tagName) {
        String content = getElementByTagName(elem, tagName).getTextContent();
        if(content.length() == 0) throw new MalformedInputException("La etiqueta '" + tagName + "' está vacía");
        return content;
    }

    private int getIntegerElement(Element elem, String tagName) {
        String content = getElementByTagName(elem, tagName).getTextContent();
        if(content.length() == 0) throw new MalformedInputException("La etiqueta '" + tagName + "' está vacía");
        try {
            return Integer.parseInt(content);
        } catch(NumberFormatException x) {
            throw new MalformedInputException("La etiqueta '" + tagName + "' debe contener un valor entero, tiene '" + content + "'");
        }
    }

    private boolean getBooleanElement(Element elem, String tagName) {
        String content = getElementByTagName(elem, tagName).getTextContent();
        if(content.length() == 0) throw new MalformedInputException("La etiqueta '" + tagName + "' está vacía");
        switch(content) {
            case "true": return true;
            case "false": return false;
            default: throw new MalformedInputException("La etiqueta '" + tagName + "' debe contener un valor booleano (true/false), tiene '" + content + "'");
        }
    }

    private <T extends Enum<T>> T getEnumElement(Element elem, String tagName, T[] values) {
        String content = getElementByTagName(elem, tagName).getTextContent();
        if(content.length() == 0) throw new MalformedInputException("La etiqueta '" + tagName + "' está vacía");
        for(T value : values) {
            if(value.name().equals(content)) return value;
        }
        throw new MalformedInputException("La etiqueta '" + tagName + "' debe contener un valor del enum " + values[0].getDeclaringClass().getSimpleName() + ", tiene '" + content + "'");
    }

    private Element getElementByTagName(Element elem, String tagName) {
        NodeList nl = elem.getElementsByTagName(tagName);
        for(int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if(node instanceof Element) {
                return (Element) node;
            }
        }
        throw new MalformedInputException("No se encontró etiqueta requerida '" + tagName + "'");
    }
}
