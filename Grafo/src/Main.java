public class Main {
    public static void main(String[] args) {
        precedenceGraph grafo = new precedenceGraph();
        grafo.graphReader();
        System.out.println(grafo.adjacencyMatrix.get(0).get(0));
    }
}
