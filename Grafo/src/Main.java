import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "Grafo/src/Grafo.txt";
        List<List<Integer>> adjacencyMatrix = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] characters = line.split(" ");

                List<Integer> row = new ArrayList<>();

                for (String character: characters) {
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
}
