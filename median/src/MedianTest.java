import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class MedianTest {
    private void balanceHelper(String lower, String upper) {
        Median m = new Median();
        Arrays.stream(lower.split(",")).forEach(s -> m.lower.add(Integer.parseInt(s)));
        Arrays.stream(upper.split(",")).forEach(s -> m.upper.add(Integer.parseInt(s)));
        m.lowerMax = Collections.max(m.lower);
        m.upperMin = Collections.min(m.upper);
        m.balance();
        assertEquals((lower.split(",").length + upper.split(",").length), m.lower.size() + m.upper.size());
        assertTrue(m.lowerMax <= m.upperMin);
        assertTrue(Math.abs(m.lower.size() - m.upper.size()) <= 1);
    }

    @Test
    public void balanceTest() {
        balanceHelper("1", "2");
        balanceHelper("1,2,3,4", "5,6,7,8");
        balanceHelper("1", "2,3,4,5,6,7,8");
    }

    private int medianHelper(String nums) {
        Median m = new Median();
        Arrays.stream(nums.split(",")).forEach(s -> m.put(Integer.parseInt(s)));
        return m.get();
    }

    @Test
    public void medianTest() {
        assertEquals(1, medianHelper("1"));
        assertEquals(4, medianHelper("1,2,3,4,5,6,7,8"));
        assertEquals(3, medianHelper("1,2,7,3,3,9,2"));
    }

}
