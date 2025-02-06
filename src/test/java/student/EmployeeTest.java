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
                "HOURLY", 20000, 4530, 0);
        Nami = new SalaryEmployee("Nami", "s193",200000,
                "SALARY", 17017,4983,1000);

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

//    @Test
//    void runPayroll() {
//    }

    @Test
    void toCSV() {
        assertEquals("HOURLY,Luffy,s192,30.00,0,20000,4530", Luffy.toCSV());
        assertEquals("SALARY,Nami,s193,200000,1000,17017,4983", Nami.toCSV());
    }
}