public class Lado {
    Vertice verticeInicial;
    Vertice verticeFinal;
    String etiqueta;
    int multiplicidad;

    public Vertice getVerticeInicial() {
        return verticeInicial;
    }

    public Vertice getVerticeFinal() {
        return verticeFinal;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int getMultiplicidad() {
        return multiplicidad;
    }

    public void setMultiplicidad(int multiplicidad) {
        this.multiplicidad = multiplicidad;
    }
}
