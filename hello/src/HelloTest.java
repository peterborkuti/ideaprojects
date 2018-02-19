import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class HelloTest {
    @Test
    public void myMethodTest() {
        assertEquals("test input", "Test", Hello.myMethod("test"));
    }

}
