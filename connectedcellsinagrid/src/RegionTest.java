import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class RegionTest {
    @Test
    public void myMethodTest() {
        int[][] matrix = new int[][]{{0,0,0},{1,0,0},{1,1,0},{0,0,0},{0,1,0},{1,0,1},{0,1,0},{1,1,1}};
        assertEquals("test input", 7, Region.connectedCell(matrix));
    }

}
