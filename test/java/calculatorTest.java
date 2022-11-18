import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {
    @Test
    void addTest() {
        assertEquals(4, calculator.add(2, 2));
        assertEquals(22, calculator.add(12, 10));
        assertEquals(0, calculator.add(0, 0));
    }

    @Test
    void subTest() {
        assertEquals(0, calculator.sub(2, 2));
        assertEquals(-5, calculator.sub(10, 15));
        assertEquals(0, calculator.sub(0, 0));
    }

    @Test
    void mulTest() {
        var calculator = new calculator();
        assertEquals(4, calculator.mul(2, 2));
        assertEquals(-120, calculator.mul(12, -10));
        assertEquals(0, calculator.mul(0, 123));
    }

    @Test
    void divTest() {
        var calculator = new calculator();
        assertEquals(1, calculator.div(2, 2));
        assertEquals(1.2, calculator.div(12, 10));
        assertEquals(0, calculator.div(0, 46));
    }

    @Test
    void isNumericTest() {
        assertTrue(calculator.isNumeric("12"));
        assertFalse(calculator.isNumeric("+-"));
    }

    @Test
    void calculate2Test() {//TODO More rigorous testing
        var calculator = new calculator();
        Queue<String> example = new LinkedList<>();
        example.add("4");
        example.add("18");
        example.add("9");
        example.add("3");
        example.add("-");
        example.add("÷");
        example.add("+");
        assertEquals(example, calculator.extendedCalculate("4+18÷(9-3)"));
    }

    @Test
    void evalTestBasic() {
        String equ = "2+2";
        assertEquals(4, calculator.eval(equ));
        String equ1 = "2+4*2";
        assertEquals(10, calculator.eval(equ1));
        assertEquals(7, calculator.eval("3 + 4"));
        assertEquals(12, calculator.eval("3 * 4"));
        assertEquals(-1, calculator.eval("3 - 4"));
        assertEquals(0.75, calculator.eval("3 ÷ 4"));
    }

    @Test
    void evalTestAdv(){
        assertEquals(15.7, calculator.eval("12+4-3*1.2/12"));
        String equ2 = "(2+4)*2";
        assertEquals(12, calculator.eval(equ2));
        String equ3 = "4+18÷(9-3)";
        assertEquals(7, calculator.eval(equ3));
        String equ4 = "9+2-(1÷(2-4)+1)";
        assertEquals(10.5, calculator.eval(equ4));
    }

    @Test
    void regexTest(){
        System.out.println(Arrays.toString("√99+2-(√11÷(2-4)+1)".split("(?=[-+*÷()])|(?<=[^-+*÷][-+*÷])|(?<=[()])|(?<=√)")));
        System.out.println(Arrays.toString("√99+2-(√11÷(2-4)+1)".split("(?<=\\D)(?=\\d)")));
    }

    @Test
    void checkValidTest() {
        assertTrue(calculator.checkNotValid("99"));
        assertFalse(calculator.checkNotValid("+"));
    }
}