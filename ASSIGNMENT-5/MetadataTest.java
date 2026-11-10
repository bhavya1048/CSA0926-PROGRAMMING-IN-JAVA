
import java.util.*;

public class MetadataTest {
    public static void main(String[] args) {

        String database = "Java In-Memory Storage";
        String driver = "Not Applicable";

        List<String> tables = new ArrayList<>();

        tables.add("bus_pass");

        System.out.println("Database Product: " + database);
        System.out.println("Driver Version: " + driver);

        System.out.println("Tables Present:");

        for (String table : tables) {
            System.out.println(table);
        }

        System.out.println(
            "Metadata inspection simulation completed.");
    }
}
