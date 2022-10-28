package ui;

import model.AmortizedLoan;
import model.ListOfLoan;
import model.Loan;
import model.PureDiscountLoan;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

// Loan Tracker Application
public class LoanApp {
    private static final String destination = "./data/myFile.json";
    private Scanner input;
    private ListOfLoan listOfLoan;
    private Writer writer;
    private Reader reader;

    // EFFECTS: run the application
    public LoanApp() {
        writer = new Writer(destination);
        reader = new Reader(destination);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: execute user input
    private void runApp() {
        boolean state = true;
        String command = null;

        init();

        while (state) {
            showMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                state = false;
            } else {
                processInput(command);
            }
        }

        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs
    private void processInput(String userInput) {
        if (userInput.equals("a")) {
            showAddLoanMenu();
        } else if (userInput.equals("r")) {
            removeLoan();
        } else if (userInput.equals("d")) {
            displayLoanDetail();
        } else if (userInput.equals("v")) {
            showExistingLoans();
        } else if (userInput.equals("s")) {
            saveListOfLoan();
        } else if (userInput.equals("l")) {
            loadListOfLoan();
        } else {
            System.out.println("Selection does not exist!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize a list of loans and input
    private void init() {
        listOfLoan = new ListOfLoan();
        input = new Scanner(System.in);
    }

    // EFFECTS: Display the starting menu
    private void showMenu() {
        System.out.println("Select one option below:");
        System.out.println("a -> Add New Loan");
        System.out.println("r -> Remove A Loan");
        System.out.println("d -> View details of a loan");
        System.out.println("v -> View Existing Loans");
        System.out.println("s -> Save list of loan to file");
        System.out.println("l -> Load list of loan from file");
        System.out.println("q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Display menu for adding a new loan, prompts user to select the type of loan they want to add
    public void showAddLoanMenu() {
        String option = "";

        while (!(option.equals("p") || option.equals("a"))) {
            System.out.println("Select the type of loan:");
            System.out.println("p -> Pure Discount Loan");
            System.out.println("a -> Amortized Loan");
            option = input.next();
            option = option.toLowerCase();
        }

        if (option.equals("p")) {
            createPureDiscountLoan();
        } else {
            createAmortizedLoan();
        }
    }

    // MODIFIES: this,
    // EFFECTS: given user input values, create a Pure Discount Loan and add it to a list of loans
    public void createPureDiscountLoan() {
        System.out.println("Enter a name for this loan:");
        String name = input.next();

        System.out.println("Enter the loan amount:");
        double amount = input.nextDouble();

        System.out.println("Enter the interest rate:");
        double rate = input.nextDouble();

        System.out.println("Enter the length of the loan(years):");
        int length = input.nextInt();

        PureDiscountLoan pdLoan = new PureDiscountLoan(name, amount, rate, length);
        listOfLoan.getListOfLoan().add(pdLoan);
        System.out.println("Loan has been added to your list of loans!");
    }

    // MODIFIES: this,
    // EFFECTS: given user input values, create an Amortized Loan and add it to a list of loans
    public void createAmortizedLoan() {
        System.out.println("Enter a name for this loan:");
        String name = input.next();

        System.out.println("Enter the loan amount:");
        float amount = input.nextFloat();

        System.out.println("Enter the interest rate:");
        double rate = input.nextDouble();

        System.out.println("Enter the length of the loan(years):");
        int length = input.nextInt();

        AmortizedLoan amtLoan = new AmortizedLoan(name, amount, rate, length);
        listOfLoan.getListOfLoan().add(amtLoan);
        System.out.println("Loan has been added to your list of loans!");
    }

    // MODIFIES: this
    // EFFECTS: removes the loan in listOfLoan given the loan name
    public void removeLoan() {
        System.out.println("Enter the name of the loan to be removed");
        String removeName = input.next();
        for (int i = 0; i < listOfLoan.getListOfLoan().size(); i++) {
            if (removeName.equals((listOfLoan.getListOfLoan().get(i)).getName())) {
                listOfLoan.getListOfLoan().remove(i);
                System.out.println("Loan has been removed!");
            }
        }
    }

    // EFFECTS: Display details of a loan given its type
    public void displayLoanDetail() {
        System.out.println("Enter the name of the loan to view its details");
        String loanName = input.next();
        Loan selected = null;
        for (Loan l : listOfLoan.getListOfLoan()) {
            if (loanName.equals(l.getName())) {
                selected = l;
            }
        }
        if (selected instanceof PureDiscountLoan) {
            System.out.println(selected.getName());
            System.out.println("-----------------");
            System.out.println("Loan Amount: " + ((PureDiscountLoan) selected).getAmount());
            System.out.println("Interest Rate: " + ((PureDiscountLoan) selected).getInterestRate());
            System.out.println("Loan Length: " + ((PureDiscountLoan) selected).getLength());
            System.out.println("Future Value: " + ((PureDiscountLoan) selected).calculateFV());
        } else if (selected instanceof AmortizedLoan) {
            System.out.println(selected.getName());
            System.out.println("-----------------");
            System.out.println("Beginning Principle Amount: " + ((AmortizedLoan) selected).getPrincipalAmount());
            System.out.println("Interest Rate: " + ((AmortizedLoan) selected).getRate());
            System.out.println("Total Loan Length: " + ((AmortizedLoan) selected).getYearsRemaining());
            System.out.println("Yearly Annuities: " + ((AmortizedLoan) selected).getYearlyAnnuities());
        }
    }

    // EFFECTS: print out the existing list of loans
    public void showExistingLoans() {
        System.out.println("Your current list of loans:");

        for (Loan l : listOfLoan.getListOfLoan()) {
            System.out.println(l.getName());
        }
    }

    // EFFECTS: saves list of loan to file
    private void saveListOfLoan() {
        try {
            writer.open();
            writer.write(listOfLoan);
            writer.close();
            System.out.println("Saved list of loan to" + destination);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save list of loan");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list of loan from file
    private void loadListOfLoan() {
        try {
            listOfLoan = reader.read();
            System.out.println("Loaded list of loan from" + destination);
        } catch (IOException e) {
            System.out.printf("Failed to load list of loan");
        }
    }
}
