public class Main {
    public static void main(String[] args) {
        PrecedenceGraph grafo = new PrecedenceGraph("Grafo.txt");
        PrecedenceGraph grafo2 = new PrecedenceGraph("Grafo2.txt");
        PrecedenceGraph grafo3 = new PrecedenceGraph("Grafo3.txt");

        // Grafo 1
        grafo.createPseudocode();
        System.out.println();
        grafo.showValidFlows();

        // Grafo 2
        System.out.println();
        grafo2.createPseudocode();
        System.out.println();
        grafo2.showValidFlows();

        // Grafo 3
        System.out.println();
        grafo3.createPseudocode();
        System.out.println();
        grafo3.showValidFlows();
    }
}
