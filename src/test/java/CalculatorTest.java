import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTest {
    @BeforeSuite
    public void suitStarted() {
        System.out.println("Testing suite has started");
    }
    @BeforeMethod
    public void started(){
        System.out.println("Test has started");
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
        Assert.assertEquals(calculator.divide(30,30), 1);
    }
    @Test
    public void testMultiplicationMethod() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.multiply(30,20), 600);
    }
    @AfterSuite
    public void suitEnded() {
        System.out.println("Testing suite has ended");
    }
}
