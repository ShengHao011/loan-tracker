package test;

import model.AmortizedLoan;
import model.ListOfLoan;
import model.PureDiscountLoan;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;
import test.JsonTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest extends JsonTest {

    @Test
    void writerInvalidFileTest() {
        try {
            ListOfLoan lol = new ListOfLoan();
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writerEmptylistofloanTest() {
        try {
            ListOfLoan lol = new ListOfLoan();
            Writer writer = new Writer("./data/writerEmptylistofloanTest.json");
            writer.open();
            writer.write(lol);
            writer.close();

            Reader reader = new Reader("./data/writerEmptylistofloanTest.json");
            lol = reader.read();
            assertEquals(0, lol.getListOfLoan().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void writerGenerallistofloanTest() {
        try {
            ListOfLoan lol = new ListOfLoan();
            lol.getListOfLoan()
                    .add(new PureDiscountLoan("BankLoan", 9070.29, 0.05, 2));
            lol.getListOfLoan()
                    .add(new AmortizedLoan("TestLoan", 5000, 0.08, 4));
            Writer writer = new Writer("./data/writerGenerallistofloanTest.json");
            writer.open();
            writer.write(lol);
            writer.close();

            Reader reader = new Reader("./data/writerGenerallistofloanTest.json");
            lol = reader.read();
            assertEquals(2, lol.getListOfLoan().size());
            checkPDLoan("BankLoan", 9070.29, 0.05, 2,
                    (PureDiscountLoan) lol.getListOfLoan().get(0));
            checkAmtLoan("TestLoan", 5000, 0.08, 4,
                    (AmortizedLoan) lol.getListOfLoan().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
