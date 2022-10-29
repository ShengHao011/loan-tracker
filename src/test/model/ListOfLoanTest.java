package model;

import model.ListOfLoan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfLoanTest {

    @Test
    void constructorTest() {
        ListOfLoan testList = new ListOfLoan();
        assertEquals(0, testList.getListOfLoan().size());
    }
}
