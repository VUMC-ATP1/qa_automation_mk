import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    @Test
    public void testAddMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.add(30.00,20.00), 50.00);
    }
    @Test
    public void testSubtractMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.subtract(30.00,20.00), 10.00);
    }

    @Test
    public void testDivisionMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.divide(30.00,20.00), 1.5);
    }

    @Test
    public void testMultiplicationMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.multiply(30.00,20.00), 600.00);
    }
}
