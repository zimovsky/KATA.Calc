import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello and welcome!");
        System.out.println("Input: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        try {
            System.out.println("Output: " + calc(input));
        } catch (Exception e) {
            // Do nothing...
        }

    }

    public static String calc(String input) throws Exception {

        input = input.toUpperCase();
        String[] args;
        String operator;
        boolean romanDigits = false;
        int a;
        int b;
        int result;
        String output;

        try {
            args = input.split(" ");
            if (args.length == 3) {

                if (isOperator(args[1])) {

                    operator = args[1];

                    if (isRoman(args[0])) {
                        if (isRoman(args[2])) {
                            romanDigits = true;
                            a = r2a(args[0]);
                            b = r2a(args[2]);
                        }
                        else throw new Exception("Either both Arabic or Roman digits should be used!");
                    } else if (isArabic(args[0])) {
                        if (isRoman(args[2])) throw new Exception("Either both Arabic or Roman digits should be used!");
                        else {
                            a = Integer.parseInt(args[0]);
                            b = Integer.parseInt(args[2]);
                        }
                    } else throw new Exception("Only both Arabic or Roman digits should be used!");

                    result = switch (operator) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> a / b;
                        default -> throw new Exception("Incorrect operator!");
                    };

                    if (romanDigits) {
                        if (result > 0) {
                            output = a2r(result);
                        } else throw new Exception("Calculation result is less than 1 (" + result + ")!" );
                    } else {
                        output = String.valueOf(result);
                    }
                }
                else {
                    throw new Exception("Invalid operator!");
                }
            }
            else  throw new Exception("Invalid input format!");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            throw e;
        }

        return output;

    }

    static boolean isRoman(String input) {
        return switch (input) {
            case "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" -> true;
            default -> false;
        };
    }
    static boolean isArabic(String input) {
        int arg = Integer.parseInt(input);
        return arg >= 1 & arg <= 10;
    }
    static boolean isOperator(String operator) {
        return switch (operator) {
            case "+", "-", "/", "*" -> true;
            default -> false;
        };
    }

    static int r2a(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
    static String a2r(int arabic) {
        String result = "";
        if (arabic >= 100) {
            result += "C";
            arabic -= 100;
        }
        if (arabic >= 50) {
            result += "L";
            arabic -= 50;
        }
        else if (arabic >= 40) {
            result += "XL";
            arabic -= 40;
        }
        else if (arabic >= 10) {
            result += "X";
            arabic -= 10;
        }
        else if (arabic == 9) {
            result += "IX";
            arabic -= 9;
        }
        else if (arabic >= 5) {
            result += "V";
            arabic -= 5;
        }
        else if (arabic == 4) {
            result += "IV";
            arabic -= 4;
        }
        else if (arabic >= 1) {
            result += "I";
            arabic -= 1;
        }
        else if (arabic == 0) {
            return result;
        }
        return result + a2r(arabic);
    }

}