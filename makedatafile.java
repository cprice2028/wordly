import java.io.*;

public class makedatafile {
    public static void main(String[] args) {
        String inputFilename = "bigwords.txt";
        String outputFilename = "processedwords.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words based on spaces
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.length() == 5) { // Ensure the word is exactly 5 characters long
                        writer.write(word);
                        writer.newLine(); // Write each word on a new line
                    }
                }
            }
            System.out.println("Processing complete. Output written to " + outputFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}