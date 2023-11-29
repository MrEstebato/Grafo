import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class precedenceGraph {
    // Boolean orientation;
    LinkedList<Node> nodes = new LinkedList<Node>();

    Node root;

    List<List<Integer>> adjacencyMatrix = new ArrayList<>();

    public void graphReader(String filePath) {
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

    }

    public void addEdge(Node node1, Node node2) {
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

    private class Node {
        boolean isRoot;
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
