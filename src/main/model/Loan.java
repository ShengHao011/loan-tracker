package model;

// Represents the general loan type with a name
public abstract class Loan {
    protected final String name; // The name of this loan

    public Loan(String loanName) {
        name = loanName;
    }

    public String getName() {
        return name;
    }
}
