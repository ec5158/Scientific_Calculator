/**
 * @file calc_screen.java
 * @author: Eric Chen
 *
 * @Desc: This class
 *
 *
 **/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calc_screen extends JFrame implements ActionListener{
    static StringBuilder textFieldString;
    static JTextField textField;
    static StringBuilder equation;

    /**
     ** Name: setUpButtons
     ** This function
     **
     ** @param frame the frame that the buttons will be put on
     **
     **/
    private static void setUpButtons(JFrame frame){
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        calc_screen c = new calc_screen();

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
        JButton bneg = new JButton("(-)");

        JButton bsin = new JButton("sin");
        JButton bcos = new JButton("cos");
        JButton btan = new JButton("tan");

        JButton bxpon = new JButton("^");
        JButton bLog = new JButton("log");
        JButton bln = new JButton("ln");

        JButton bper = new JButton("%");
        JButton bsqr = new JButton("√");
        JButton bClear = new JButton("Clear");
        JButton bdec = new JButton(".");
        JButton bleftp = new JButton("(");
        JButton brightp = new JButton(")");

        JButton badd = new JButton("+");
        JButton bsub = new JButton("-");
        JButton bmult = new JButton("*");
        JButton bdiv = new JButton("÷");

        bmult.addActionListener(c);
        bdiv.addActionListener(c);
        bsub.addActionListener(c);
        badd.addActionListener(c);

        b9.addActionListener(c);
        b8.addActionListener(c);
        b7.addActionListener(c);
        b6.addActionListener(c);
        b5.addActionListener(c);
        b4.addActionListener(c);
        b3.addActionListener(c);
        b2.addActionListener(c);
        b1.addActionListener(c);
        b0.addActionListener(c);
        b00.addActionListener(c);

        beq.addActionListener(c);
        bneg.addActionListener(c);

        bsin.addActionListener(c);
        bcos.addActionListener(c);
        btan.addActionListener(c);

        bxpon.addActionListener(c);
        bLog.addActionListener(c);
        bln.addActionListener(c);

        bper.addActionListener(c);
        bsqr.addActionListener(c);
        bClear.addActionListener(c);
        bdec.addActionListener(c);
        bleftp.addActionListener(c);
        brightp.addActionListener(c);

        buttonPanel.setLayout(new GridLayout(6,5));
        buttonPanel.add(bleftp);
        buttonPanel.add(brightp);
        buttonPanel.add(bper);
        buttonPanel.add(bsqr);
        buttonPanel.add(bClear);
        buttonPanel.add(bsin);
        buttonPanel.add(bcos);
        buttonPanel.add(btan);
        buttonPanel.add(bdiv);
        buttonPanel.add(bneg);
        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(bmult);
        buttonPanel.add(bxpon);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(bsub);
        buttonPanel.add(bLog);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(badd);
        buttonPanel.add(bln);
        buttonPanel.add(b0);
        buttonPanel.add(b00);
        buttonPanel.add(bdec);
        buttonPanel.add(new JButton(""));
        buttonPanel.add(beq);
        buttonPanel.setPreferredSize(new Dimension(400, 400));

        containerPanel.add(buttonPanel);
        frame.getContentPane().add(containerPanel);
    }

    /**
     ** Name: actionPerformed
     ** This function
     **
     ** @param e the ActionEvent being checked for
     **
     **/
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append(s);
        }
        else if (s.charAt(0) == 'C') {
            textFieldString.setLength(0);
            equation.setLength(0);
            textField.setText(String.valueOf(textFieldString));
        }
        else if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '÷' || s.charAt(0) == '√') {
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append(s);
        }
        else if (s.charAt(0) == '=') {
            if(equation.length() != 0){
                textField.setText(String.valueOf(calculator.calculate(equation.toString())));
                textFieldString.setLength(0);
                equation.setLength(0);
            }
        }

    }

    /**
     ** Name: createAndShowGUI
     ** This function
     **
     **/
    private static void createAndShowGUI() {
        equation = new StringBuilder(50);

        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(255, 255, 255));
        yellowLabel.setPreferredSize(new Dimension(1000, 1000));

        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        textFieldString = new StringBuilder();
        textField = new JTextField(16);
        textField.setEditable(false);
        frame.getContentPane().add(textField, BorderLayout.PAGE_START);

        setUpButtons(frame);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     ** Name: main
     ** This is the main function that runs the calc_screen class
     **
     ** @param args list of arguments given (should be null)
     **
     **/
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
