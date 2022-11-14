package ui;

import model.AmortizedLoan;
import model.Loan;
import model.PureDiscountLoan;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action listener for viewLoanDetails button
public class ViewLoanDetailsListener implements ActionListener {
    private LoanApplication loanApplication;
    private String filler = "";
    private Object [] columnNames;
    private Object [] row1Data;
    private Object [] loanInfoData;
    private Object [] emptyRow;
    private Object [] title;
    private Object [] scheduleColumnNames;
    private DefaultTableModel model;

    // EFFECTS: creates a viewLoanDetails Listener,
    public ViewLoanDetailsListener(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    // EFFECTS: create corresponding table to the corresponding type of loan
    @Override
    public void actionPerformed(ActionEvent e) {
        Loan selectedLoan = loanApplication.loanList.getSelectedValue();
        if (selectedLoan instanceof PureDiscountLoan) {
            createPDloanTable((PureDiscountLoan) selectedLoan);
        } else if (selectedLoan instanceof AmortizedLoan) {
            createAmortizationTable((AmortizedLoan) selectedLoan);
        }
    }

    // MODIFIES: detailsTable
    // EFFECTS: create a JTable for a pure discount loan
    public void createPDloanTable(PureDiscountLoan selectedLoan) {
        Object [] columnNames = {"Loan Amount:", "Effective Annual Rate:", "Loan Length:"};
        Object [] row1Data = {"Loan Amount:", "Effective Annual Rate:", "Loan Length:"};
        Object [] loanInfoData = {"$" + selectedLoan.getAmount(),
                selectedLoan.getInterestRate() * 100 + " %", selectedLoan.getLength() + " Years"};
        Object [] fvData = {"Future Value: " + "$" + selectedLoan.calculateFV()};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        model.addRow(row1Data);
        model.addRow(loanInfoData);
        model.addRow(fvData);
        loanApplication.detailsTable.setModel(model);
        loanApplication.detailsTable.setGridColor(Color.RED);
        loanApplication.addJTabletoPanel();
    }

    // MODIFIES: detailsTable
    // EFFECTS: create a JTable for an amortized loan
    public void createAmortizationTable(AmortizedLoan selectedLoan) {
        setUpRows(selectedLoan);
        double bb = selectedLoan.getPrincipalAmount();
        double yearlyPayment = selectedLoan.getYearlyAnnuities();
        double interest = selectedLoan.getRate() * bb;
        double principalPaid = yearlyPayment - interest;
        double eb = bb - principalPaid;

        double totalInterest = interest;
        for (int i = 1; i <= selectedLoan.getYearsRemaining(); i++) {
            Object [] row = new Object[]{i, bb, yearlyPayment, interest, principalPaid, eb};
            model.addRow(row);
            bb = eb;
            interest = selectedLoan.getRate() * bb;
            totalInterest = totalInterest + interest;
            principalPaid = yearlyPayment - interest;
            eb = bb - principalPaid;
        }
        double totalPayment = yearlyPayment * selectedLoan.getYearsRemaining();
        double totalPrinciplePaid = totalPayment - totalInterest;
        Object [] rowTotals = {"Totals:", filler, totalPayment, totalInterest, totalPrinciplePaid};
        model.addRow(rowTotals);

        loanApplication.detailsTable.setModel(model);
        loanApplication.detailsTable.setGridColor(Color.RED);
        loanApplication.addJTabletoPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up all required rows to be added for amortization table
    public void setUpRows(AmortizedLoan selectedLoan) {
        columnNames = new Object[]{filler, filler, filler, filler, filler, filler};
        row1Data = new Object[]{"Loan Amount:", "Effective Annual Rate:", "Loan Length:"};
        loanInfoData = new Object[]{"$" + selectedLoan.getPrincipalAmount(),
                selectedLoan.getRate() * 100 + " %", selectedLoan.getYearsRemaining() + " Years"};
        emptyRow = new Object[]{};
        title = new Object[]{"Amortization Schedule"};
        scheduleColumnNames = new Object[]{"Year", "Beginning Balance", "Total Payment",
                "Interest Paid", "Principle Paid", "Ending Balance"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        model.addRow(row1Data);
        model.addRow(loanInfoData);
        model.addRow(emptyRow);
        model.addRow(title);
        model.addRow(scheduleColumnNames);
    }
}
