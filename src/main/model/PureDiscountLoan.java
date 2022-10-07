package model;

public class PureDiscountLoan {
    private String name;              // The name of this loan
    private int amount;               // Amount borrowed by the borrower
    private double interestrate;      // Annual interest rate to determine amount of interest
    private int length;               // Length of the loan

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, installmentYears >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          interestRate is set to interestRate, installmentYears is set to loanLength.

    public PureDiscountLoan(String loanName, int loanAmount, double interestRate, int loanLength) {
        name = loanName;
        amount = loanAmount;
        interestrate = interestRate;
        length = loanLength;
    }


}
