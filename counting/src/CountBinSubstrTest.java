import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class CountBinSubstrTest {
    @Test
    public void countOneTest() {
        assertEquals("test input", 1, CountBinSubstr.countOne("01", "01"));
        assertEquals("test input", 2, CountBinSubstr.countOne("0101", "01"));
        assertEquals("test input", 2, CountBinSubstr.countOne("001011", "01"));
        assertEquals("test input", 1, CountBinSubstr.countOne("00110", "0011"));
        assertEquals("test input", 1, CountBinSubstr.countOne("00110", "01"));
        assertEquals("test input", 1, CountBinSubstr.countOne("00110", "10"));
    }

    @Test
    public void countAllTest() {
        assertEquals("test input", 3, CountBinSubstr.countAll("00110"));
        assertEquals("test input", 4, CountBinSubstr.countAll("10101"));
        assertEquals("test input", 2, CountBinSubstr.countAll("10001"));
    }

    @Test
    public void countAll2Test() {
        assertEquals("test input", 3, CountBinSubstr.countAll2("00110"));
        assertEquals("test input", 4, CountBinSubstr.countAll2("10101"));
        assertEquals("test input", 2, CountBinSubstr.countAll2("10001"));
    }
}

