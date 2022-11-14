package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action listener for remove loan button
public class RemoveLoanListener implements ActionListener {
    private LoanApplication loanApplication;

    // EFFECTS: creates a RemoveLoanListener
    public RemoveLoanListener(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    // MODIFIES: loans, listModel
    // EFFECTS: remove the selected loan from the list
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = loanApplication.loanList.getSelectedIndex();
        loanApplication.listModel.remove(index);
        loanApplication.loans.getListOfLoan().remove(index);
    }
}
