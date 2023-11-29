public class Main {
    public static void main(String[] args) {
        precedenceGraph grafo = new precedenceGraph();
        grafo.graphReader("src/Grafo.txt");
        System.out.println(grafo.adjacencyMatrix.get(4).get(6));
    }
}
