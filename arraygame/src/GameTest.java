import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class GameTest {
    @Test
    public void myMethodTest() {
        assertEquals("test input", 7, Game.go(new int[]{5,6,8,8,5}));
    }

}
