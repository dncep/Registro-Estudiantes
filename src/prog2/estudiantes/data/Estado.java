package prog2.estudiantes.data;

/**
 * Enumeración que posee los estados en los que puede estar un estudiante del INTEC.
 * */
public enum Estado {
    NUEVO("Nuevo"),
    ACTIVO("Activo"),
    A_PRUEBA_ACADEMICA("A prueba académica"),
    INACTIVO("Inactivo");

    /**
     * El nombre legible del estado
     * */
    private String legible;

    /**
     * Crea un objeto Estado
     *
     * @param legible El nombre legible del estado
     * */
    Estado(String legible) {
        this.legible = legible;
    }

    @Override
    public String toString() {
        return legible;
    }
}
