import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordTest {
    @Test
    public void isMainCornerTest() {
        assertTrue(new Coord(0,0).isMainCorner(10,10));
        assertTrue(new Coord(9,9).isMainCorner(10,10));
        assertFalse(new Coord(0,9).isMainCorner(10,10));
    }

    @Test
    public void instantiationWithIndexTest() {
        assertEquals("(0,0)",new Coord(0,10, 10).toString());
        assertEquals("(1,0)",new Coord(4,4, 4).toString());
        assertEquals("(1,3)",new Coord(7,4, 4).toString());
        assertEquals("(3,3)",new Coord(15,4, 4).toString());

    }

    @Test
    public void isInTest() {
        assertTrue(new Coord(0,0).isIn(10,10));
        assertFalse(new Coord(9,9).isIn(5,5));
        assertTrue(new Coord(9,9).isIn(10,10));
        assertFalse(new Coord(-1,-1).isIn(5,5));
    }

    private static <T> String listToString(List<T> l) {
        return l.stream().map(i -> i.toString()).collect(Collectors.joining());
    }

    @Test
    public void neighboursTest() {
        assertEquals("(0,1)(2,1)(1,0)(1,2)", listToString(new Coord(1,1).neighbours(10,10)));
        assertEquals("(0,0)(1,0)(0,0)(0,1)", listToString(new Coord(0,0).neighbours(10,10)));
    }
}
