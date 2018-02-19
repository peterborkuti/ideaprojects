import org.junit.Test;

import static org.junit.Assert.*;

public class CheckTest {
    @Test
    public void inLineTest() {
        assertTrue( Check.inLine(0,0 , 4, -2, 10, -5));
        assertTrue( Check.inLine(1,2 , 4, 3, 7, 4));
        assertTrue( Check.inLine(1,2 , 4, 3, 10, 5));
        assertFalse( Check.inLine(1,2 , 4, 3, 10, 4));
        assertFalse( Check.inLine(0,3 , 1, 1, 2, 6));
    }

}
