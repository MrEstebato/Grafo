import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un grafo de precedencia.
 */
public class PrecedenceGraph {
    private LinkedList<Node> nodes;
    private List<List<Integer>> adjacencyMatrix;
    private List<Node> flows;
    private Node root;
    private Node last;
    private HashMap<String, ArrayList<Node>> forks = new HashMap<>();
    private HashMap<String, Node> thaNodes;

    /**
     * Constructor de la clase PrecedenceGraph.
     *
     * @param filePath Ruta del archivo que contiene la matriz de adyacencia.
     */
    public PrecedenceGraph(String filePath) {
        // Inicialización de estructuras de datos.
        adjacencyMatrix = new ArrayList<>();
        nodes = new LinkedList<>();
        flows = new ArrayList<>();
        thaNodes = new HashMap<String, Node>();

        // Lectura de la matriz de adyacencia desde el archivo.
        graphReader(filePath);

        // Creación de nodos y asignación al mapa de nodos 'thaNodes'.
        for (int i = 0; i < adjacencyMatrix.get(0).size(); i++) {
            Node addedNode = new Node("S" + (i + 1));
            thaNodes.put("S" + (i + 1), addedNode);
            addNode(addedNode);
        }

        root = nodes.get(0);

        // Inserción de nodos en el grafo.
        for (int j = 0; j < nodes.size(); j++) {
            for (int k = 0; k < adjacencyMatrix.get(0).size(); k++) {
                if (adjacencyMatrix.get(j).get(k) == 1) {
                    addEdge(nodes.get(j), nodes.get(k));
                }
            }
        }

        // Obtención del nodo raíz y del último nodo.
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

        for (int j = 0; j < nodes.size(); j++) {
            int count = 0;
            for (int i = 0; i < nodes.size(); i++) {
                count += adjacencyMatrix.get(j).get(i);
            }
            if (count == 0) {
                last = nodes.get(j);
                break;
            }
        }
    }

    /**
     * Método para leer la matriz de adyacencia desde un archivo.
     *
     * @param filePath Ruta del archivo.
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
     * Método para mostrar los flujos válidos en el grafo.
     */
    public void showValidFlows() {
        getValidFlows(this, root, flows);
    }

    /**
     * Método recursivo para obtener los flujos válidos en el grafo.
     *
     * @param grafo        Grafo actual.
     * @param nodoActual   Nodo actual.
     * @param caminoActual Lista de nodos en el camino actual.
     */
    private void getValidFlows(PrecedenceGraph grafo, Node nodoActual, List<Node> caminoActual) {
        caminoActual.add(nodoActual);

        if (nodoActual.children.isEmpty()) {
            // Imprimir el camino actual.
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

    /**
     * Método para agregar un nodo al grafo.
     *
     * @param node Nodo a agregar.
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Método para agregar una arista entre dos nodos.
     *
     * @param node1 Nodo de origen.
     * @param node2 Nodo de destino.
     */
    public void addEdge(Node node1, Node node2) {
        node1.addChild(node2);
    }

    /**
     * Método para obtener el nodo raíz del grafo.
     *
     * @return Nodo raíz.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Método privado para imprimir el nodo raíz.
     */
    private void printRoot() {
        System.out.println(root.getData());
    }

    /**
     * Método privado para imprimir las constantes 'CONTS'.
     */
    private void printCONTS() {
        // Crear un HashMap con S1:0, S2:0, S3:0, ..., Sn:0.
        HashMap<String, Integer> conts = new HashMap<>();
        for (String key : thaNodes.keySet()) {
            conts.put(key, 0);
        }

        // Recorrer la matriz por columnas para obtener
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            for (int j = 0; j < adjacencyMatrix.size(); j++) {
                if (adjacencyMatrix.get(i).get(j) == 1) {
                    conts.put("S" + (j + 1), conts.getOrDefault("S" + (j + 1), 0) + 1);
                }
            }
        }

        // Imprimir las constantes 'CONTS' mayores a 1.
        for (String key : conts.keySet()) {
            if (conts.get(key) > 1)
                System.out.println("CONTS-" + key + " := " + conts.get(key));
        }
    }

    private int forkLabelCounter = 1;
    private int joinLabelCounter = 1;

    /**
     * Método privado para generar una etiqueta de fork.
     *
     * @return Etiqueta generada.
     */
    private String generateForkLabel() {
        return "L" + forkLabelCounter++;
    }

    /**
     * Método privado para generar una etiqueta de join.
     *
     * @return Etiqueta generada.
     */
    private String generateJoinLabel() {
        return "L" + joinLabelCounter++;
    }

    /**
     * Método privado para generar el pseudocódigo del grafo.
     *
     * @param node        Nodo actual.
     * @param indentation Cadena de indentación.
     */
    private void generatePseudocode(Node node, String indentation) {
        if (!last.equals(node)) {
            System.out.println(indentation + node.getData() + ";");
        }

        if (!node.children.isEmpty()) {
            if (node.children.size() > 1) {
                String forkLabel = generateForkLabel();
                String joinLabel = generateJoinLabel();

                System.out.println(indentation + "FORK " + forkLabel + ";");

                for (int i = 0; i < node.children.size(); i++) {
                    Node child = node.children.get(i);
                    generatePseudocode(child, indentation + "  ");

                    if (i < node.children.size() - 1) {
                        System.out.println(indentation + "GOTO L" + joinLabelCounter + ";");
                        System.out.println(indentation + forkLabel + ":");
                    }
                }
            } else {
                generatePseudocode(node.children.get(0), indentation);
            }
        }
    }

    /**
     * Método privado para obtener los forks del grafo.
     */
    private void getFORKS() {
        // Crear un HashMap con etiquetas L1, L2, ..., Ln.
        int num = 1;
        for (String key : thaNodes.keySet()) {
            forks.put("L" + num, new ArrayList<Node>());
            num++;
        }

        int forkNumber = 0;

        // Recorrer la matriz por filas para obtener los hijos y actualizarlos en el HashMap.
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            int onesInRow = 0;

            for (int j = 0; j < adjacencyMatrix.size(); j++) {
                if (adjacencyMatrix.get(i).get(j) == 1) {
                    forks.get("L" + (forkNumber + 1)).add(nodes.get(j));
                    onesInRow++;
                    if (onesInRow >= 2) {
                        forkNumber++;
                    }
                }
            }
            onesInRow = 0;
            if (i < adjacencyMatrix.size() - 1) {
                forks.put("L" + (i + 1), forks.getOrDefault("L" + (i + 1), forks.get("L" + (i + 1))));
            }
        }
    }

