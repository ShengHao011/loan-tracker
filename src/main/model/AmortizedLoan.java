package model;

public class AmortizedLoan extends Loan {
    private final String name;           // The name of this loan
    private double principalAmount;       // Amount borrowed by the borrower
    private final double rate;            // Annual interest rate to determine amount of interest
    private int yearsRemaining;          // Length of the loan remaining in years
    private final double yearlyAnnuities;      // Total payment each year for the loan

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, loanLength >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          rate is set to interestRate, yearsRemaining is set to loanLength.
    public AmortizedLoan(String loanName, float loanAmount, double interestRate, int loanLength) {
        name            = loanName;
        principalAmount = loanAmount;
        rate            = interestRate;
        yearsRemaining  = loanLength;
        yearlyAnnuities = Math.round((principalAmount
                / ((1 - Math.pow((1 + rate), (-1 * yearsRemaining))) / rate)) * 100) / 100.0;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public double getRate() {
        return rate;
    }

    public int getYearsRemaining() {
        return yearsRemaining;
    }

    public double getYearlyAnnuities() {
        return yearlyAnnuities;
    }

    // EFFECTS: calculates the interest paid for the year
    public double calculateInterestPaid() {
        return principalAmount * rate;
    }

    // MODIFIES: this
    // EFFECTS: deduct yearsRemaining by 1, if yearsRemaining is 0, then keep it at 0
    public void deductYearsRemaining() {
        if (yearsRemaining == 0) {
            yearsRemaining = 0;
        } else {
            yearsRemaining = yearsRemaining - 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: subtracts principle amount by principle paid
    public void deductPrincipleAmount() {
        principalAmount = principalAmount - (yearlyAnnuities - calculateInterestPaid());
    }

}
