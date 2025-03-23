import java.util.Scanner;

public class Methods {
    //Create Method without returning any value (without object)
    public static void getRequirements() {
        System.out.println("Author: Tanner Morlan"
                            + "\nProgram calculates approximate travel time."
                            + "\nPrompt user to enter miles and miles per hour (accepts double values)."
                            + "\n*Must* perform data validation:"
                            + "\n\ta. only numbers,"
                            + "\n\tb. miles range (> 0 and no more than 3000),"
                            + "\n\tc. MPH range (> 0 and no more than 100)."
                            + "\n\nDisplay approximate travel time in hours and minutes."
                            + "\nHint: Use integer arithmetic, division, and modulus operators to calculate hours and minutes."
                            + "\n\nPrompt user to continue (only if user enters \"y\" or \"Y\")."
                            + "\nNote: Can create either GUI or command-line program--your option.");
    }
    
    public static double validateMilesDataType() {
        double miles = 0.0;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter miles: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter valid number. \n");

            System.out.print("Enter miles: ");
            sc.next();
        }
        miles = sc.nextDouble();
        return miles;
    }
    
    public static double validateMilesRange(double miles) {
        Scanner sc = new Scanner(System.in);
        
        while (miles <= 0 || miles > 3000) {
            System.out.println("Miles must be between 1 and 3000, inclusive. Please try again.\n");
            System.out.print("Enter miles: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Please enter valid number. ");
                sc.next();
            }
            miles = sc.nextDouble();
        }
        return miles;
    }
    
    public static double validateMPHDataType() {
        double mph = 0.0;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter mph: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter valid number. \n");

            System.out.print("Enter mph: ");
            sc.next();
        }
        mph = sc.nextDouble();
        return mph;
    }
    
    public static double validateMPHRange(double mph) {
        Scanner sc = new Scanner(System.in);
        
        while (mph <= 0 || mph > 100) {
            System.out.println("MPH must be between 1 and 100, inclusive. Please try again.\n");
            System.out.print("Enter mph: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Please enter valid number. ");
                sc.next();
            }
            mph = sc.nextDouble();
        }
        return mph;
    }
    
    public static void calculateTravelTime(double miles, double mph) {
        double hours = 0.0;
        int minutes = 0;
        int hoursInt = 0;
        int minutesRemainder = 0;
        
        hours = miles / mph;
        minutes = (int) (hours * 60);
        hoursInt = minutes / 60;
        minutesRemainder = minutes % 60;
        
        System.out.println("Estimated travel time: " + hoursInt + " hr(s) " + minutesRemainder + " Minutes");
        System.out.println("Thank you for using the program!\n");
    }
}