import java.util.*;
public class Vehicle {
    String number, type;
    void input() {
        Scanner sc = new Scanner(System.in);
        number = sc.next();
        type = sc.next();
    }

    void display() {
        System.out.println(number);
        System.out.println(type);
    }

    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        v.input();
        v.display();
    }
}