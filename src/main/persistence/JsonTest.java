package persistence;

import model.Loan;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkLoan(String name, Loan l) {
        assertEquals(name, l.getName());
    }
}
