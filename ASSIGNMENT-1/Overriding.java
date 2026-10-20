import java.util.*;
class Vehicle {
    void show() { System.out.println("Vehicle"); }
}
class Car extends Vehicle {
    void show() { System.out.println("Car"); }
}
class Overriding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehicle v = new Car();
        sc.next();
        v.show();
    }
}