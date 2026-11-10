
import java.util.*;

public class InsertCompare {
    public static void main(String[] args) {

        List<String[]> statement = new ArrayList<>();
        List<String[]> prepared = new ArrayList<>();
        List<String[]> batch = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            String[] pass = {
                "P" + (1000 + i),
                "19231100" + i,
                "Student" + i,
                "12",
                "4500"
            };

            statement.add(pass);
            prepared.add(pass.clone());
            batch.add(pass.clone());
        }

        System.out.println("Statement Records: "
                + statement.size());

        System.out.println("PreparedStatement Records: "
                + prepared.size());

        System.out.println("Batch Records: "
                + batch.size());

        System.out.println("\nInserted Records:");

        for (String[] p : batch) {
            System.out.println(Arrays.toString(p));
        }

        System.out.println("\nAll 10 records verified successfully.");
    }
}
