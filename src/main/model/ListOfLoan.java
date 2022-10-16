package model;

import java.util.ArrayList;

// Represents a list of different types of loans
public class ListOfLoan {
    private final ArrayList<Loan> listOfLoan;

    // EFFECTS: creates an empty list for different types of loans to be added
    public ListOfLoan() {
        listOfLoan = new ArrayList<>();
    }

    public ArrayList<Loan> getListOfLoan() {
        return listOfLoan;
    }
}
