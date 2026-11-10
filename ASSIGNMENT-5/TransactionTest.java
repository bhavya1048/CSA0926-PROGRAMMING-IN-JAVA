
import java.util.*;

public class TransactionTest {
    public static void main(String[] args) {

        List<String> autoCommit = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            autoCommit.add("P" + i);
        }

        System.out.println("Auto-commit records before failure: "
                + autoCommit.size());

        autoCommit.add("P6");

        System.out.println("Auto-commit records after failure: "
                + autoCommit.size());

        List<String> transaction = new ArrayList<>();

        try {
            for (int i = 1; i <= 5; i++) {

                if (i == 3) {
                    throw new RuntimeException("Invalid record");
                }

                transaction.add("P" + i);
            }

        } catch (RuntimeException e) {
            transaction.clear();
            System.out.println("Transaction rolled back");
        }

        System.out.println("Transaction records after failure: "
                + transaction.size());
    }
}
