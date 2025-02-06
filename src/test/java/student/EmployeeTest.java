package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee Luffy;  // class-level field
    private Employee Nami;

    @BeforeEach
    void setUp() {
//        <employees.csv>
//        employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
//        HOURLY,Luffy,s192,30.00,0,20000,4530
//        SALARY,Nami,s193,200000,1000,17017,4983

        Luffy = new HourlyEmployee("Luffy", "s192", 30.00,
                20000, 4530, 0);
        Nami = new SalaryEmployee("Nami", "s193",200000,
                17017,4983,1000);

    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", Luffy.getEmployeeType());
        assertEquals("SALARY", Nami.getEmployeeType());
    }

    @Test
    void getName() {
        assertEquals("Luffy", Luffy.getName());
        assertEquals("Nami", Nami.getName());
    }

    @Test
    void getID() {
        assertEquals("s192", Luffy.getID());
        assertEquals("s193", Nami.getName());
    }

    @Test
    void getPayRate() {
        assertEquals(30.00, Luffy.getPayRate());
        assertEquals(200000, Nami.getPayRate());
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(0, Luffy.getPretaxDeductions());
        assertEquals(1000, Nami.getPretaxDeductions());
    }

    @Test
    void getYTDEarnings() {
        assertEquals(20000, Luffy.getYTDEarnings());
        assertEquals(17017, Nami.getYTDEarnings());
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4530, Luffy.getYTDTaxesPaid());
        assertEquals(4983, Nami.getYTDTaxesPaid());
    }

    @Test
    void testHourlyEmployeeNoOvertime() {
        IPayStub payStub = Luffy.runPayroll(40);
        assertNotNull(payStub);
    }

    @Test
    void testHourlyEmployeeWithOvertime() {
        IPayStub payStub = Luffy.runPayroll(50);
        assertNotNull(payStub);
        // getPayRate() * ( NORMAL_HOURS + OT_BONUS_RATE * (hoursWorked - NORMAL_HOURS) );
//        int NORMAL_HOURS = 40;
//        double OT_BONUS_RATE = 1.5;
//        double expectedGrossPay = 30.00 * ( NORMAL_HOURS + OT_BONUS_RATE * (50 - NORMAL_HOURS) );
//        double expectedNetPayBeforeTax = expectedGrossPay - 0;
//        double expectedTax = expectedNetPayBeforeTax * 0.2265;
//        double expectedNetPayAfterTax = expectedNetPayBeforeTax - expectedTax;
//        double expectedTaxesPaid = Luffy.getYTDTaxesPaid() + expectedTax; // this.YTDTaxesPaid += tax;

//        assertEquals(expectedNetPayAfterTax, payStub.getPay());
//        assertEquals(expectedTaxesPaid, payStub.getTaxesPaid());
//        assertEquals(, payStub.toCSV());
    }

    @Test
    void testSalaryEmployee() {
        IPayStub payStub = Nami.runPayroll(0);
        assertNotNull(payStub);
    }


    @Test
    void toCSV() {
        assertEquals("HOURLY,Luffy,s192,30.00,0,20000,4530", Luffy.toCSV());
        assertEquals("SALARY,Nami,s193,200000,1000,17017,4983", Nami.toCSV());
    }
}