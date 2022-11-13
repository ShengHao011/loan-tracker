package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;

// Represents an amortized loan with a name, principal amount at the beginning, interest rate, length of the loan,
//            and its yearly annuities.
public class AmortizedLoan extends Loan implements Writable {
    private double principalAmount;                     // Amount borrowed by the borrower
    private final double rate;                          // Annual interest rate to determine amount of interest
    private int yearsRemaining;                         // Length of the loan remaining in years
    private final double yearlyAnnuities;               // Total payment each year for the loan
    private static final String type = "AmortizedLoan"; // Type of the loan

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, loanLength >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          rate is set to interestRate, yearsRemaining is set to loanLength.
    public AmortizedLoan(String loanName, double loanAmount, double interestRate, int loanLength) {
        super(loanName);
        principalAmount = loanAmount;
        rate            = interestRate;
        yearsRemaining  = loanLength;
        int n = yearsRemaining;
        double ya = (principalAmount / ((1 - Math.pow((1 + rate), (-1 * n))) / rate));
        DecimalFormat df = new DecimalFormat("0.00");
        yearlyAnnuities = Double.parseDouble(df.format(ya));
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

    public String getType() {
        return type;
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
    // EFFECTS: subtracts principal amount by principle paid
    public void deductPrincipleAmount() {
        principalAmount = principalAmount - (yearlyAnnuities - calculateInterestPaid());
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject objJ = new JSONObject();
        objJ.put("type", type);
        objJ.put("name", this.getName());
        objJ.put("amount", principalAmount);
        objJ.put("interest rate", rate);
        objJ.put("length of loan", yearsRemaining);
        return objJ;
    }
}
