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
    public static double factorial(double n){
        if(n == 0){
            return 1;
        }
        return n * factorial(n - 1);
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
        String parsed_equ = equ.replaceAll("\\s","");
        parsed_equ = parsed_equ.replaceAll("/","÷");
        String[] parts = parsed_equ.split("(?<=[-+*÷√])|(?=[-+*÷√])");
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

    // Trying new form of calculating equations
    // Consider changing equation to abstract syntax tree and performing bottom up evaluation
    //  to get PEMDAS OR Shunting Yard Algorithm
    public static Queue<String> extendedCalculate(String equ){
        String parsed_equ = equ.replaceAll("\\s","");
        parsed_equ = parsed_equ.replaceAll("/","÷");
        // TODO More rigorous testing for regex
        String[] parts = parsed_equ.split("(?=[-+*÷()^!])|(?<=[^-+*÷][-+*÷])|(?<=[()]|(?<=√)|(?<=s)|(?<=c)|(?<=t)|(?<=l)|(?<=n))|(?<=\\^)|(?<=!)");
        Queue<String> aspects = new LinkedList<>();
        Stack<String> operators = new Stack<>();

        for(String part: parts){
            if(isNumeric(part)){
                aspects.add(part);
            }
            else{
                // TODO More testing for operator combinations and equations
                switch (part) {
                    case "(" -> operators.push(part);
                    case "+", "-", "*", "÷"-> {
                        while (!operators.isEmpty() && (operators.peek().equals("√") || operators.peek().equals("^") || operators.peek().equals("!"))){
                            aspects.add(operators.pop());
                        }
                        while (!operators.isEmpty() && (operators.peek().equals("*") || operators.peek().equals("÷"))){
                            aspects.add(operators.pop());
                        }
                        operators.push(part);
                    }
                    case "√", "^", "!", "s", "c", "t", "l", "n" -> {
                        operators.push(part);
                    }
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

        while(!operators.isEmpty()){
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
        Queue<String> aspects = extendedCalculate(equ);
        if(aspects == null){
            System.err.println("Error: No equation to evaluate. Ending Program");
            return Double.NaN;
        }
        Stack<String> solver = new Stack<>();
        String current;
        double total;
        double val1;
        double val2;

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
