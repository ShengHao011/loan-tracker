package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action listener for saveButton
public class SaveListener implements ActionListener {
    private LoanApplication loanApplication;

    // creates a saveListener
    public SaveListener(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    // EFFECTS: calls save loan list method in loanApplication
    @Override
    public void actionPerformed(ActionEvent e) {
        loanApplication.saveLoans();
    }
}
