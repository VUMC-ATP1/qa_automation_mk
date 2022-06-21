import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest {

    @Test
    public void employeeNameTest(){
        Employee emp = new Employee();
        emp.setName("Martins");
        Assert.assertEquals(emp.getName(),"Martins");
    }

    @Test
    public void employeeSurnameTest(){
        Employee emp = new Employee();
        emp.setSurname("Kruklis");
        Assert.assertEquals(emp.getSurname(),"Kruklis");
    }

    @Test
    public void employeeYearTest(){
        Employee emp = new Employee();
        emp.setYear(1991);
        Assert.assertEquals(emp.getYear(),1991);
    }

    @Test
    public void employeeRoleTest(){
        Employee emp = new Employee();
        emp.setRole("employee");
        Assert.assertEquals(emp.getRole(),"employee");
    }

    @Test
    public void testAllArguments(){
        Employee emp = new Employee("Martins","Kruklis",1991,"employee");
        Assert.assertEquals(emp.getName(),"Martins");
        Assert.assertEquals(emp.getSurname(),"Kruklis");
        Assert.assertEquals(emp.getYear(),1991);
        Assert.assertEquals(emp.getRole(),"employee");
    }

}
