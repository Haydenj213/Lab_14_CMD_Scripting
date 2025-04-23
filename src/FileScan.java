import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        File fileToScan;

        if (args.length > 0) {
            fileToScan = new File("src/" + args[0]);

            if (!fileToScan.exists() || !fileToScan.isFile()) {
                System.out.println("Error: File '" + args[0] + "' not found in src.");
                return;
            }
        } else {
            JFileChooser fileChooser = new JFileChooser(new File("src"));
            fileChooser.setDialogTitle("Select a Text File");

            int fileChosen = fileChooser.showOpenDialog(null);

            if (fileChosen == JFileChooser.APPROVE_OPTION) {
                fileToScan = fileChooser.getSelectedFile();
            } else {
                System.out.println("No file selected. Please select a .txt file.");
                return;
            }
        }

        processFile(fileToScan);
    }


    private static void processFile(File file) {
        int numberOfLines = 0;
        int numberOfWords = 0;
        int numberOfCharacters = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);

                numberOfLines++;
                numberOfWords += line.split("\\s+").length;
                numberOfCharacters += line.length();
            }

            System.out.println("\nSummary Report:");
            System.out.println("File Name: " + file.getName());
            System.out.println("Number of lines: " + numberOfLines);
            System.out.println("Number of words: " + numberOfWords);
            System.out.println("Number of characters: " + numberOfCharacters);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}

