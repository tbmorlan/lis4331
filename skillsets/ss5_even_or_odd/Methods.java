import javax.swing.JOptionPane;

public class Methods {

    public static void getRequirements() {
        JOptionPane.showMessageDialog(null,
            "Developer: Tanner Morlan"
            + "\nProgram evaluates integers as even or odd."
            + "\nNote: Program *does* perform data validation,"
            + "\nprompting user until correct data entered.");
    }

    public static int getNum() {
        String numString = "";
        int num = 0;

        numString = JOptionPane.showInputDialog(null,
            "Enter integer:",
            "Number Test Dialog",
            JOptionPane.INFORMATION_MESSAGE);

        while (!isIntNumber(numString)) {
            numString = JOptionPane.showInputDialog(null,
                "Invalid integer. Please enter integer:",
                "Number Test Dialog",
                JOptionPane.INFORMATION_MESSAGE);
        }

        num = Integer.parseInt(numString);
        return num;
    }

    public static boolean isIntNumber(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void printResults(int num) {
        if (num % 2 == 0) {
            JOptionPane.showMessageDialog(null, num + " is an even number.");
        } else {
            JOptionPane.showMessageDialog(null, num + " is an odd number.");
        }
    }
}
