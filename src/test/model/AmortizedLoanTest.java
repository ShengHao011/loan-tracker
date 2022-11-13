package model;

import model.AmortizedLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AmortizedLoanTest {
    private AmortizedLoan amtLoan;

    @BeforeEach
    void runBefore() {
        amtLoan = new AmortizedLoan("TestLoan", 5000, 0.08, 4);
    }

    @Test
    void constructorTest() {
        assertEquals("TestLoan", amtLoan.getName());
        assertEquals(5000, amtLoan.getPrincipalAmount());
        assertEquals(0.08, amtLoan.getRate());
        assertEquals(4, amtLoan.getYearsRemaining());
        assertEquals(1509.60, amtLoan.getYearlyAnnuities());
        assertEquals("AmortizedLoan", amtLoan.getType());
    }

    @Test
    void calculateInterestPaidTest() {
        assertEquals(400.00, amtLoan.calculateInterestPaid());
    }

    @Test
    void deductYearsRemainingTest() {
        amtLoan.deductYearsRemaining();
        assertEquals(3, amtLoan.getYearsRemaining());
        AmortizedLoan oneYearlengthLoan =
                new AmortizedLoan("TestLoan", 5000, 0.08, 1);
        oneYearlengthLoan.deductYearsRemaining();
        assertEquals(0, oneYearlengthLoan.getYearsRemaining());
        oneYearlengthLoan.deductYearsRemaining();
        assertEquals(0, oneYearlengthLoan.getYearsRemaining());
    }

    @Test
    void deductPrincipleAmountTest() {
        amtLoan.deductPrincipleAmount();
        assertEquals(3890.40, amtLoan.getPrincipalAmount());
    }

}
