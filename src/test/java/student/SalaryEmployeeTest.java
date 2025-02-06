package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {
    private SalaryEmployee nami;

    @BeforeEach
    void setUp() {
        nami = new SalaryEmployee("Nami", "s193", 200000,
                17017, 4983, 1000);
    }

    @Test
    void calculateGrossPay() {
        int NUM_PAYMENTS = 24;
        double expectedPay = nami.getPayRate() / NUM_PAYMENTS;
        assertEquals(expectedPay, nami.calculateGrossPay(0));

    }

}