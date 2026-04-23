import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Application class for processing BST commands from input.txt.
 * Source acknowledgement: Developed with assistance from ChatGPT.
 */
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        String fileName = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    processCommand(line, bst);
                } catch (NumberFormatException e) {
                    System.out.println("Input not valid");
                } catch (IllegalArgumentException e) {
                    System.out.println("Input not valid");
                } catch (Exception e) {
                    System.out.println("Input not valid");
                }
            }
        } catch (IOException e) {
            System.out.println("Input not valid");
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void processCommand(String line, BinarySearchTree bst) {
        String[] parts = line.split("\\s+");
        String command = parts[0].toUpperCase();

        switch (command) {
            case "BUILD":
                if (parts.length < 2) {
                    throw new IllegalArgumentException();
                }
                for (int i = 1; i < parts.length; i++) {
                    int value = Integer.parseInt(parts[i]);
                    bst.insert(value);
                }
                break;

            case "IN_ORDER":
                if (parts.length != 1) {
                    throw new IllegalArgumentException();
                }
                System.out.printf("%-15s %s%n", "IN_ORDER:", bst.getInOrderString());
                break;

            case "INSERT":
                if (parts.length != 2) {
                    throw new IllegalArgumentException();
                }
                bst.insert(Integer.parseInt(parts[1]));
                break;

            case "DELETE":
                if (parts.length != 2) {
                    throw new IllegalArgumentException();
                }
                bst.delete(Integer.parseInt(parts[1]));
                break;

            case "SIZE":
                if (parts.length != 1) {
                    throw new IllegalArgumentException();
                }
                System.out.printf("%-15s %d%n", "SIZE:", bst.getSize());
                break;

            case "HEIGHT":
                if (parts.length != 1) {
                    throw new IllegalArgumentException();
                }
                System.out.printf("%-15s %d%n", "HEIGHT:", bst.height());
                break;

            case "KTH_SMALLEST":
                if (parts.length != 2) {
                    throw new IllegalArgumentException();
                }
                int k = Integer.parseInt(parts[1]);
                int kthValue = bst.find_kth_smallest(k);
                if (kthValue != -1) {
                    System.out.printf("%-15s %d (k = %d)%n", "KTH_SMALLEST:", kthValue, k);
                }
                break;

            case "SEARCH":
                if (parts.length != 2) {
                    throw new IllegalArgumentException();
                }
                int searchValue = Integer.parseInt(parts[1]);
                System.out.printf("SEARCH %d: %b%n", searchValue, bst.search(searchValue));
                break;

            default:
                throw new IllegalArgumentException();
        }
    }
}
