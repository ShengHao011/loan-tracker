package ui;

import java.awt.event.ActionListener;

// Action Listener for add loan buttons
public abstract class AddLoanListener implements ActionListener {
    protected LoanApplication loanApplication;
    protected String name;
    protected double amount;
    protected double rate;
    protected int length;

    // EFFECTS: creates an AddLoanListener
    public AddLoanListener(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
