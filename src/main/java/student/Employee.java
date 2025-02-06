package student;

public abstract class Employee implements IEmployee {
    private String employeeType;
    private String name;
    private String ID;
    private double payRate;
    private double pretaxDeductions;
    private double YTDEarnings;
    private double YTDTaxesPaid;

    public Employee (String name, String ID, double payRate, String employeeType,
                     double YTDEarnings, double YTDTaxesPaid, double pretaxDeductions) {
        this.employeeType = employeeType;
        this.name = name;
        this.ID = ID;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
    }

    /**
     * Gets the employee's name.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the ID of the employee
     */
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return the pay rate of the employee
     */
    @Override
    public double getPayRate() {
        return this.payRate;
    }


    /**
     * Gets the employee's Type as a string.
     * Either "HOURLY" or "SALARY" depending on the type of employee.
     *
     * You may want to consider using an enum to store
     * the type, and using .name() to get the string representation.
     *
     * @return the type of the employee as a string
     */
    @Override
    public String getEmployeeType() {
        return this.employeeType;
    }

    /**
     * Gets the YTD earnings of the employee.
     * @return the YTD earnings of the employee
     */
    @Override
    public double getYTDEarnings() {
        return this.YTDEarnings;
    }

    /**
     * Gets the YTD taxes paid by the employee.
     * @return the YTD taxes paid by the employee
     */
    @Override
    public double getYTDTaxesPaid() {
        return this.YTDTaxesPaid;
    }

    /**
     * Gets pretax deductions for the employee. Yes, on a normal paycheck this varies as either set
     * amounts or percents, and often more than one type of deduction.
     * For now, you can just assume a single pretax deduction as a whole dollar amount.
     *
     * @return the pretax deductions for the employee
     */
    @Override
    public double getPretaxDeductions() {
        return this.pretaxDeductions;
    }

    // create an protected abstract calculateGrossPay(double hoursWorked)
    //     * method to calculate the gross pay for the pay period, as runPayroll is exactly
    //     * the same for both SalaryEmployee and HourlyEmployee, but calculateGrossPay is different.
    protected abstract double calculateGrossPay(double hoursWorked);


    /**
     * @param hoursWorked the hours worked for the pay period
     * @return the pay stub for the current pay period, update the YTD earnings, and update the taxes paid YTD.
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        double TAX_RATE = 0.2265;

        // If either type of employee has < 0 hours, they are skipped this payroll period.
        if (hoursWorked < 0) {
            return null;
        }

        // Call the protected abstract method to calculate gross pay
        double grossPay = calculateGrossPay(hoursWorked);

        // calculate Net Pay and Tax
        double netPayBeforeTax = grossPay - this.pretaxDeductions;
        double tax = netPayBeforeTax * TAX_RATE;
        double netPayAfterTax = netPayBeforeTax - tax;

        // update the YTD earnings and the taxes paid YTD
        this.YTDEarnings += grossPay;
        this.YTDTaxesPaid += tax;

        // create and return the pay stub
        return new PayStub(this.name, this.ID, grossPay, netPayAfterTax, tax, this.YTDEarnings, this.YTDTaxesPaid);
    }

    /**
     * Converts the employee to a CSV string.
     * Format of the String s as follows:
     * employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     *
     * @return the employee as a CSV string
     */
    @Override
    public String toCSV() {
        return this.employeeType + "," + this.name + "," + this.ID + "," + this.payRate + ","
                + this.pretaxDeductions + "," + this.YTDEarnings + "," + this.YTDTaxesPaid + ",";
    }

}
