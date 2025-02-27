import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DistanceCalculator implements ActionListener {
    private JFrame frame;
    private JTextField legAField;
    private JTextField legBField;
    private JLabel distanceCalculatorLabel;
    private JButton computeButton;

    public DistanceCalculator() {

        legAField = new JTextField(5);
        legBField = new JTextField(5);
        distanceCalculatorLabel = new JLabel("Compute Leg C");
        computeButton = new JButton("Compute");

        computeButton.addActionListener(this);

        JPanel north = new JPanel(new GridLayout(2,2));
        north.add(new JLabel("Leg A: "));
        north.add(legAField);
        north.add(new JLabel("Leg B: "));
        north.add(legBField);

        frame = new JFrame("Distance Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(north, BorderLayout.NORTH);
        frame.add(distanceCalculatorLabel, BorderLayout.CENTER);
        frame.add(computeButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        String legAText = legAField.getText();
        double legA = Double.parseDouble(legAText);
        String legBText = legBField.getText();
        double legB = Double.parseDouble(legBText);

        double legC = Math.sqrt(Math.pow(legA, 2) + Math.pow(legB, 2));

        distanceCalculatorLabel.setText(String.format("Leg C: %,.2f", legC));
    }
    
}