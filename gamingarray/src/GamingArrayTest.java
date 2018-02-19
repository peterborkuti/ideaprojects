import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GamingArrayTest {
    @Test
    public void wipeListTest() {
        List<Long> test = Arrays.asList(new Long[]{0l, 1l, 2l, 1l, 0l});
        List<Long> expected = Arrays.asList(new Long[]{0l, 1l});
        assertEquals("test input", expected, GamingArray.wipeList(test));
    }

    @Test
    public void numberOfWipesTest() {
        List<Long> test = Arrays.asList(new Long[]{0l, 1l, 2l, 1l, 0l});
        assertEquals("test input", 3l, (long)GamingArray.numberOfWipes(test));
    }

}
