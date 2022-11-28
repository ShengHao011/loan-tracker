package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfLoanTest {

    @Test
    void constructorTest() {
        ListOfLoan testList = new ListOfLoan();
        assertEquals(0, testList.getListOfLoan().size());
    }

    @Test
    void addLoanTest() {
        ListOfLoan testList = new ListOfLoan();
        PureDiscountLoan pdLoan =
                new PureDiscountLoan("BankLoan", 9070.29, 0.05, 2);
        AmortizedLoan amtLoan =
                new AmortizedLoan("TestLoan", 5000, 0.08, 4);
        testList.addLoan(pdLoan);
        testList.addLoan(amtLoan);
        assertEquals(2, testList.getListOfLoan().size());
        assertEquals(pdLoan, testList.getListOfLoan().get(0));
    }

    @Test
    void removeLoanTest() {
        ListOfLoan testList = new ListOfLoan();
        PureDiscountLoan pdLoan =
                new PureDiscountLoan("BankLoan", 9070.29, 0.05, 2);
        AmortizedLoan amtLoan =
                new AmortizedLoan("TestLoan", 5000, 0.08, 4);
        testList.addLoan(pdLoan);
        testList.addLoan(amtLoan);
        testList.removeLoan(1);
        assertEquals(1, testList.getListOfLoan().size());
        assertFalse(testList.getListOfLoan().contains(amtLoan));
    }
}
