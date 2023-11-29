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

    Node root;

    //List<List<Integer>> adjacencyMatrix = new ArrayList<>();

    /*
     * public int getDegree() {
     * // Handle case graph has no nodes (returns -1)
     * if (nodes.isEmpty()) {
     * return -1;
     * }
     * 
     * Iterator<Node> nodeIterator = nodes.iterator();
     * int maxNodeDegree = 0;
     * while (nodeIterator.hasNext()) {
     * Node currentNode = nodeIterator.next();
     * if (maxNodeDegree < currentNode.getDegree()) {
     * maxNodeDegree = currentNode.getDegree();
     * }
     * }
     * // In case that graph has one single node, this should return 0
     * return maxNodeDegree;
     * }
     */

    // public boolean isOriented(){
    // return orientation;
    // }

    public boolean isSimple() {
        // TODO complete function
        return true;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2) {
        node1.children.add(node2);
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

    public int getTotalNodes(){
        return nodes.size();
    }
}
