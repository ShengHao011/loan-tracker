package model;

public class AmortizedLoan {
    private final String name;
    private double balance;
    private final double rate;
    private int yearsRemaining;

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, loanLength >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          rate is set to interestRate, yearsRemaining is set to loanLength.
    public AmortizedLoan(String loanName, double loanAmount, double interestRate, int loanLength) {
        name           = loanName;
        balance        = loanAmount;
        rate           = interestRate;
        yearsRemaining = loanLength;
    }



}
