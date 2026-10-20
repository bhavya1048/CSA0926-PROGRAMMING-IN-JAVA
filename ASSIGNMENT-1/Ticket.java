import java.util.Scanner;
class Ticket {
    int id;
    String number;

    Ticket(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ticket t = new Ticket(sc.nextInt(), sc.next());
        System.out.println(t.id + " " + t.number);
    }
}