package ui;

import model.ListOfLoan;
import model.Loan;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Loan Tracker Application
public class LoanApplication {
    protected ListOfLoan loans;
    protected JList<Loan> loanList;
    protected DefaultListModel<Loan> listModel;

    private AddLoanListener addPDLoanListener;
    private AddLoanListener addAmtLoanListener;
    private RemoveLoanListener removeLoanListener;
    private ViewLoanDetailsListener viewLoanDetailsListener;
    private SaveListener saveListener;
    private LoadListener loadListener;

    private JFrame frame;

    private JPanel loanUIpanel;
    private JPanel loanDetailsPanel;
    private JPanel saveloadPanel;
    private JScrollPane loanListPane;

    protected JTable detailsTable;

    private JButton addPDloanButton;
    private JButton addAmtloanButton;
    private JButton removeLoanButton;
    private JButton viewLoanDetailsButton;
    private JLabel loanNameLabel;
    private JLabel loanAmountLabel;
    private JLabel interestRateLabel;
    private JLabel loanLengthLabel;
    protected JTextField loanName;
    protected JTextField loanAmount;
    protected JTextField interestRate;
    protected JTextField loanLength;

    private JButton saveButton;
    private JButton loadButton;

    private Writer writer;
    private Reader reader;
    private static final String destination = "./data/myFile.json";

    // EFFECTS: constucts a loan tracker application GUI
    public LoanApplication() {
        loans = new ListOfLoan();
        listModel = new DefaultListModel<>();
        detailsTable = new JTable();

        writer = new Writer(destination);
        reader = new Reader(destination);

        createJlist();
        loanListPane = new JScrollPane(loanList);
        loanListPane.setPreferredSize(new Dimension(200, 800));

        createaddPDloanButton();
        createaddAmtloanButton();
        createremoveLoanButton();
        createviewLoanDetailsButton();
        createLabels();
        createTextFIelds();
        createsaveButton();
        createloanButton();
        createloanUIpanel();
        createloanDetailsPanel();
        createsaveloadPanel();

        setUpFrame();
    }

    // MODIFIES: this
    // EFFECTS: sets up frame and add all panels
    public void setUpFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Loan Tracker Application");
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.setLayout(new BorderLayout());
        frame.add(loanUIpanel, BorderLayout.PAGE_START);
        frame.add(loanDetailsPanel, BorderLayout.CENTER);
        frame.add(loanListPane, BorderLayout.LINE_END);
        frame.add(saveloadPanel, BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates loan list
    public void createJlist() {
        loanList = new JList<>();
        loanList.setModel(listModel);
        loanList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: this
    // EFFECTS: creates addPDloanButton
    public void createaddPDloanButton() {
        addPDloanButton = new JButton("Add Pure Discount Loan");
        addPDLoanListener = new AddPDLoanListener(this);
        addPDloanButton.addActionListener(addPDLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates addAmtloanButton
    public void createaddAmtloanButton() {
        addAmtloanButton = new JButton("Add Amortized Loan");
        addAmtLoanListener = new AddAmtLoanListener(this);
        addAmtloanButton.addActionListener(addAmtLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates removeLoanButton
    public void createremoveLoanButton() {
        removeLoanButton = new JButton("Delete Selected Loan");
        removeLoanListener = new RemoveLoanListener(this);
        removeLoanButton.addActionListener(removeLoanListener);
    }

    // MODIFIES: this
    // EFFECTS: creates a viewLoanDetailsButton
    public void createviewLoanDetailsButton() {
        viewLoanDetailsButton = new JButton("View Selected Loan Details");
        viewLoanDetailsListener = new ViewLoanDetailsListener(this);
        viewLoanDetailsButton.addActionListener(viewLoanDetailsListener);
    }

    // MODIFIES: this
    // EFFECTS: creates all labels
    public void createLabels() {
        loanNameLabel = new JLabel("Loan Name");
        loanAmountLabel = new JLabel("Loan Amount");
        interestRateLabel = new JLabel("Effective Annual Rate (%)");
        loanLengthLabel = new JLabel("Length (years)");
    }

    // MODIFIES: this
    // EFFECTS: creates all text fields
    public void createTextFIelds() {
        loanName = new JTextField(20);
        loanAmount = new JTextField(20);
        interestRate = new JTextField(20);
        loanLength = new JTextField(20);
    }

    // MODIFIES: this
    // EFFECTS: creates a saveButton
    public void createsaveButton() {
        saveButton = new JButton("Save");
        saveListener = new SaveListener(this);
        saveButton.addActionListener(saveListener);
    }

    // MODIFIES: this
    // EFFECTS: creates a loadButton
    public void createloanButton() {
        loadButton = new JButton("Load");
        loadListener = new LoadListener(this);
        loadButton.addActionListener(loadListener);
    }


    // REQUIRES: all required JLabels, text fields, and buttons are created
    // MODIFIES: this
    // EFFECTS: creates add loan panel
    public void createloanUIpanel() {
        loanUIpanel = new JPanel();
        loanUIpanel.setLayout(new GridLayout(0, 4, 1, 1));
        loanUIpanel.add(loanNameLabel);
        loanUIpanel.add(loanAmountLabel);
        loanUIpanel.add(interestRateLabel);
        loanUIpanel.add(loanLengthLabel);
        loanUIpanel.add(loanName);
        loanUIpanel.add(loanAmount);
        loanUIpanel.add(interestRate);
        loanUIpanel.add(loanLength);
        loanUIpanel.add(addPDloanButton);
        loanUIpanel.add(addAmtloanButton);
        loanUIpanel.add(removeLoanButton);
        loanUIpanel.add(viewLoanDetailsButton);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel to display loan details
    public void createloanDetailsPanel() {
        loanDetailsPanel = new JPanel();
        loanDetailsPanel.setLayout(new BorderLayout());
        loanDetailsPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    // MODIFIES: this
    // EFFECTS: creates a save load panel
    public void createsaveloadPanel() {
        saveloadPanel = new JPanel();
        saveloadPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        saveloadPanel.setLayout(new FlowLayout());
        saveloadPanel.add(saveButton);
        saveloadPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: adds the newly constructed JTabel to panel to display loan details
    public void addJTabletoPanel() {
        loanDetailsPanel.add(detailsTable, BorderLayout.CENTER);
        frame.repaint();
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: saves loan list to destination file
    public void saveLoans() {
        try {
            writer.open();
            writer.write(loans);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save list of loan");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads loan list from file
    public void loadLoans() {
        try {
            ListOfLoan listOfLoan = reader.read();
            for (Loan loan : listOfLoan.getListOfLoan()) {
                listModel.addElement(loan);
            }
        } catch (IOException e) {
            System.out.println("Failed to load list of loan");
        }
    }
}
