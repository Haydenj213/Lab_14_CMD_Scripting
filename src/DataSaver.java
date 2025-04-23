import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        while (true) {
            System.out.println("\nPlease enter the following user information into the prompts. Type 'exit' as the first name to stop the program.");

            String firstName = SafeInput.getNonZeroLenString(in, "Enter a first name");

            if (firstName.equalsIgnoreCase("exit")) {
                break;
            }

            String lastName = SafeInput.getNonZeroLenString(in, "Enter a last name");
            String email = SafeInput.getNonZeroLenString(in, "Enter an email in the following format name@(mailprovider).com");
            int yearOfBirth = SafeInput.getInt(in, "Enter the birth year (e.g., 1992)");
            in.nextLine();

            String idNumber = String.format("%06d", idCounter++);

            String record = String.join(", ", firstName, lastName, idNumber, email, String.valueOf(yearOfBirth));
            records.add(record);
        }

        if (!records.isEmpty()) {
            String fileName = SafeInput.getNonZeroLenString(in, "Enter a name for the output CSV file. (include .csv extension)");
            saveRecordsToFile(records, "src/" + fileName);
        } else {
            System.out.println("No records were entered. Exiting the program.");
        }
    }

    private static void saveRecordsToFile(ArrayList<String> records, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Data saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
