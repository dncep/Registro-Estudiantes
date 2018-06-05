package prog2.estudiantes.saveio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

public class SaveReader {

    private final FileInputStream fis;

    public SaveReader(FileInputStream fis) {
        this.fis = fis;
    }

    public int readInt() throws IOException {
        int n = 0;
        for(int i = 0; i < 4; i++) {
            n <<= 8;
            n += readByte();
        }
        return n;
    }

    public int readByte() throws IOException {
        int n = fis.read();
        return n;
    }

    public String readString() throws IOException {
        int len = readInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.append((char) readInt());
        }
        return sb.toString();
    }

    public Calendar readDate() throws IOException {
        int year = readInt();
        int month = readByte();
        int day = readByte();
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        return date;
    }

    public boolean readBoolean() throws IOException {
        return readByte() == 1;
    }

    public void close() throws IOException {
        fis.close();
    }
}
