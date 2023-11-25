import java.util.LinkedList;
public class precedenceGraph {
    Boolean orientation;
    LinkedList<Node> nodes = new LinkedList<Node>();
    LinkedList<Edge> edges = new LinkedList<Edge>();

    public int getDegree(){
        //TODO complete function
        return 0;
    }

    public boolean isOriented(){
        return orientation;
    }

    public boolean isSimple(){
        //TODO complete function
        return true;
    }

    public void addNode(Node node){
        //TODO complete function
    }

    public void deleteNode(Node node){
        //TODO complete function
    }

    public void addEdge(Node node1, Node node2){
        //TODO complete function
    }

    public void deleteEdge(Node node1, Node node2){
        //TODO complete function
    }

    public boolean areAdyacent(Node node1, Node node2){
        //TODO complete function
        return true;
    }

    public int getNodeDegree(){
        //TODO complete function
        return 0;
    }

    public int getInNodeDegree(){
        //TODO complete function
        return 0;
    }

    public int getOutNodeDegree(){
        //TODO complete function
        return 0;
    }
}
