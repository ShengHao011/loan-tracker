package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    @Test
    void toStringTest() {
        PureDiscountLoan pdLoan = new PureDiscountLoan("test1", 1, 1, 1);
        AmortizedLoan amtLoan = new AmortizedLoan("test2", 1, 1, 1);
        assertEquals("test1", pdLoan.toString());
        assertEquals("test2", amtLoan.toString());
    }
}
