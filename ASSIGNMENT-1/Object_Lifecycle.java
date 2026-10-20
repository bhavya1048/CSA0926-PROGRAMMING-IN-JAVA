import java.util.Scanner;
class Object_Lifecycle {
    static int count = 0;

    Object_Lifecycle() {
        count++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++)
            new Object_Lifecycle();

        System.out.println(count);
    }
}