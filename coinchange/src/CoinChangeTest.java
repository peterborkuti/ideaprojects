import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class CoinChangeTest {
    //@Test
    public void simpleTest() {
        assertEquals(0, CoinChange.getWays(0, new long[0]));
        assertEquals(0, CoinChange.getWays(1, new long[0]));
        assertEquals(0, CoinChange.getWays(0, new long[]{1}));

        assertEquals(0, CoinChange.getWays(2, new long[]{3}));
        assertEquals(1, CoinChange.getWays(4, new long[]{2}));
        assertEquals(0, CoinChange.getWays(5, new long[]{2}));
    }

    @Test
    public void oneCoinTest() {
        //assertEquals(10, CoinChange.getWays(10, new long[]{1, 2, 5}));
        assertEquals(4, CoinChange.getWays(5, new long[]{1, 2, 5}));
    }

}
