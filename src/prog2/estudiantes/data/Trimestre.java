package prog2.estudiantes.data;

public class Trimestre {
    private MesTrimestre mes;
    private int anio;

    public Trimestre(MesTrimestre mes, int anio) {
        this.mes = mes;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return mes.formato.replace("%d", "" + anio).replace("%d", "" + (anio+1));
    }
}
