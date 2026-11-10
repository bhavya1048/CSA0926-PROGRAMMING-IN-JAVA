
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PassStore {

    private Path file;

    PassStore(Path file) {
        this.file = file;
    }

    public void add(String id, String reg, String name,
                    int route, double amount) throws IOException {

        try (BufferedWriter w = Files.newBufferedWriter(
                file, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            w.write(id + "," + reg + "," + name + ","
                    + route + "," + amount);
            w.newLine();
        }
    }

    public String[] find(String passId) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(file)) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(",", -1);

                if (fields.length != 5) {
                    continue;
                }

                try {
                    Integer.parseInt(fields[3]);
                    Double.parseDouble(fields[4]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (fields[0].equals(passId)) {
                    return fields;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        try {
            Path f = Files.createTempFile("passes", ".csv");

            PassStore store = new PassStore(f);

            store.add("P1001", "192311001", "Arun", 12, 4500);
            Files.writeString(f, "P1002,DAMAGED LINE\n",
                    StandardOpenOption.APPEND);
            store.add("P1003", "192311003", "Divya", 7, 3800);

            System.out.println(Arrays.toString(store.find("P1003")));
            System.out.println(Arrays.toString(store.find("P1002")));
            System.out.println(Arrays.toString(store.find("P9999")));

            Files.deleteIfExists(f);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
