package ui;

import model.AmortizedLoan;

import java.awt.event.ActionEvent;

// Action listener for add Amt loan button
public class AddAmtLoanListener extends AddLoanListener {

    // EFFECTS: creates an addamtLoan listener
    public AddAmtLoanListener(LoanApplication loanApplication) {
        super(loanApplication);
    }

    // MODIFIES: loans, listModel
    // EFFECTS: add an amt loan to the list of loans when add amt loan button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        name = loanApplication.loanName.getText();
        amount = Double.parseDouble(loanApplication.loanAmount.getText());
        rate = Double.parseDouble(loanApplication.interestRate.getText());
        double decimalrate = rate / 100.0;
        length = Integer.parseInt(loanApplication.loanLength.getText());
        AmortizedLoan amtLoan = new AmortizedLoan(super.name, super.amount, decimalrate, super.length);
        super.loanApplication.loans.addLoan(amtLoan);
        super.loanApplication.listModel.addElement(amtLoan);
        super.loanApplication.loanName.setText("");
        super.loanApplication.loanAmount.setText("");
        super.loanApplication.interestRate.setText("");
        super.loanApplication.loanLength.setText("");
    }
}
