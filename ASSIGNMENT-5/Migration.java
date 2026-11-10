
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Migration {
    public static void main(String[] args) throws Exception {

        Path file = Files.createTempFile("passes", ".csv");

        String data =
                "P1001,192311001,Arun,12,4500\n" +
                "P1002,192311002,Divya,7,3800\n" +
                "P1003,192311003,Ravi,ABC,4000\n" +
                "P1004,192311004,Kumar,5,4200\n" +
                "P1005,192311005,Meena,8,XYZ\n";

        Files.writeString(file, data);

        List<String[]> database = new ArrayList<>();
        List<String[]> batch = new ArrayList<>();

        int failed = 0;

        BufferedReader br = new BufferedReader(
                new FileReader(file.toFile()));

        String line;

        while ((line = br.readLine()) != null) {

            String[] p = line.split(",", -1);

            try {
                if (p.length != 5) {
                    throw new Exception();
                }

                Integer.parseInt(p[3]);
                double amount = Double.parseDouble(p[4]);

                if (p[0].isEmpty() || p[1].isEmpty()
                        || p[2].isEmpty() || amount < 0) {
                    throw new Exception();
                }

                batch.add(p);

                if (batch.size() == 1000) {
                    database.addAll(batch);
                    batch.clear();
                }

            } catch (Exception e) {
                System.out.println("Failed Record: " + line);
                failed++;
            }
        }

        br.close();

        // Commit remaining valid records
        database.addAll(batch);

        System.out.println("\nMigration Completed");
        System.out.println("Successful Records: " + database.size());
        System.out.println("Failed Records: " + failed);

        System.out.println("\nMigrated Records:");

        for (String[] p : database) {
            System.out.println(Arrays.toString(p));
        }

        Files.deleteIfExists(file);
    }
}
