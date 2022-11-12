package model;

import org.json.JSONObject;

// Represents the general loan type with a name
public abstract class Loan {
    protected final String name; // The name of this loan

    public Loan(String loanName) {
        name = loanName;
    }

    public String getName() {
        return name;
    }

    public abstract JSONObject convertToJson();

    @Override
    public String toString() {
        return name;
    }
}
