import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class RLTest {
    @Test
    public void myMethodTest() {

        RL rl = new RL(4, 4);
        for (int i = 0; i < 1000; i++) {
            rl.doOneStep(i);
        }
    }

}
