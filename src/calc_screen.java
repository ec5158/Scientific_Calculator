/**
 * @file calc_screen.java
 * @author: Eric Chen
 *
 * @Desc: This class displays the GUI of the calculator and
 *  handles how the buttons work
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
     ** This function creates a panel in the GUI that contains all
     **     the buttons needed for the calculator to work
     **
     ** @param frame the frame that the buttons will be put on
     **
     **/
    private static void setUpButtons(JFrame frame){
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        calc_screen c = new calc_screen();

        // Basic numbers
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

        // Equal and factorial
        JButton beq = new JButton("=");
        JButton bfact = new JButton("!");

        // Sin, cos, and tan
        JButton bsin = new JButton("sin");
        JButton bcos = new JButton("cos");
        JButton btan = new JButton("tan");

        // Exponential, logarithmic, and natural log
        JButton bxpon = new JButton("^");
        JButton bLog = new JButton("log");
        JButton bln = new JButton("ln");

        // Percent, square root, and clear
        JButton bper = new JButton("%");
        JButton bsqr = new JButton("√");
        // Clear button removes current equation
        //  from screen and internal storage
        JButton bClear = new JButton("Clear");

        // Decimal and parenthesis
        JButton bdec = new JButton(".");
        JButton bleftp = new JButton("(");
        JButton brightp = new JButton(")");

        // Addition, subtraction, multiplication, and division
        JButton badd = new JButton("+");
        JButton bsub = new JButton("-");
        JButton bmult = new JButton("*");
        JButton bdiv = new JButton("÷");

        // addActionListener adds the function
        //  that causes pressing the button to do something
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
        bfact.addActionListener(c);

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

        // Adds all the buttons onto a grid-like layout
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
        buttonPanel.add(bfact);
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

        // Adds the button panel to the rest of the screen
        containerPanel.add(buttonPanel);
        frame.getContentPane().add(containerPanel);
    }

    /**
     ** Name: actionPerformed
     ** This function updates the calculator screen GUI
     **     depending on what button is pushed
     **
     ** @param e the ActionEvent being checked for (what button is being pushed)
     **
     **/
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        // Adds the number and/or decimal to the equation and screen
        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append(s);
        }
        // Clears the screen and resets the storied equation to prepare for a new one
        else if (s.charAt(0) == 'C') {
            textFieldString.setLength(0);
            equation.setLength(0);
            textField.setText(String.valueOf(textFieldString));
        }
        // Adds the operation to the screen and the equation
        else if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '÷' || s.charAt(0) == '√') {
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append(s);
        }
        else if (s.charAt(0) == '^' || s.charAt(0) == '!' || s.charAt(0) == '(' || s.charAt(0) == ')'){
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append(s);
        }
        // Achieves percentages by converting the number into a decimal with ÷100
        else if (s.charAt(0) == '%'){
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append("÷100");
        }
        // Sin, single letters are easier to interpret with regex than full words
        else if (s.charAt(0) == 's'){
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append("s");
        }
        // Cos, single letters are easier to interpret with regex than full words
        else if (s.charAt(0) == 'c'){
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append("c");
        }
        // Tan, single letters are easier to interpret with regex than full words
        else if (s.charAt(0) == 't'){
            textField.setText(String.valueOf(textFieldString.append(s)));
            equation.append("t");
        }
        // Logarithmic operators
        else if (s.charAt(0) == 'l'){
            // Natural log
            if(s.charAt(1) == 'n'){
                textField.setText(String.valueOf(textFieldString.append(s)));
                equation.append("n");
            }
            // Otherwise regular log
            else{
                textField.setText(String.valueOf(textFieldString.append(s)));
                equation.append("l");
            }

        }
        // Send the equation to calculator.java to evaluate it and then
        //  displays the answer onto the screen
        else if (s.charAt(0) == '=') {
            if(equation.length() != 0){
                textField.setText(String.valueOf(calculator.eval(equation.toString())));
                textFieldString.setLength(0);
                equation.setLength(0);
            }
        }

    }

    /**
     ** Name: createAndShowGUI
     ** This function creates the actual calculator screen GUI
     **
     **/
    private static void createAndShowGUI() {
        equation = new StringBuilder(50);

        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Some background creation
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(255, 255, 255));
        yellowLabel.setPreferredSize(new Dimension(1000, 1000));

        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        // textFieldString keeps track of the equation
        textFieldString = new StringBuilder();
        // This is the field of the GUI where the equation and later
        //  solution are displayed
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
