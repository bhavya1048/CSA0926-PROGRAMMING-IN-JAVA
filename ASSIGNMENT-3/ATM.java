import java.util.Scanner;

class ATM {
    static double balance = 5000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter amount to withdraw: ");
            String input = sc.nextLine();

            double amount = Double.parseDouble(input);

            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }

            if (amount > balance) {
                throw new ArithmeticException("Insufficient balance");
            }

            balance -= amount;

            System.out.println("Withdrawal successful!");
            System.out.println("Remaining balance: " + balance);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());

        } finally {
            System.out.println("Thank you for using ATM.");
            sc.close();
        }
    }
}