    int h = 0;
    int a = 0;

    /**
     * Método privado para imprimir los forks del grafo.
     *
     * @param node Nodo actual.
     */
    private void printFORKS(Node node) {
        if (node.children.isEmpty()) {
            return;
        }

        System.out.println(node.children.size());
        System.out.println("L" + (h + 1));
        if (node.children.size() > 1) {
            String key = "L" + (h + 1);

            if (forks.get(key) != null) {
                System.out.println("FORK " + key);
                System.out.println(forks.get(key).get(h).getData());
            }
        }
        for (Node child : node.children) {
            h++;
            System.out.println(child.getData());
            printFORKS(child);
        }
    }

    /**
     * Método público para crear el pseudocódigo del grafo.
     */
    public void createPseudocode() {
        printCONTS();
        System.out.println("CONT := " + root.children.size() + ";"); // Impresión única de CONT
        generatePseudocode(root, "");
        System.out.println("L" + joinLabelCounter + ":");
        System.out.println(last.getData());
    }

    /**
     * Elimina el nodo con los datos proporcionados del grafo, manteniendo la estructura del grafo.
     *
     * @param nodedata Datos del nodo a eliminar.
     */
    public void deleteNode(String nodedata) {
        // Verificar si el nodo a eliminar es la raíz; en ese caso, no se realiza ninguna operación.
        if (root.getData().equals(nodedata)) {
            return;
        }

        // Llamar al método de eliminación recursiva para buscar y eliminar el nodo.
        deleteNodeRec(nodedata, root);
    }

    /**
     * Método auxiliar para la eliminación recursiva de un nodo del grafo.
     *
     * @param nodedata Datos del nodo a eliminar.
     * @param father Nodo padre del cual se eliminará el nodo hijo.
     */
    private void deleteNodeRec(String nodedata, Node father) {
        // Obtener la lista de hijos del nodo padre.
        ArrayList<Node> children = father.children;

        // Iterar sobre los hijos para buscar el nodo a eliminar.
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);

            // Verificar si el nodo actual tiene los datos que se quieren eliminar.
            if (child.getData().equals(nodedata)) {
                // Crear una copia de los hijos del nodo a eliminar.
                ArrayList<Node> tempchildren = new ArrayList<>(child.children);
                // Eliminar el nodo actual de la lista de hijos del padre.
                children.remove(i);

                // Agregar los hijos del nodo eliminado como nietos del padre.
                for (Node grandChild : tempchildren) {
                    father.addChild(grandChild);
                }
                return;
            } else {
                // Llamar recursivamente al método para continuar la búsqueda en los nodos hijos.
                deleteNodeRec(nodedata, child);
            }
        }
    }


    /**
     * Elimina la arista dirigida desde el nodo con datos 'data1' al nodo con datos 'data2' en el grafo.
     *
     * @param data1 Datos del nodo origen.
     * @param data2 Datos del nodo destino.
     * @return true si la operación fue exitosa, false si al menos uno de los nodos no existe en el grafo.
     */
    public boolean deleteEdge(String data1, String data2) {
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
         * @param data Datos del nodo.
         */
        public Node(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        /**
         * Método para agregar un nodo hijo.
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
         * Método para establecer los datos del nodo.
         *
         * @param data Datos del nodo.
         */
        public void setData(String data) {
            this.data = data;
        }

        /**
         * Método para obtener los datos del nodo.
         *
         * @return Datos del nodo.
         */
        public String getData() {
            return data;
        }
    }
}
