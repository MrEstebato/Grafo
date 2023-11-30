import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un grafo de precedencia y proporciona métodos para encontrar flujos válidos.
 */
public class PrecedenceGraph {

    // Listas y nodos para representar el grafo.
    private LinkedList<Node> nodes;
    private List<List<Integer>> adjacencyMatrix;
    private ArrayList<Node> flows;

    // Nodo raíz del grafo.
    private Node root;

    /**
     * Constructor de la clase PrecedenceGraph.
     *
     * @param filePath Ruta del archivo que contiene la información para construir el grafo.
     */
    public PrecedenceGraph(String filePath) {
        adjacencyMatrix = new ArrayList<>();
        nodes = new LinkedList<Node>();
        flows = new ArrayList<>();

        // Leer y construir el grafo desde el archivo.
        graphReader(filePath);

        // Crear nodos iniciales y establecer la raíz.
        for (int i = 0; i < adjacencyMatrix.get(0).size(); i++) {
            Node addedNode = new Node("S" + (i + 1));
            addNode(addedNode);
        }

        // Establecer conexiones entre nodos según la matriz de adyacencia.
        for (int j = 0; j < nodes.size(); j++) {
            for (int k = 0; k < adjacencyMatrix.get(0).size(); k++) {
                if (adjacencyMatrix.get(j).get(k) == 1) {
                    addEdge(nodes.get(j), nodes.get(k));
                }
            }
        }

        // Encontrar la raíz del grafo.
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

    /**
     * Lee la información del grafo desde un archivo y construye la matriz de adyacencia.
     *
     * @param filePath Ruta del archivo que contiene la información del grafo.
     */
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

    /**
     * Muestra los flujos válidos en el grafo.
     */
    public void showValidFlows() {
        getValidFlows(this, root, flows);
    }

    /**
     * Recursivamente encuentra y muestra los flujos válidos en el grafo.
     *
     * @param grafo       Objeto de tipo PrecedenceGraph que representa el grafo.
     * @param currentNode  Nodo actual durante la recursión.
     * @param currentFlow Lista que representa el camino actual durante la recursión.
     */
    private void getValidFlows(PrecedenceGraph grafo, Node currentNode, ArrayList<Node> currentFlow) {
        currentFlow.add(currentNode);

        if (currentNode.children.isEmpty()) {
            // Mostrar el flujo válido.
            for (Node node : currentFlow) {
                System.out.print(node.getData() + "; ");
            }
            System.out.println();
        } else {
            // Continuar la recursión para los nodos hijos.
            for (Node child : currentNode.children) {
                getValidFlows(grafo, child, new ArrayList<>(currentFlow));
            }
        }
    }

    /**
     * Agrega un nodo al grafo.
     *
     * @param node Nodo a agregar al grafo.
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Agrega una conexión entre dos nodos en el grafo.
     *
     * @param node1 Nodo origen.
     * @param node2 Nodo destino.
     */
    public void addEdge(Node node1, Node node2) {
        node1.addChild(node2);
    }

    /**
     * Obtiene la raíz del grafo.
     *
     * @return Nodo raíz del grafo.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Elimina la arista dirigida desde el nodo con datos 'data1' al nodo con datos 'data2' en el grafo.
     *
     * @param data1 Datos del nodo origen.
     * @param data2 Datos del nodo destino.
     * @return true si la operación fue exitosa, false si al menos uno de los nodos no existe en el grafo.
     */
    public boolean removeEdge(String data1, String data2) {
        // Buscar los nodos correspondientes en el grafo.
        Node node1 = findNodeByData(data1);
        Node node2 = findNodeByData(data2);

        // Verificar si ambos nodos existen en el grafo.
        if (node1 != null && node2 != null) {
            // Eliminar la arista dirigida desde node1 hacia node2.
            node1.removeChild(node2);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un nodo por sus datos en el grafo.
     *
     * @param data Datos del nodo a buscar.
     * @return Nodo correspondiente o null si no se encuentra.
     */
    private Node findNodeByData(String data) {
        // Iterar sobre los nodos para encontrar el que coincide con los datos proporcionados.
        for (Node node : nodes) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Clase interna que representa un nodo en el grafo.
     */
    private class Node {
        String data;

        ArrayList<Node> children;

        /**
         * Constructor de la clase Node.
         *
         * @param data Datos asociados al nodo.
         */
        public Node(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        /**
         * Agrega un nodo hijo al nodo actual.
         *
         * @param child Nodo hijo a agregar.
         */
        public void addChild(Node child) {
            children.add(child);
        }

        /**
         * Elimina el nodo proporcionado de la lista de nodos hijos del nodo actual.
         *
         * @param child Nodo hijo a eliminar.
         */
        public void removeChild(Node child) {
            children.remove(child);
        }


        /**
         * Obtiene los datos asociados al nodo.
         *
         * @return Datos del nodo.
         */
        public String getData() {
            return data;
        }
    }
}
