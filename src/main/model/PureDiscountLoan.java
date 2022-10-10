package model;

public class PureDiscountLoan extends Loan {
    private final String name;              // The name of this loan
    private final double amount;            // Amount borrowed by the borrower
    private final double rate;              // Annual interest rate to determine amount of interest
    private final int length;               // Length of the loan remaining in years

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, loanLength >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          rate is set to interestRate, length is set to loanLength.
    public PureDiscountLoan(String loanName, double loanAmount, double interestRate, int loanLength) {
        name   = loanName;
        amount = loanAmount;
        rate   = interestRate;
        length = loanLength;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return rate;
    }

    public int getLength() {
        return length;
    }

    // EFFECTS: calculate the future value of the loan,
    public double calculateFV() {
        double fv = amount * Math.pow((1 + rate), length);
        return Math.round((fv * 100) / 100.0);
    }

}

