public class Main {
    public static void main(String[] args) {
        precedenceGraph grafo = new precedenceGraph("src/Grafo.txt");
        precedenceGraph grafo2 = new precedenceGraph("src/Grafo2.txt");
        precedenceGraph grafo3 = new precedenceGraph("src/Grafo3.txt");
        precedenceGraph grafo4 = new precedenceGraph("src/Grafo4.txt");
        grafo.showValidFlows();
        System.out.println();
        grafo2.showValidFlows();
        System.out.println();
        grafo3.showValidFlows();
        System.out.println();
        grafo4.showValidFlows();
    }
}
