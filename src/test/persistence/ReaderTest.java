package persistence;

import model.AmortizedLoan;
import model.ListOfLoan;
import model.PureDiscountLoan;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest extends JsonTest {

    @Test
    void readerNonExistentFileTest() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            ListOfLoan lol = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readerEmptylistofloanTest() {
        ListOfLoan lol = new ListOfLoan();
        Writer writer = new Writer("./data/readerEmptylistofloanTest.json");
        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail();
        }
        writer.write(lol);
        writer.close();

        Reader reader = new Reader("./data/readerEmptylistofloanTest.json");
        try {
            ListOfLoan rlol = reader.read();
            assertEquals(0, rlol.getListOfLoan().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void readerGenerallistofloanTest() {
        ListOfLoan lol = new ListOfLoan();
        lol.getListOfLoan()
                .add(new PureDiscountLoan("BankLoan", 9070.29, 0.05, 2));
        lol.getListOfLoan().add(new AmortizedLoan("TestLoan", 5000, 0.08, 4));
        Writer writer = new Writer("./data/readerGenerallistofloanTest.json");

        try {
            writer.open();
        } catch (FileNotFoundException e) {
            fail();
        }
        writer.write(lol);
        writer.close();

        Reader reader = new Reader("./data/readerGenerallistofloanTest.json");
        try {
            ListOfLoan rlol = reader.read();
            assertEquals(2, rlol.getListOfLoan().size());
            checkPDLoan("BankLoan", 9070.29, 0.05, 2,
                    (PureDiscountLoan) rlol.getListOfLoan().get(0));
            checkAmtLoan("TestLoan", 5000, 0.08, 4,
                    (AmortizedLoan) rlol.getListOfLoan().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
