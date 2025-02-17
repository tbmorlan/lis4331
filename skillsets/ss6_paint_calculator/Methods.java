import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Methods {
    
    public static void getRequirements() {

        JOptionPane.showMessageDialog(null,
            "Developer: Tanner Morlan"
            + "\nProgram uses Java GUI message and input dialogs."
            + "\nFor paint\"area\" simplicity: use length X height X 2 + width X height X 2."
            + "\nFormat numbers as per below: thousand separator, decimal point and $ sign for currency."
            + "\nResearch how many square feet a gallon of paint covers."
            + "\nNote: Program performs data validation.");

    }

    public static double getPaintCost() {

        String paintCostString = "";
        double paintCost = 0.0;

        paintCostString = JOptionPane.showInputDialog(null,
            "Paint price per gallon:",
            "Paint Cost Calculator",
            JOptionPane.INFORMATION_MESSAGE);

        while (!isNumber(paintCostString)) {
            paintCostString = JOptionPane.showInputDialog(null,
                "Not a valid value. Please enter paint price per gallon:",
                "Paint Cost Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
        paintCost = Double.parseDouble(paintCostString);
        return paintCost;

    }

    public static double getLength() {
        String lengthString = "";
        double length = 0.0;

        lengthString = JOptionPane.showInputDialog(null,
            "Room/house length:",
            "Paint Cost Calculator",
            JOptionPane.INFORMATION_MESSAGE);

        while (!isNumber(lengthString)) {
            lengthString = JOptionPane.showInputDialog(null,
                "Not a valid value. Please enter room/house length:",
                "Paint Cost Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
        length = Double.parseDouble(lengthString);
        return length;
    }

    public static double getWidth() {
        String widthString = "";
        double width = 0.0;

        widthString = JOptionPane.showInputDialog(null,
            "Room/house width:",
            "Paint Cost Calculator",
            JOptionPane.INFORMATION_MESSAGE);

        while (!isNumber(widthString)) {
            widthString = JOptionPane.showInputDialog(null,
                "Not a valid value. Please enter room/house width:",
                "Paint Cost Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
        width = Double.parseDouble(widthString);
        return width;
    }

    public static double getHeight() {
        String heightString = "";
        double height = 0.0;

        heightString = JOptionPane.showInputDialog(null,
            "Room/house height:",
            "Paint Cost Calculator",
            JOptionPane.INFORMATION_MESSAGE);

        while (!isNumber(heightString)) {
            heightString = JOptionPane.showInputDialog(null,
                "Not a valid value. Please enter room/house height:",
                "Paint Cost Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
        height = Double.parseDouble(heightString);
        return height;
    }

    public static boolean isNumber(String n) {
        try {
            Double.parseDouble(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static double getArea(double length, double width, double height) {

        double area = 0.0;
        area = (length * height * 2) + (width * height * 2);
        return area;
    }

    public static double calculatePaintCost(double area, double cost) {
        double gallons = 0.0, total = 0.0;

        final int SQFT_PER_GAL = 350;

        gallons = area / SQFT_PER_GAL;
        total = gallons * cost;
        return total;
    }

    public static void printPaintCost(double cost, double area, double total) {
        DecimalFormat moneyFormat = new DecimalFormat("$#,###,##0.00");
        DecimalFormat numberFormat = new DecimalFormat("#,###,##0.00");

        JOptionPane.showMessageDialog(null,
            "Paint = " + moneyFormat.format(cost) + " per gallon."
            + "\nArea of room/house = " + numberFormat.format(area) + "sq ft."
            + "\nTotal = " + moneyFormat.format(total));
    }
}
