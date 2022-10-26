/**
 * @file calculator.java
 * @author: Eric Chen
 *
 * @Desc: This class
 *
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

    /**
     ** Name: calculate
     ** This function reads in an equation as a string and parses it
     **     into an actual equation that is then solved
     **
     ** @param equ the equation being calculated as a string
     **
     ** @return the result of the equation
     **/
    public static double calculate(String equ){
        String[] parts = equ.split("(?<=[-+*÷√])|(?=[-+*÷√])");
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

        label:
        for(int i = start + 1; i < parts.length; i++) {
            if (isNumeric(parts[i])) {
                val1 = Double.parseDouble(parts[i]);
                switch (parts[i - 1]) {
                    case "+":
                        total = add(total, val1);
                        break;
                    case "-":
                        total = sub(total, val1);
                        break;
                    case "*":
                        total = mul(total, val1);
                        break;
                    case "÷":
                        total = div(total, val1);
                        break;
                    default:
                        System.err.println("Error: Input not of proper equation form.");
                        break label;
                }
            }
            else if(checkValid(parts[i])){
                System.err.println("Error: Input not of proper equation form.");
                break;
            }
        }

        if(sqr){
            return Math.sqrt(total);
        }

        return total;
    }

    // Trying new form of calculating equations
    // Consider changing equation to abstract syntax tree and performing bottom up evaluation
    //  to get PEMDAS OR Shunting Yard Algorithm
    public static Queue<String> calculate2(String equ){
        String[] parts = equ.split("(?<=[-+*÷√])|(?=[-+*÷√])");
        Queue<String> aspects = new LinkedList<>();
        Stack<String> operators = new Stack<>();

        for(String part: parts){
            if(isNumeric(part)){
                aspects.add(part);
            }
            else{
                // TODO add sqrt, percent, and sin, cos, and tan
                switch (part) {
                    case "+", "-", "(" -> operators.push(part);
                    case "*", "÷" -> {
                        while (operators.peek().equals("*") || operators.peek().equals("÷")) {
                            aspects.add(operators.pop());
                        }
                        operators.push(part);
                    }
                    case ")" -> {
                        while (!operators.isEmpty() && !operators.peek().equals(")")) {
                            aspects.add(operators.pop());
                        }
                        if (operators.isEmpty()) {
                            System.err.println("Error: Input not of proper equation form.");
                            return null;
                        }
                        operators.pop();
                    }
                    default -> {
                        System.err.println("Error: Input not of proper equation form.");
                        return null;
                    }
                }

            }
        }

        while(!operators.isEmpty()){
            if(operators.peek().equals("(")){
                System.err.println("Error: Input not of proper equation form.");
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
        double total = 0;
        Queue<String> aspects = calculate2(equ);
        if(aspects == null){
            return Double.NaN;
        }
        // TODO Solve the equation from the queue
        while(!aspects.isEmpty()){
            aspects.poll();
        }

        return total;
    }

    /**
     ** Name: checkValid
     ** This function checks if the string given is a valid operator
     **
     ** @param str the string that is being checked
     **
     ** @return whether the string is a valid operator or not
     **/
    public static boolean checkValid(String str){
        return !switch (str) {
            case "+", "-", "*", "÷" -> true;
            default -> false;
        };
    }

    /**
     ** Name: main
     ** This is the main function that runs the calculator class
     **
     ** @param args list of arguments given (should be null)
     **
     **/
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.print("Enter an equation: ");
            String equation = reader.nextLine();
            String[] parts = equation.split("(?<=[-+*/])|(?=[-+*/])");
            if(!isNumeric(parts[0])){
                System.err.println("Error: Input not of proper equation form. Ending program.");
                break;
            }
            double total = Double.parseDouble(parts[0]);
            double val1;

            label:
            for(int i = 1; i < parts.length; i++) {
                if (isNumeric(parts[i])) {
                    val1 = Double.parseDouble(parts[i]);
                    switch (parts[i - 1]) {
                        case "+":
                            total = add(total, val1);
                            break;
                        case "-":
                            total = sub(total, val1);
                            break;
                        case "*":
                            total = mul(total, val1);
                            break;
                        case "/":
                            total = div(total, val1);
                            break;
                        default:
                            running = false;
                            System.err.println("Error: Input not of proper equation form. Ending program.");
                            break label;
                    }
                }
                else if(checkValid(parts[i])){
                    running = false;
                    System.err.println("Error: Input not of proper equation form. Ending program.");
                    break;
                }
            }

            if(running){
                System.out.println(total);
            }
        }

        reader.close();
    }
}
