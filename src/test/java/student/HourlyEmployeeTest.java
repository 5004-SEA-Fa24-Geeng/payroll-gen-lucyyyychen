package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    private HourlyEmployee Luffy;

    @BeforeEach
    void setUp() {
        Luffy = new HourlyEmployee("Luffy", "s192", 30.00,
                20000, 4530, 0);
    }

    @Test
    void testCalculateGrossPayNormalHours() {
        int hourWorked = 30;
        double expectedGrossPay = Luffy.getPayRate() * hourWorked;
        assertEquals(expectedGrossPay, Luffy.calculateGrossPay(hourWorked));

    }

    @Test
    void testCalculateGrossPayOverTime() {
        int hourWorked = 50;
        int NORMAL_HOURS = 40;
        double OT_BONUS_RATE = 1.5;
        double expectedGrossPay = Luffy.getPayRate() * ( NORMAL_HOURS + OT_BONUS_RATE * (hourWorked - NORMAL_HOURS) );
        assertEquals(expectedGrossPay, Luffy.calculateGrossPay(hourWorked));
    }
}