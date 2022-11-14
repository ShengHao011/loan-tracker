package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PureDiscountLoanTest {
    private PureDiscountLoan pdLoan;

    @BeforeEach
    void runBefore() {
        pdLoan =
                new PureDiscountLoan("BankLoan", 9070.29, 0.05, 2);
    }

    @Test
    void constructorTest() {
        assertEquals("BankLoan", pdLoan.getName());
        assertEquals(9070.29, pdLoan.getAmount());
        assertEquals(0.05, pdLoan.getInterestRate());
        assertEquals(2, pdLoan.getLength());
        assertEquals("PureDiscountLoan", pdLoan.getType());
    }

    @Test
    void calculateFVTest() {
        assertEquals(9999.99, pdLoan.calculateFV());
    }
}