package prog2.estudiantes.data;

public enum MesTrimestre {
    FEBRERO("Febrero-Mayo %d"),
    MAYO("Mayo-Agosto %d"),
    AGOSTO("Agosto-Noviembre %d"),
    NOVIEMBRE("Noviembre %d-Febrero %d");

    public String formato;

    MesTrimestre(String formato) {
        this.formato = formato;
    }
}
