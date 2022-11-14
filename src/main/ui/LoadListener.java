package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action listner for loadButton
public class LoadListener implements ActionListener {
    private LoanApplication loanApplication;

    // EFFECTS: creates a loanListener
    public LoadListener(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    // EFFECTS: calls load loan list method in loanApplication
    @Override
    public void actionPerformed(ActionEvent e) {
        loanApplication.loadLoans();
    }
}
