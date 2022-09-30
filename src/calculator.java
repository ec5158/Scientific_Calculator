import java.util.Scanner;

public class calculator {
    public static double add(double val1, double val2){return val1 + val2;}
    public static double sub(double val1, double val2){return val1 - val2;}
    public static double mul(double val1, double val2){return val1 * val2;}
    public static double div(double val1, double val2){return val1 / val2;}
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static double calculate(String equ){
        String[] parts = equ.split("(?<=[-+*÷])|(?=[-+*÷])");
        if(!isNumeric(parts[0])){
            System.err.println("Error: Input not of proper equation form.");
            return -1;
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
        return total;
    }

    public static boolean checkValid(String str){
        return !switch (str) {
            case "+", "-", "*", "÷" -> true;
            default -> false;
        };
    }
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
