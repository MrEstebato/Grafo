import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class precedenceGraph {
    private LinkedList<Node> nodes;
    private List<List<Integer>> adjacencyMatrix;
    private List<Node> flows;

    private Node root;

    public precedenceGraph(String filePath) {
        adjacencyMatrix = new ArrayList<>();
        nodes = new LinkedList<Node>();
        flows = new ArrayList<>();

        graphReader(filePath);

        for (int i = 0; i < adjacencyMatrix.get(0).size(); i++) {
            Node addedNode = new Node("S" + (i + 1));
            addNode(addedNode);
        }

        root = nodes.get(0);

        for (int j = 0; j < nodes.size(); j++) {
            for (int k = 0; k < adjacencyMatrix.get(0).size(); k++) {
                if (adjacencyMatrix.get(j).get(k) == 1) {
                    addEdge(nodes.get(j), nodes.get(k));
                }
            }
        }

        for (int j = 0; j < nodes.size(); j++) {
            int count = 0;
            for (int i = 0; i < nodes.size(); i++) {
                count += adjacencyMatrix.get(i).get(j);
            }
            if (count == 0) { 
                root = nodes.get(j);
                break;
            }
        }
    }

    private void graphReader(String filePath) {
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
    }

    public void showValidFlows() {
        getValidFlows(this, root, flows);
    }

    private void getValidFlows(precedenceGraph grafo, Node nodoActual, List<Node> caminoActual) {
        caminoActual.add(nodoActual);

        if (nodoActual.children.isEmpty()) {

            for (Node node : caminoActual) {
                System.out.print(node.getData() + "; ");
            }
            System.out.println();
        } else {
            for (Node hijo : nodoActual.children) {
                getValidFlows(grafo, hijo, new ArrayList<>(caminoActual));
            }
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2) {
        node1.addChild(node2);
    }

    public Node getRoot() {
        return root;
    }

    private class Node {
        String data;

        ArrayList<Node> children;

        public Node(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}