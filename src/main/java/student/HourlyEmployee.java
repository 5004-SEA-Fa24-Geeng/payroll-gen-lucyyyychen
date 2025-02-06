package student;

public class HourlyEmployee extends Employee {
    public HourlyEmployee(String name, String ID, double payRate,
                          double YTDEarnings, double YTDTaxesPaid, double pretaxDeductions) {
        //  call to the constructor of the parent class
        super(name, ID, payRate, "HOURLY", YTDEarnings, YTDTaxesPaid, pretaxDeductions);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        int NORMAL_HOURS = 40;
        double OT_BONUS_RATE = 1.5;

        if (hoursWorked > NORMAL_HOURS) {
            return getPayRate() * ( NORMAL_HOURS + OT_BONUS_RATE * (hoursWorked - NORMAL_HOURS) );
        } else {
            return getPayRate() * hoursWorked;
        }
    }
}
