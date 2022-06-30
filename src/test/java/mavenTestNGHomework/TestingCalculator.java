package mavenTestNGHomework;

import org.testng.Assert;
import org.testng.annotations.*;

public class TestingCalculator {

    @BeforeTest
    public void start() {
        System.out.println("The test has started!");
    }

    @Test
    public void testAddMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.add(30,20), 50);
    }

    @Test
    public void testSubtractMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.substract(30,20), 10);
    }

    @Test
    public void testDivisionMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.divide(30,20), 1.5);
    }

    @Test
    public void testMultiplicationMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.multiply(30,20), 600);
    }
}
