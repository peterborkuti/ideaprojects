import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class BFSTest {
    @Test
    public void simpleCases() {
        assertEquals(BFS.go("2:1:1 2"), "1 2");
        assertEquals(BFS.go(2,1,"1 2"), "1 2");
        assertEquals(BFS.go(2,1,new int[][]{{1,2}}), "1 2");
    }

    @Test
    public void testCases() {
        assertEquals("1 2 3", BFS.go("3:1:1 2,2 3"));
        assertEquals("1 2 3 4", BFS.go("4:1:1 2,2 3,3 4"));
        assertEquals("1 2 3 4", BFS.go("4:1:1 2,2 3,3 4,2 4"));
        assertEquals("3 2 4 1", BFS.go("4:3:1 2,2 3,3 4,2 4"));
    }

    @Test
    public void distanceTest() {
        assertEquals("0 1", BFS.distance("2:1:1 2"));
        assertEquals("0 1 2", BFS.distance("3:1:1 2,2 3"));
        assertEquals("0 1 2 3", BFS.distance("4:1:1 2,2 3,3 4"));
        assertEquals("0 1 2 2", BFS.distance("4:1:1 2,2 3,3 4,2 4"));
        assertEquals("0 1 2 2 -1", BFS.distance("5:1:1 2,2 3,3 4,2 4"));
        assertEquals("2 1 0 1", BFS.distance("4:3:1 2,2 3,3 4,2 4"));
        assertEquals("2 1 0 1 3", BFS.distance("5:3:1 2,2 3,3 4,2 4,1 5"));
        assertEquals("2 1 0 1 2", BFS.distance("5:3:1 2,2 3,3 4,2 4,1 5,4 5"));

    }


}