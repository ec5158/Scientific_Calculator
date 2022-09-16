import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calc_screen {
    protected static final String textFieldString = "Equation";
    private static void setUpButtons(JFrame frame){
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();

        JButton b00 = new JButton("00");
        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");

        JButton beq = new JButton("=");

        JButton bper = new JButton("%");
        JButton bsqr = new JButton("√");
        JButton bClear = new JButton("Clear");
        JButton bdec = new JButton(".");

        JButton badd = new JButton("+");
        JButton bsub = new JButton("-");
        JButton bmult = new JButton("*");
        JButton bdiv = new JButton("÷");

        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.add(bper);
        buttonPanel.add(bsqr);
        buttonPanel.add(bClear);
        buttonPanel.add(bdiv);
        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(bmult);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(bsub);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(badd);
        buttonPanel.add(b0);
        buttonPanel.add(b00);
        buttonPanel.add(bdec);
        buttonPanel.add(beq);
        buttonPanel.setPreferredSize(new Dimension(300, 400));

        containerPanel.add(buttonPanel);
        frame.getContentPane().add(containerPanel);
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(500, 20));

        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(255, 255, 255));
        yellowLabel.setPreferredSize(new Dimension(500, 500));

        frame.setJMenuBar(greenMenuBar);
        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        JTextField textField = new JTextField(10);
        textField.setActionCommand(textFieldString);
        frame.getContentPane().add(textField, BorderLayout.PAGE_START);

        setUpButtons(frame);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
