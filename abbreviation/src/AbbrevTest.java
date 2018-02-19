import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class AbbrevTest {
    @Test
    public void abbrevTest() {
        assertFalse(Abbrev.abbrev("", "X"));
        assertFalse(Abbrev.abbrev("X", "Y"));
        assertTrue(Abbrev.abbrev("X", "X"));
        assertTrue(Abbrev.abbrev("x", "X"));
        assertTrue(Abbrev.abbrev("xxABxx", "AB"));
        assertTrue(Abbrev.abbrev("xxABxxc", "ABC"));
        assertTrue(Abbrev.abbrev("xxabxxC", "ABC"));
        assertFalse(Abbrev.abbrev("xxaxbxxC", "ABC"));


        //assertFalse(Abbrev.abbrev("ABC", "XY"));
        //assertFalse(Abbrev.abbrev("ABCX", "XY"));
        //assertFalse(Abbrev.abbrev("ABCXY", "XY"));
        //assertFalse(Abbrev.abbrev("ABCXsdfYcvb", "XY"));
    }

}
