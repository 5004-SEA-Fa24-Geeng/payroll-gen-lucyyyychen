package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee Luffy;  // class-level field
    private Employee Nami;

    @BeforeEach
    void setUp() {
        Luffy = new HourlyEmployee("Luffy", "s192", 30.00,
                20000, 4530, 0);
        Nami = new SalaryEmployee("Nami", "s193",200000,
                17017,4983,1000);

    }

    @Test
    void testRoundToTwoDecimals() {
        assertEquals(20000.00, Luffy.roundToTwoDecimals(Luffy.getYTDEarnings()));
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
        assertEquals("s193", Nami.getID());
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
    void testNegativeHours() {
        IPayStub payStubNami = Nami.runPayroll(-10);
        assertNull(payStubNami);
    }

    @Test
    void testSalaryEmployee() {
        IPayStub payStubNami = Nami.runPayroll(0);
        assertNotNull(payStubNami);

        // check calculateGrossPay method
        // Nami.getPayRate() = 200000
        double expectedGrossPay = Nami.getPayRate() / 24; // 8333.33
        assertEquals(expectedGrossPay, Nami.calculateGrossPay(0));

        // update YTDTaxesPaid
        // Nami.getPretaxDeductions() = 1000;
        // Nami.getYTDTaxesPaid() = 4983
        double expectedTaxes = (expectedGrossPay - Nami.getPretaxDeductions()) * 0.2265;  // 1661
        double newYTDTaxesPaid = 4983 + expectedTaxes;  // 6644
        assertEquals(newYTDTaxesPaid, Nami.getYTDTaxesPaid());

        // check update of YTDEarning (after tax)
        // Nami.getYTDEarnings() = 17017
        double expectedNetPay = Nami.roundToTwoDecimals(expectedGrossPay - Nami.getPretaxDeductions() - expectedTaxes);   // 5672.33
        double newYTDEarnings = 17017 + expectedNetPay;  // 22689.33
        assertEquals(newYTDEarnings, Nami.getYTDEarnings());

        // check all methods in the PayStub class
        assertEquals(expectedNetPay, payStubNami.getPay());
        assertEquals(newYTDTaxesPaid, payStubNami.getTaxesPaid());
        assertEquals("Nami,5672.33,1661.0,22689.33,6644.0", payStubNami.toCSV());
    }

    @Test
    void testHourlyEmployeeNormalHours() {
        IPayStub payStubLuffyNormalHours = Luffy.runPayroll(30);
        assertNotNull(payStubLuffyNormalHours);

        // check calculateGrossPay method
        // Luffy.getPayRate() = 30.00
        // Luffy.getYTDTaxesPaid() = 4530
        double expectedGrossPay = 30.00 * 30;  // 900.00
        assertEquals(expectedGrossPay, Luffy.calculateGrossPay(30));

        // check update of YTDTaxesPaid
        // Luffy.getPretaxDeductions() = 0
        double expectedTaxes = (expectedGrossPay - Luffy.getPretaxDeductions()) * 0.2265;  // expectedTaxes = 203.85
        double newYTDTaxesPaid = 4530 + expectedTaxes;  // newYTDTaxesPaid = 4733.85
        assertEquals(newYTDTaxesPaid, Luffy.getYTDTaxesPaid());  // 4733.85

        // check update of YTDEarning (after tax)
        double expectedNetPay = (expectedGrossPay - Luffy.getPretaxDeductions()) - expectedTaxes;  // 696.15
        double newYTDEarnings = 20000 + expectedNetPay;  // 20696.15
        assertEquals(newYTDEarnings, Luffy.getYTDEarnings());  // 20696.15

        // check all methods in the PayStub class
        assertEquals(expectedNetPay, payStubLuffyNormalHours.getPay());  // 696.15
        assertEquals(newYTDTaxesPaid, payStubLuffyNormalHours.getTaxesPaid());  // 4733.85
        assertEquals("Luffy,696.15,203.85,20696.15,4733.85", payStubLuffyNormalHours.toCSV());
    }

    @Test
    void testHourlyEmployeeOvertime() {
        IPayStub payStubLuffyNormalHours = Luffy.runPayroll(50);
        assertNotNull(payStubLuffyNormalHours);

        // check calculateGrossPay method
        // Luffy.getPayRate() = 30.00
        // Luffy.getYTDTaxesPaid() = 4530
        double expectedGrossPay = Luffy.roundToTwoDecimals(30.00 * 40 + 1.5 * 30.00 * 10);  // 1650.00
        assertEquals(expectedGrossPay, Luffy.calculateGrossPay(50));

        // check update of YTDTaxesPaid
        // Luffy.getPretaxDeductions() = 0
        double expectedTaxes = Luffy.roundToTwoDecimals((expectedGrossPay - Luffy.getPretaxDeductions()) * 0.2265);  // expectedTaxes = 373.725
//        assertEquals(373.73, expectedTaxes);
        double newYTDTaxesPaid = 4530 + expectedTaxes;  // newYTDTaxesPaid = 4903.725
        assertEquals(4903.73, Luffy.getYTDTaxesPaid());  // 4903.725

        // check update of YTDEarning (after tax)
        double expectedNetPay = (expectedGrossPay - Luffy.getPretaxDeductions()) - expectedTaxes;  // 1276.275
        double newYTDEarnings = 20000 + expectedNetPay;  // 21276.275
        assertEquals(newYTDEarnings, Luffy.getYTDEarnings());  // 21276.275

        // check all methods in the PayStub class
        assertEquals(expectedNetPay, payStubLuffyNormalHours.getPay());  // 1276.275
        assertEquals(newYTDTaxesPaid, payStubLuffyNormalHours.getTaxesPaid());  // 4903.725
        // this.name + "," + this.netPay + "," + this.tax + "," + this.YTDEarnings + "," + this.YTDTaxesPaid;
        assertEquals("Luffy,1276.27,373.73,21276.27,4903.73", payStubLuffyNormalHours.toCSV());
    }


    @Test
    void toCSV() {
        assertEquals("HOURLY,Luffy,s192,30.0,0.0,20000.0,4530.0", Luffy.toCSV());
        assertEquals("SALARY,Nami,s193,200000.0,1000.0,17017.0,4983.0", Nami.toCSV());
    }
}