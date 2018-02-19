import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SpiesTest {
    @Test
    public void climbOneTest() {

        assertEquals("test input", "Test", Spies.hillClimbing("2 4 7 1 8 11 5 3 9 6 10"));
    }

}
