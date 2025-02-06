package student;

public class SalaryEmployee extends Employee {
    public SalaryEmployee(String name, String ID, double payRate,
                          double YTDEarnings, double YTDTaxesPaid, double pretaxDeductions) {
        //  call to the constructor of the parent class
        super(name, ID, payRate, "SALARY", YTDEarnings, YTDTaxesPaid, pretaxDeductions);
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        int NUM_PAYMENTS = 24;
        return getPayRate() / NUM_PAYMENTS;
    }
}
