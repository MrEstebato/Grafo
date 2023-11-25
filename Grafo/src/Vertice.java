public class Vertice {
    String etiqueta;
    int gradoInterior;
    int gradoExterior;

    public String getEtiqueta(){
        return etiqueta;
    }

    public int getGrado(){
        return 0;
    }

    public int getGradoInterior(){
        return gradoInterior;
    }

    public int getGradoExterior(){
        return gradoExterior;
    }

    public void setGradoExterior(int gradoExterior) {
        this.gradoExterior = gradoExterior;
    }

    public void setGradoInterior(int gradoInterior) {
        this.gradoInterior = gradoInterior;
    }
}
