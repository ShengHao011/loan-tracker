package persistence;

import model.AmortizedLoan;
import model.ListOfLoan;
import model.PureDiscountLoan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader to read data from the file
public class Reader {
    private String source;

    // EFFECTS: constructs a reader to read data from file
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads and returns the list of loan from file, throws IOException
    // if error occurs in reading the file or a wrong path is present
    public ListOfLoan read() throws IOException {
        String data = readFile(source);
        JSONObject objJ = new JSONObject(data);
        return createLoL(objJ);
    }

    // EFFECTS: reads sources from file and returns as a string
    public String readFile(String source) throws IOException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source))) {
            stream.forEach(s -> content.append(s));
        }

        return content.toString();
    }

    // EFFECTS: creates list of loan from JSON Object and returns it
    private ListOfLoan createLoL(JSONObject objJ) {
        ListOfLoan lol = new ListOfLoan();
        addLoans(lol, objJ);
        return lol;
    }

    // MODIFIES: lol
    // EFFECTS: creates loans from JSON Object and adds them to the list of loan
    private void addLoans(ListOfLoan lol, JSONObject objJ) {
        JSONArray arrayJ = objJ.getJSONArray("List of Loan");
        for (Object o : arrayJ) {
            JSONObject nextLoan = (JSONObject) o;
            addLoan(lol, nextLoan);
        }
    }

    // MODIFIES: lol
    // EFFECTS: creates loan form JSON Object and adds it to the list of loan
    private void addLoan(ListOfLoan lol, JSONObject objJ) {
        String name = objJ.getString("name");
        double amount = objJ.getDouble("amount");
        double ir = objJ.getDouble("interest rate");
        int length = objJ.getInt("length of loan");
        if (objJ.get("type").equals("PureDiscountLoan")) {
            PureDiscountLoan loan = new PureDiscountLoan(name, amount, ir, length);
            lol.getListOfLoan().add(loan);
        } else {
            AmortizedLoan loan = new AmortizedLoan(name, amount, ir, length);
            lol.getListOfLoan().add(loan);
        }
    }

}
