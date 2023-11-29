import java.util.ArrayList;

public class Node {
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

    public String getData() {
        return data;
    }

}