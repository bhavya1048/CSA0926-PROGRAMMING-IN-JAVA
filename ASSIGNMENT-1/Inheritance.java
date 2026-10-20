import java.util.Scanner;
class Vehicle {
    String number;

    Vehicle(String number) {
        this.number = number;
    }
    void display() {
        System.out.println("Car: " + number);
    }
}
class Car extends Vehicle {
    Car(String number) {
        super(number);
    }
}
public class Inheritance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car c = new Car(sc.next());
        c.display();
    }
}