
public class SQLTest {
    public static void main(String[] args) {

        String username = "admin";
        String password = "' OR '1'='1";

        // Unsafe login simulation
        String unsafeQuery = "SELECT * FROM users WHERE id='"
                + username + "' AND pwd='" + password + "'";

        System.out.println("Unsafe Query:");
        System.out.println(unsafeQuery);

        if (unsafeQuery.contains("' OR '1'='1")) {
            System.out.println("Unsafe Login: Accepted");
        } else {
            System.out.println("Unsafe Login: Rejected");
        }

        // Safe login simulation using parameter validation
        String correctUsername = "admin";
        String correctPassword = "admin123";

        if (username.equals(correctUsername)
                && password.equals(correctPassword)) {
            System.out.println("Safe Login: Accepted");
        } else {
            System.out.println("Safe Login: Rejected");
        }
    }
}
