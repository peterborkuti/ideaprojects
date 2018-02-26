import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class CandiesTest {
    @Test
    public void candiesTest() {
        assertEquals("test input", "1", Candies.candies("1"));
        assertEquals("test input", "1,2", Candies.candies("1,2"));
        assertEquals("test input", "1,2,1", Candies.candies("1,2,2"));
        assertEquals("test input", "1,2,1,2,1,2,3,4,2,1", Candies.candies("2,4,2,6,1,7,8,9,2,1"));
    }

}
