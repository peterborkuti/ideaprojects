import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class GreedyMaxMinTest {
    @Test
    public void myMethodTest() {
        String test = "7 3 10 100 300 200 1000 20 30";
        assertEquals("test input",7 , GreedyMaxMin.angryChildren(3, test));
    }

    @Test
    public void sortArrayTest() {
        int[][]  test = new int[][]{{3,1},{2,2},{1,4}};
        int[][]  expected = new int[][]{{1,4},{2,2},{3,1}};
        assertArrayEquals("test input", expected , GreedyMaxMin.sortMatrix(test));
    }

}
