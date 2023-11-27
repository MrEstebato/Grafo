import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class precedenceGraph {
    // Boolean orientation;
    LinkedList<Node> nodes = new LinkedList<Node>();
    LinkedList<Edge> edges = new LinkedList<Edge>();

    public int getDegree() {
        // Handle case graph has no nodes (returns -1)
        if (nodes.isEmpty()) {
            return -1;
        }

        Iterator<Node> nodeIterator = nodes.iterator();
        int maxNodeDegree = 0;
        while (nodeIterator.hasNext()) {
            Node currentNode = nodeIterator.next();
            if (maxNodeDegree < currentNode.getDegree()) {
                maxNodeDegree = currentNode.getDegree();
            }
        }
        // In case that graph has one single node, this should return 0
        return maxNodeDegree;
    }

    // public boolean isOriented(){
    // return orientation;
    // }

    public void graphReader() {
        String filePath = "Grafo/src/Grafo.txt";
        List<List<Integer>> adjacencyMatrix = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] characters = line.split(" ");

                List<Integer> row = new ArrayList<>();

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
        for (List<Integer> row : adjacencyMatrix) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public boolean isSimple() {
        // TODO complete function
        return true;
    }

    public void addNode(Node node) {
        // TODO complete function
    }

    public void deleteNode(Node node) {
        // TODO complete function
    }

    public void addEdge(Node node1, Node node2) {
        // TODO complete function
    }

    public void deleteEdge(Node node1, Node node2) {
        // TODO complete function
    }

    public boolean areAdyacent(Node node1, Node node2) {
        // TODO complete function
        return true;
    }

    public int getNodeDegree() {
        // TODO complete function
        return 0;
    }

    public int getInNodeDegree() {
        // TODO complete function
        return 0;
    }

    public int getOutNodeDegree() {
        // TODO complete function
        return 0;
    }
}
