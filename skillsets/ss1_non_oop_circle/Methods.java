import java.util.Scanner;

public class Methods {

    public static void getRequirements() {

        System.out.println("Developer: Tanner Morlan"
        + "\nNon-OOP program calculates diameter, circumference, and circle area."
        + "\nMust use Java's built-in PI constatnt, printf(), and formatted to 2 decimal places."
        + "\nMust *only* permit numeric entry.\n");
        
    }    

    public static void calculateCircle() {

        //init scanner, create scanner object, get user input
        Scanner sc = new Scanner(System.in);
        double radius = 0.0;

        System.out.print("Enter circle radius: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Not valid number!\n");
            sc.next();
            System.out.print("Please try again. Enter circle radius: ");
        }
        radius = sc.nextDouble();

        System.out.printf("\nCircle diameter: %.2f\nCircumference: " + "%.2f\nArea: %.2f\n", (2*radius), (2*Math.PI*radius),(Math.PI*radius*radius));
        sc.close();

    }
}
