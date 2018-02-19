import org.junit.Test;

import static org.junit.Assert.*;

public class GridChallengeTest {
    @Test
    public void myMethodTest() {
        //assertEquals("test input", "Test", GridChallenge.myMethod("test"));
    }

    @Test
    public void columnIsSortedTest() {
        String[] test = {
                "abc",
                "def",
                "gki",
                "jhl"
        };
        assertTrue( GridChallenge.columnIsSorted(test,0));
        assertFalse( GridChallenge.columnIsSorted(test, 1));
        assertTrue( GridChallenge.columnIsSorted(test, 2));
    }

    @Test
    public void allColumnAreSortedTest() {
        String[] test = {
                "abc",
                "def",
                "gki",
                "jhl"
        };
        assertFalse( GridChallenge.allColumnAreSorted(test));
    }

    @Test
    public void sortTest() {
        String[] test = {
                "abc",
                "def",
                "gki",
                "jhl"
        };
        assertEquals( "abcdefghijkl",GridChallenge.sortGrid(test));

    }

    @Test
    public void stringToGridTest() {
        assertEquals(3, GridChallenge.stringToGrid("aaabbb", 3)[0].length());
    }

    @Test
    public void stringSortTest() {
        assertEquals("abc", GridChallenge.stringSort("cab"));
    }
}
