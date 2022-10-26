import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {

    @Test
    void addTest() {
        var calculator = new calculator();
        assertEquals(4, calculator.add(2, 2));
        assertEquals(22, calculator.add(12, 10));
        assertEquals(0, calculator.add(0, 0));
    }

    @Test
    void subTest() {
        var calculator = new calculator();
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
    }

    @Test
    void calculate2Test() {
    }

    @Test
    void evalTest() {
    }

    @Test
    void checkValidTest() {
    }
}