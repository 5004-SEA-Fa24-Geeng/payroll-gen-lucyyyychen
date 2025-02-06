package student;

public class PayStub implements IPayStub {
    private String name;
    private String ID;
    private double grossPay;
    private double netPay;
    private double tax;
    private double YTDEarnings;
    private double YTDTaxesPaid;

    public PayStub(String name, String ID, double grossPay, double netPay,
                   double tax, double YTDEarnings, double YTDTaxesPaid) {
        this.name = name;
        this.ID = ID;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.tax = tax;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
    }

    /**
     * Gets the pay for the current pay period.
     * @return the pay for the current pay period
     */
    public double getPay() {
        return this.netPay;
    };

    /**
     * Gets the taxes paid for the current pay period.
     * @return the taxes paid for the current pay period
     */
    public double getTaxesPaid() {
        return this.YTDTaxesPaid;
    };

    /**
     * Converts the PayStub object to a CSV string.
     * Format of the CSV string is: "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"
     *
     * @return the CSV string
     */
    public String toCSV() {
        return this.netPay + "," + this.tax + "," + this.YTDEarnings + "," + this.YTDTaxesPaid;
    };
}
