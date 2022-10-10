package model;

public class AmortizedLoan extends Loan {
    private final String name;           // The name of this loan
    private float principalAmount;       // Amount borrowed by the borrower
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

    public String getName() {
        return name;
    }

    public float getPrincipalAmount() {
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


}
