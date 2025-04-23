import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInspector {
    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser(new File("src"));
        fileChooser.setDialogTitle("Select a Text File From Below");

        int fileChosen = fileChooser.showOpenDialog(null);

        if (fileChosen == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processFile(selectedFile);
        } else {
            System.out.println("No file selected was selected. Please select a .txt file.");
        }
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
            System.out.println("Number of lines in the file: " + numberOfLines);
            System.out.println("Number of words in the file: " + numberOfWords);
            System.out.println("Number of characters in the file: " + numberOfCharacters);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}