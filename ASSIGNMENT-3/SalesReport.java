
import java.io.*;
import java.util.*;

public class SalesReport {

    static void manualReport(String[] files, String output) {

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(output));

            for (String file : files) {
                try {
                    reader = new BufferedReader(new FileReader(file));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }

                    reader.close();
                    reader = null;

                } catch (IOException e) {
                    System.out.println(
                        "Manual version: Error in " + file);
                }
            }

        } catch (IOException e) {
            System.out.println("Report error: " + e.getMessage());

        } finally {
            try {
                if (reader != null)
                    reader.close();

                if (writer != null)
                    writer.close();

            } catch (IOException e) {
                System.out.println("Closing error");
            }
        }
    }

    static void safeReport(String[] files, String output) {

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(output))) {

            for (String file : files) {

                try (BufferedReader reader =
                         new BufferedReader(new FileReader(file))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }

                } catch (IOException e) {
                    System.out.println(
                        "Try-with-resources: Error in " + file);
                }
            }

        } catch (IOException e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        String[] files = {
            "store1.txt",
            "missing.txt",
            "store2.txt"
        };

        manualReport(files, "manualReport.txt");
        safeReport(files, "safeReport.txt");

        System.out.println("Reports generated");
    }
}
