import java.util.*;
class Ticket {
    private int minutes;
    void set(int m) { minutes = m; }
    int get() { return minutes; }
}
class Encapsulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ticket t = new Ticket();
        t.set(sc.nextInt());
        System.out.println(t.get());
    }
}