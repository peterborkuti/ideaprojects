import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TestReader {
    public final static Collector col = Collector.of(
            () -> new ArrayList<String>(),
            (result, line) -> {
                if (result.size() > 3)
                result.add()
                result[0] += article.getWordCount()
            },
            (result1, result2) -> {
                    result1[0] += result2[0];
                    return result1;
                },
            total -> total[0]
    );

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
