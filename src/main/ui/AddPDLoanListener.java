package ui;

import model.PureDiscountLoan;

import java.awt.event.ActionEvent;

// Action listener for add PD loan button
public class AddPDLoanListener extends AddLoanListener {

    // EFFECTS: creates an addpdLoan listener
    public AddPDLoanListener(LoanApplication loanApplication) {
        super(loanApplication);
    }

    // MODIFIES: loans, listModel
    // EFFECTS: add a pd loan to the list of loans when add pd loan button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        name = loanApplication.loanName.getText();
        amount = Double.parseDouble(loanApplication.loanAmount.getText());
        rate = Double.parseDouble(loanApplication.interestRate.getText());
        double decimalrate = rate / 100.0;
        length = Integer.parseInt(loanApplication.interestRate.getText());
        PureDiscountLoan pdLoan = new PureDiscountLoan(super.name, super.amount, decimalrate, super.length);
        super.loanApplication.loans.getListOfLoan().add(pdLoan);
        super.loanApplication.listModel.addElement(pdLoan);
        super.loanApplication.loanName.setText("");
        super.loanApplication.loanAmount.setText("");
        super.loanApplication.interestRate.setText("");
        super.loanApplication.loanLength.setText("");
    }
}
