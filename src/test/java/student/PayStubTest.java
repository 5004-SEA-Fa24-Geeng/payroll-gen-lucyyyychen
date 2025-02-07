package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {

    private PayStub payStub1;

    @BeforeEach
    void setUp() {
        payStub1 = new PayStub("Luffy", "s192", 30.00, 20000.00, 4530.00, 0.00, 100.00);
    }

    @Test
    void getPay() {
        assertEquals(20000.00, payStub1.getPay());

    }

    @Test
    void getTaxesPaid() {
        assertEquals(100.00, payStub1.getTaxesPaid());
    }

    @Test
    void toCSV() {
        // this.name + "," + this.netPay + "," + this.tax + "," + this.YTDEarnings + "," + this.YTDTaxesPaid;
        assertEquals("Luffy,20000.00,4530.00,0.00,100.00", payStub1.toCSV());
    }
}