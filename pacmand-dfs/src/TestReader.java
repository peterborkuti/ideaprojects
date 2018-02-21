import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestReader {
    public final List<TestData> data = new ArrayList<>();

    public static void read(String fileName) {
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            stream.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
