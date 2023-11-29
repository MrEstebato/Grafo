import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<ArrayList<Integer>> matrixReader(String filePath) {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] characters = line.split(" ");
                ArrayList<Integer> row = new ArrayList<>();
                for (String character : characters) {
                    if (!character.trim().isEmpty()) {
                        row.add(Integer.parseInt(character));
                    }
                }
                adjacencyMatrix.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adjacencyMatrix;
    }

    public static void showValidFlows(precedenceGraph grafo, Node nodoActual, List<Node> caminoActual) {
        caminoActual.add(nodoActual);

        if (nodoActual.children.isEmpty()) {

            for (Node node : caminoActual) {
                System.out.print(node.getData() + "; ");
            }
            System.out.println();
        } else {
            for (Node hijo : nodoActual.children) {
                showValidFlows(grafo, hijo, new ArrayList<>(caminoActual));
            }
        }
    }
    public static void main(String[] args) {
        String filePath = "Grafo.txt";
        ArrayList<ArrayList<Integer>> adjacencyMatrix = matrixReader(filePath);
        precedenceGraph grafo = new precedenceGraph();
        int nodeCounter = 1;
        for(int i=0; i<adjacencyMatrix.get(0).size(); i++){
            Node addedNode = new Node("S"+nodeCounter);
            grafo.addNode(addedNode);
            nodeCounter++;
        }

        for(int j=0; j<grafo.getTotalNodes(); j++){
            for(int k=0; k<adjacencyMatrix.get(0).size(); k++){
                if(adjacencyMatrix.get(j).get(k) == 1){
                    grafo.addEdge(grafo.nodes.get(j), grafo.nodes.get(k));
                }
            }
        }

        /*for(int x=0; x<grafo.getTotalNodes(); x++){
            System.out.println("Children of " + grafo.nodes.get(x).getData() + ": ");
            for(Node node: grafo.nodes.get(x).children){
                System.out.println(node.getData());
            }
        }*/

        showValidFlows(grafo, grafo.nodes.getFirst(), new ArrayList<>());
    }

}
