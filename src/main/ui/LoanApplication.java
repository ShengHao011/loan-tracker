package ui;

import model.ListOfLoan;
import model.Loan;

import javax.swing.*;
import java.awt.*;

// Loan Tracker Application
public class LoanApplication extends JPanel {
    protected ListOfLoan loans;
    protected JList<Loan> loanList;
    protected DefaultListModel<Loan> listModel;

    private AddLoanListener addPDLoanListener;
    private AddLoanListener addAmtLoanListener;
    private RemoveLoanListener removeLoanListener;

    private JPanel addLoanPanel;
    private JPanel saveloadPanel;
    private JScrollPane loanListPane;

    private JButton addPDloanButton;
    private JButton addAmtloanButton;
    private JButton removeLoanButton;
    private JLabel loanNameLabel;
    private JLabel loanAmountLabel;
    private JLabel interestRateLabel;
    private JLabel loanLengthLabel;
    protected JTextField loanName;
    protected JTextField loanAmount;
    protected JTextField interestRate;
    protected JTextField loanLength;

    // EFFECTS: constucts a loan tracker application GUI
    public LoanApplication() {
        super(new BorderLayout());
        loans = new ListOfLoan();
        listModel = new DefaultListModel<>();

        createJlist();
        loanListPane = new JScrollPane(loanList);
        loanListPane.setPreferredSize(new Dimension(200, 800));

        createAddPDloanButton();
        createaddAmtloanButton();
        createremoveLoanButton();
        createLabels();
        createTextFIelds();
        createaddLoanPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Loan Tracker Application");
        frame.setPreferredSize(new Dimension(1000, 800));
        add(addLoanPanel);
        add(loanListPane, BorderLayout.LINE_END);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates loan list
    public void createJlist() {
        loanList = new JList<>();
        loanList.setModel(listModel);
        // defaultList.addElement(new AmortizedLoan("TestLoan", 5000, 0.08, 4));
        loanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: this
    // EFFECTS: creates addPDloanButton
    public void createAddPDloanButton() {
        addPDloanButton = new JButton("Add Pure Discount Loan");
        addPDloanButton.setBounds(130, 100, 200, 25);
        addPDLoanListener = new AddPDLoanListener(this);
        addPDloanButton.addActionListener(addPDLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates addAmtloanButton
    public void createaddAmtloanButton() {
        addAmtloanButton = new JButton("Add Amortized Loan");
        addAmtloanButton.setBounds(490, 100,200, 25);
        addAmtLoanListener = new AddAmtLoanListener(this);
        addAmtloanButton.addActionListener(addAmtLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates removeLoanButton
    public void createremoveLoanButton() {
        removeLoanButton = new JButton("Delete Selected Loan");
        removeLoanButton.setBounds(310, 140, 200, 25);
        removeLoanListener = new RemoveLoanListener(this);
        removeLoanButton.addActionListener(removeLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates all labels
    public void createLabels() {
        loanNameLabel = new JLabel("Name");
        loanNameLabel.setBounds(10, 20, 100, 25);
        loanAmountLabel = new JLabel("Loan Amount");
        loanAmountLabel.setBounds(210, 20, 100, 25);
        interestRateLabel = new JLabel("Interest Rate (%)");
        interestRateLabel.setBounds(410, 20, 100, 25);
        loanLengthLabel = new JLabel("Length (years)");
        loanLengthLabel.setBounds(610, 20, 100, 25);
    }

    // MODIFIES: this
    // EFFECTS: creates all text fields
    public void createTextFIelds() {
        loanName = new JTextField(20);
        loanName.setBounds(10, 50, 140, 25);
        loanAmount = new JTextField(20);
        loanAmount.setBounds(210, 50, 140, 25);
        interestRate = new JTextField(20);
        interestRate.setBounds(410, 50, 140, 25);
        loanLength = new JTextField(20);
        loanLength.setBounds(610, 50, 140, 25);
    }

    // REQUIRES: all required JLabels, text fields, and buttons are created
    // MODIFIES: this
    // EFFECTS: creates add loan panel
    public void createaddLoanPanel() {
        addLoanPanel = new JPanel();
        addLoanPanel.setLayout(null);
        addLoanPanel.add(loanNameLabel);
        addLoanPanel.add(loanAmountLabel);
        addLoanPanel.add(interestRateLabel);
        addLoanPanel.add(loanLengthLabel);
        addLoanPanel.add(loanName);
        addLoanPanel.add(loanAmount);
        addLoanPanel.add(interestRate);
        addLoanPanel.add(loanLength);
        addLoanPanel.add(addPDloanButton);
        addLoanPanel.add(addAmtloanButton);
        addLoanPanel.add(removeLoanButton);
    }
}
