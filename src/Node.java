public class Node {
    String label;
    int inDegree;
    int outDegree;

    public String getLabel() {
        return label;
    }

    public int getDegree() {
        return 0;
    }

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }
}