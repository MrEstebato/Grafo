public class Main {
    public static void main(String[] args) {
        PrecedenceGraph grafo = new PrecedenceGraph("src/Grafo.txt");
        PrecedenceGraph grafo2 = new PrecedenceGraph("src/Grafo2.txt");
        PrecedenceGraph grafo3 = new PrecedenceGraph("src/Grafo3.txt");
        PrecedenceGraph grafo4 = new PrecedenceGraph("src/Grafo4.txt");
        grafo.showValidFlows();
        System.out.println();
        grafo2.showValidFlows();
        System.out.println();
        grafo3.showValidFlows();
        System.out.println();
        grafo4.showValidFlows();
    }
}
