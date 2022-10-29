package test;

import model.AmortizedLoan;
import model.PureDiscountLoan;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPDLoan(String name, double amount, double ir, int length, PureDiscountLoan l) {
        assertEquals(name, l.getName());
        assertEquals(amount, l.getAmount());
        assertEquals(ir, l.getInterestRate());
        assertEquals(length, l.getLength());
    }

    protected void checkAmtLoan(String name, double amount, double ir, int length, AmortizedLoan l) {
        assertEquals(name, l.getName());
        assertEquals(amount, l.getPrincipalAmount());
        assertEquals(ir, l.getRate());
        assertEquals(length, l.getYearsRemaining());
    }
}
