package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of different types of loans
public class ListOfLoan implements Writable {
    private final ArrayList<Loan> listOfLoan;

    // EFFECTS: creates an empty list for different types of loans to be added
    public ListOfLoan() {
        listOfLoan = new ArrayList<>();
    }

    public ArrayList<Loan> getListOfLoan() {
        return listOfLoan;
    }

    public void addLoan(Loan loan) {
        listOfLoan.add(loan);
        EventLog.getInstance().logEvent(new Event("New Loan Added"));
    }

    public void removeLoan(int index) {
        listOfLoan.remove(index);
        EventLog.getInstance().logEvent(new Event("A Loan Was Removed"));
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject objJ = new JSONObject();
        objJ.put("List of Loan", listOfLoanToJson());
        return objJ;
    }

    private JSONArray listOfLoanToJson() {
        JSONArray arrayJ = new JSONArray();

        for (Loan l: listOfLoan) {
            arrayJ.put(l.convertToJson());
        }

        return arrayJ;
    }
}
