public class Edge {
    Node initialVertex;
    Node finalVertex;
    String label;
    int multiplicity;

    public Node getInitialVertex() {
        return initialVertex;
    }

    public Node getFinalVertex() {
        return finalVertex;
    }

    public String getLabel() {
        return label;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }
}
