import java.util.Scanner;

public class Methods {
    
    public static void getRequirements() {

        System.out.println("Developer: Tanner Morlan"
        + "\nProgram counts, totals, and averages total number of user-entered exam scores."
        + "\nPlease enter exam scores between 0 and 100, inclusive."
        + "\nEnter out of range number to end program."
        + "\nMust *only* permit numeric entry.\n");

    }

    public static void calculateScores() {

        //init vars, scanner
        double total = 0.0;
        int count = 0;
        double score = 0.0;
        double average = 0.0;
        Scanner sc = new Scanner (System.in);

        //get exam scores
        while (score >= 0 && score <= 100) {
            System.out.print("Enter exam score: ");

            while (!sc.hasNextDouble()) {
                System.out.println("Not valid number!\n");
                sc.next();
                System.out.print("Please try again. Enter exam score: ");
            }
            score = sc.nextDouble();

            //count & total score
            if (score >= 0 && score <= 100) {
                count = ++count;
                total = total + score;
            }
        }
        average = total/count;

        System.out.println("Count: " + count
        + "\nTotal: " + total
        + "\nAverage: " + average);

        sc.close();
    }
}
