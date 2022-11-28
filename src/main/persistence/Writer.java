package persistence;

import model.Event;
import model.EventLog;
import model.ListOfLoan;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer to write JSON object to file
public class Writer {
    private static final int indentFactor = 5;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer with a destination file
    public Writer(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: starts a new print writer with a destination file, throws FileNotFoundException
    // if destination cannot be opened to write
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes the list of loans to file
    public void write(ListOfLoan lol) {
        JSONObject objJ = lol.convertToJson();
        saveToFile(objJ.toString(indentFactor));
        EventLog.getInstance().logEvent(new Event("List of loan saved to file"));
    }

    // MODFIES: this
    // EFFECTS: writes the string to the file
    private void saveToFile(String s) {
        writer.print(s);
    }

    // MODIFIES: this
    // EFFECTS: close down writer
    public void close() {
        writer.close();
    }
}
