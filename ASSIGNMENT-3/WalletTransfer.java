
class Account {

    final int id;
    int balance;

    Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}

public class WalletTransfer {

    static void transfer(Account a, Account b, int amount) {

        Account first = a.id < b.id ? a : b;
        Account second = a.id < b.id ? b : a;

        synchronized (first) {
            synchronized (second) {

                if (a.balance >= amount) {
                    a.balance -= amount;
                    b.balance += amount;

                    System.out.println(
                        "Transferred " + amount +
                        " from " + a.id + " to " + b.id);
                }
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Account a = new Account(1, 5000);
        Account b = new Account(2, 3000);

        Thread t1 = new Thread(
            () -> transfer(a, b, 1000));

        Thread t2 = new Thread(
            () -> transfer(b, a, 500));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account 1: " + a.balance);
        System.out.println("Account 2: " + b.balance);
    }
}
