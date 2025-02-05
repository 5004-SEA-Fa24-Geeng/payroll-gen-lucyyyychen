package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee Luffy;

    @BeforeEach
    void setUp() {
        this.Luffy = new Employee("HOURLY","Luffy","s192",
                30.00,0,20000,4530);
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", Luffy.getEmployeeType());
    }

    @Test
    void getName() {
        assertEquals("Luffy", Luffy.getName());
    }

    @Test
    void getID() {
        assertEquals("s192", Luffy.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(30.00, Luffy.getPayRate());
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(0, Luffy.getPretaxDeductions());
    }

    @Test
    void getYTDEarnings() {
        assertEquals(20000, Luffy.getYTDEarnings());
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(4530, Luffy.getYTDTaxesPaid());
    }

    @Test
    void runPayroll() {

    }

    @Test
    void toCSV() {
        assertEquals("HOURLY,Luffy,s192,30.00,0,20000,4530", Luffy.toCSV());
    }
}