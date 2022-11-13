package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;

// Represents a pure discount loan that has a name, loan amount, interest rate, length of the loan,
public class PureDiscountLoan extends Loan implements Writable {
    private final double amount;                           // Amount borrowed by the borrower
    private final double rate;                             // Annual interest rate to determine amount of interest
    private final int length;                              // Length of the loan remaining in years
    private static final String type = "PureDiscountLoan"; // Type of the loan

    // REQUIRES: loanName has a non-zero length, loanAmount >= 0, 0 < interestRate < 1, loanLength >= 1
    // EFFECTS: name of the loan is set to loanName, amount of the loan is set to loanAmount,
    //          rate is set to interestRate, length is set to loanLength.
    public PureDiscountLoan(String loanName, double loanAmount, double interestRate, int loanLength) {
        super(loanName);
        amount = loanAmount;
        rate   = interestRate;
        length = loanLength;
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

    public String getType() {
        return type;
    }

    // EFFECTS: calculates the future value of the loan,
    public double calculateFV() {
        double fv = amount * Math.pow((1 + rate), length);
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(fv));
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject objJ = new JSONObject();
        objJ.put("type", type);
        objJ.put("name", this.getName());
        objJ.put("amount", amount);
        objJ.put("interest rate", rate);
        objJ.put("length of loan", length);
        return objJ;
    }
}

