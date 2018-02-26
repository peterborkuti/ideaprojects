import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class CommonChildDPTest {
    @Test
    public void myMethodTest() {
        assertEquals("test input", 0, CommonChildDP.LCS("", ""));
        assertEquals("test input", 0, CommonChildDP.LCS("X", ""));
        assertEquals("test input", 0, CommonChildDP.LCS("", "Y"));
        assertEquals("test input", 0, CommonChildDP.LCS("X", "Y"));
        assertEquals("test input", 1, CommonChildDP.LCS("X", "X"));
        assertEquals("test input", 2, CommonChildDP.LCS("XX", "XX"));
        assertEquals("test input", 2, CommonChildDP.LCS("XXYY", "YY"));
        assertEquals("test input", 2, CommonChildDP.LCS("XXX", "XX"));
        assertEquals("test input", 4, CommonChildDP.LCS("XXYYXX", "XXXX"));
        assertEquals("test input", 3, CommonChildDP.LCS("SHINCHAN", "NOHARAAA"));

    }
    
    private int lcs2(String s1, String s2) {
        LCS2 lcs = new LCS2(s1, s2);
        return lcs.LCS();
    }
    
    @Test
    public void LCS2Test() {
        assertEquals("test input", 0, lcs2("", ""));
        assertEquals("test input", 0, lcs2("X", ""));
        assertEquals("test input", 0, lcs2("", "Y"));
        assertEquals("test input", 0, lcs2("X", "Y"));
        assertEquals("test input", 1, lcs2("X", "X"));
        assertEquals("test input", 2, lcs2("XX", "XX"));
        assertEquals("test input", 2, lcs2("XXYY", "YY"));
        assertEquals("test input", 2, lcs2("XXX", "XX"));
        assertEquals("test input", 4, lcs2("XXYYXX", "XXXX"));
        assertEquals("test input", 3, lcs2("SHINCHAN", "NOHARAAA"));

    }

}
