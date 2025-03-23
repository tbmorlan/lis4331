// Main.java - refactored
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // initialize variables
        double miles = 0.0;
        double mph = 0.0;
        double validMiles = 0.0;
        double validMPH = 0.0;
        
        String choice = ""; //for posttest do...while loop
        
        // create Scanner object
        Scanner sc = new Scanner(System.in);
        
        //display developer requirements (void method)
        Methods.getRequirements();
        
        do {
            System.out.println(); // Add blank line for formatting
            
            //validate miles (value-returning method)
            miles = Methods.validateMilesDataType();
            validMiles = Methods.validateMilesRange(miles);
            
            System.out.println(); // Add blank line for formatting
            
            //validate mph (value-returning method)
            mph = Methods.validateMPHDataType();
            validMPH = Methods.validateMPHRange(mph);
            
            System.out.println(); // Add blank line for formatting
            
            //display results (void method)
            Methods.calculateTravelTime(validMiles, validMPH);
            
            //continue?
            System.out.print("Continue? (y/n): ");
            choice = sc.next();
            System.out.println();
        } 
        while (choice.equalsIgnoreCase("y"));
    }
}