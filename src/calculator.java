/**
 * @file calculator.java
 * @author: Eric Chen
 *
 * @Desc: This class performs the actual calculations of the program
 *  including parsing and solving the given equations
 *
 **/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class calculator {
    public static double add(double val1, double val2){return val1 + val2;}
    public static double sub(double val1, double val2){return val1 - val2;}
    public static double mul(double val1, double val2){return val1 * val2;}
    public static double div(double val1, double val2){return val1 / val2;}
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static double factorial(double n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0){
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     ** Name: calculate
     ** This function reads in an equation as a string and parses it
     **     into an actual equation that is then solved
     **     ***                                                              ***
     *         Note: This function only performs basic arithmetic
     *         (i.e. +, -, *, ÷) and square roots and serves as a first attempt
     *         at making a calculator. Aspects of this function are thus
     *         redundant to the rest of the project.
     *         Refer to extendedCalculate for more robust calculations.
     *      ***                                                              ***
     ** @param equ the equation being calculated as a string
     **
     ** @return the result of the equation
     **/
    public static double calculate(String equ){
        String parsed_equ = equ.replaceAll("\\s","");
        parsed_equ = parsed_equ.replaceAll("/","÷");
        String[] parts = parsed_equ.split("(?<=[-+*÷√])|(?=[-+*÷√])");
        // First part of equation should be a number or √
        if(!isNumeric(parts[0]) && !parts[0].equals("√")){
            System.err.println("Error: Input not of proper equation form.");
            return Double.NaN;
        }

        int start = 0;

        while(start < parts.length && !isNumeric(parts[start])) {
            start++;
        }

        if(start >= parts.length){
            System.err.println("Error: Input not of proper equation form.");
            return Double.NaN;
        }

        boolean sqr = parts[0].equals("√");

        double total = Double.parseDouble(parts[start]);
        double val1;

        for(int i = start + 1; i < parts.length; i++) {
            if (isNumeric(parts[i])) {
                val1 = Double.parseDouble(parts[i]);
                switch (parts[i - 1]) {
                    case "+" -> total = add(total, val1);
                    case "-" -> total = sub(total, val1);
                    case "*" -> total = mul(total, val1);
                    case "÷" -> total = div(total, val1);
                    default -> {
                        System.err.println("Error: Input not of proper equation form.");
                        return Double.NaN;
                    }
                }
            }
            else if(checkNotValid(parts[i])){
                System.err.println("Error: Input not of proper equation form.");
                return Double.NaN;
            }
        }

        if(sqr){
            return Math.sqrt(total);
        }

        return total;
    }


    /**
     * Name: extendedCalculate
     * This function reads in an equation and converts it into Reverse Polish Notation
     *      form (RPN) (3+4-2 becomes 342-+ in RPN) for usage in a Shunting Yard algorithm
     *
     * @param equ the equation being calculated in a string format
     *
     * @return the equation converted into Reverse Polish Notation and stored in a Queue
     */
    public static Queue<String> extendedCalculate(String equ){
        // Removes all the spaces in the equation
        String parsed_equ = equ.replaceAll("\\s","");
        // Changes every / symbol into ÷ because the regex parser uses ÷
        parsed_equ = parsed_equ.replaceAll("/","÷");
        // Separates the given equation into an array of numbers and operators
        //  that can be parsed in the next section
        String[] parts = parsed_equ.split("(?=[-+*÷()^!])|(?<=[^-+*÷][-+*÷])|(?<=[()]|(?<=√)|(?<=s)|(?<=c)|(?<=t)|(?<=l)|(?<=n))|(?<=\\^)|(?<=!)");
        Queue<String> aspects = new LinkedList<>();
        Stack<String> operators = new Stack<>();

        // Goes through every number and operator and sorts them into Queues and Stacks
        //  The queue is for the final product in RPF, while the stack contains all
        //  the operators
        for(String part: parts){
            // If it is a number then it is immediately added to Queue
            if(isNumeric(part)){
                aspects.add(part);
            }
            // Adds operators to the stack under specific conditions
            else{
                switch (part) {
                    case "(" -> operators.push(part);
                    case "+", "-", "*", "÷"-> {
                        // Operators sin, cos, and tam have a higher precedence than +, -, *, and ÷
                        //  so they should be performed first when going through the equation in RPN
                        //  thus they are pushed into the queue to be processed first
                        while (!operators.isEmpty() && (operators.peek().equals("s") || operators.peek().equals("c") || operators.peek().equals("t"))){
                            aspects.add(operators.pop());
                        }
                        // Operators log and ln have a higher precedence than +, -, *, and ÷
                        //  so they should be performed first when going through the equation in RPN
                        //  thus they are pushed into the queue to be processed first
                        while (!operators.isEmpty() && (operators.peek().equals("l") || operators.peek().equals("n"))){
                            aspects.add(operators.pop());
                        }
                        // Operators √, ^, and ! have a higher precedence than +, -, *, and ÷
                        //  so they should be performed first when going through the equation in RPN
                        //  thus they are pushed into the queue to be processed first
                        while (!operators.isEmpty() && (operators.peek().equals("√") || operators.peek().equals("^") || operators.peek().equals("!"))){
                            aspects.add(operators.pop());
                        }
                        // Operators * and ÷ have a higher precedence than + and - so they should be
                        //  performed first when going through the equation in RPN
                        //  thus they are pushed into the queue to be processed first
                        while (!operators.isEmpty() && (operators.peek().equals("*") || operators.peek().equals("÷"))){
                            aspects.add(operators.pop());
                        }
                        operators.push(part);
                    }
                    case "√", "^", "!", "s", "c", "t", "l", "n" -> {
                        operators.push(part);
                    }
                    // Goes through the stack and adds the operators to the queue that are in
                    //  between the () so that they have higher precedence when solving
                    //  the equation
                    case ")" -> {
                        while (!operators.isEmpty() && !operators.peek().equals("(")) {
                            aspects.add(operators.pop());
                        }
                        if (operators.isEmpty()) {
                            System.err.println("Error: No ) found for (. Ending Program");
                            return null;
                        }
                        operators.pop();
                    }
                    default -> {
                        //System.out.println(part);
                        System.err.println("Error: Input not of proper equation form. Ending Program");
                        return null;
                    }
                }

            }
        }

        // Puts the rest of the operators from the stack into the queue
        while(!operators.isEmpty()){
            // Double checks that every parenthesis are a matching pair
            if(operators.peek().equals("(")){
                System.err.println("Error: Extra ( found with no matching ). Ending Program");
                return null;
            }
            aspects.add(operators.pop());
        }

        return aspects;
    }

    /**
     ** Name: eval
     ** This function reads in an equation as a string and parses it
     **     into an actual equation that is then solved
     **
     ** @param equ the equation being calculated as a string
     **
     ** @return the result of the equation
     **/
    public static double eval(String equ){
        // Converts the equation string into a Queue of all the individual
        //  operators and numbers in RPN form
        Queue<String> aspects = extendedCalculate(equ);
        if(aspects == null){
            System.err.println("Error: No equation to evaluate. Ending Program");
            return Double.NaN;
        }
        // Keeps a list of all the results computed from the equation
        //  ex: 3+4-3 would have the stack keep the result from 3+4=7
        //  for usage in the -3 part
        Stack<String> solver = new Stack<>();
        String current;
        double total;
        double val1;
        double val2;

        // Goes through every number and operator until the equation
        //  is finished
        // Refer to Shunting Yard Algorithm for more in-depth look
        //  at how this works
        while(!aspects.isEmpty()){
            current = aspects.poll();
            if(isNumeric(current)){
                solver.push(current);
            }
            else{
                switch (current) {
                    case "+" -> {
                        val1 = Double.parseDouble(solver.pop());
                        val2 = Double.parseDouble(solver.pop());
                        total = add(val1, val2);
                        solver.push(Double.toString(total));
                    }
                    case "-" -> {
                        val1 = Double.parseDouble(solver.pop());
                        val2 = Double.parseDouble(solver.pop());
                        total = sub(val2, val1);
                        solver.push(Double.toString(total));
                    }
                    case "*" -> {
                        val1 = Double.parseDouble(solver.pop());
                        val2 = Double.parseDouble(solver.pop());
                        total = mul(val1, val2);
                        solver.push(Double.toString(total));
                    }
                    case "÷" -> {
                        val1 = Double.parseDouble(solver.pop());
                        val2 = Double.parseDouble(solver.pop());
                        total = div(val2, val1);
                        solver.push(Double.toString(total));
                    }
                    case "√" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.sqrt(val1);
                        solver.push(Double.toString(total));
                    }
                    case "^" -> {
                        val1 = Double.parseDouble(solver.pop());
                        val2 = Double.parseDouble(solver.pop());
                        total = Math.pow(val2, val1);
                        solver.push(Double.toString(total));
                    }
                    case "!" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = factorial(val1);
                        solver.push(Double.toString(total));
                    }
                    case "s" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.sin(val1);
                        solver.push(Double.toString(total));
                    }
                    case "c" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.cos(val1);
                        solver.push(Double.toString(total));
                    }
                    case "t" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.tan(val1);
                        solver.push(Double.toString(total));
                    }
                    case "l" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.log10(val1);
                        solver.push(Double.toString(total));
                    }
                    case "n" -> {
                        val1 = Double.parseDouble(solver.pop());
                        total = Math.log(val1);
                        solver.push(Double.toString(total));
                    }
                    default -> {
                        System.err.println("Error: Input syntax detected. Ending Program");
                        return Double.NaN;
                    }
                }
            }
        }

        // The final value solved and pushed into the solver stack
        //  should be the final result of the equation
        return Double.parseDouble(solver.pop());
    }

    /**
     ** Name: checkNotValid
     ** This function checks if the string given is a valid operator
     **
     ** @param str the string that is being checked
     **
     ** @return whether the string is a valid operator or not
     **/
    public static boolean checkNotValid(String str){
        return !switch (str) {
            case "+", "-", "*", "÷" -> true;
            default -> false;
        };
    }

    /**
     ** Name: main
     ** This is the main function that runs the calculator class
     **     in command line form
     ** @param args list of arguments given (should be null)
     **
     **/
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.print("Enter an equation: ");
            String equation = reader.nextLine();
            double total = calculate(equation);

            if(Double.isNaN(total)){
                running = false;
            }

            if(running){
                System.out.println(total);
            }
        }

        reader.close();
    }
}
