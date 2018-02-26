import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.concurrent.*;

public class CountBinSubstr {
    public static int countOne(String input, String subString) {
        Pattern pattern = Pattern.compile(subString);
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        while (matcher.find())
            count++;

        return count;
    }

    public static int countAll(String input) {
        StringBuilder pattern1 = new StringBuilder(""); StringBuilder pattern2 = new StringBuilder("");
        int count = 0;

        for (int i = 0; i < input.length() / 2; i++) {
            pattern1.insert(0,'0');
            pattern1.append('1');
            pattern2.insert(0,'1');
            pattern2.append('0');

            count += countOne(input, pattern1.toString());
            count += countOne(input, pattern2.toString());
        }

        return count;
    }

    public static int countAll2(String input) {
        List<Integer> counters = new ArrayList<>();
        char prev = 'x';

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != prev) {
                counters.add(0);
                prev = input.charAt(i);
            }
            counters.set(counters.size() - 1, counters.get(counters.size() - 1) + 1);
        }

        int counter = 0;
        for (int i = 0; i < counters.size() - 1; i++) {
            counter += Math.min(counters.get(i), counters.get(i+1));
        }

        return counter;
    }

    public static int countAll3(String input) {
        List<Integer> counters = Arrays.stream(input.split("")).collect(Collectors.groupingBy(c->c, Collectors.counting();
        char prev = 'x';

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != prev) {
                counters.add(0);
                prev = input.charAt(i);
            }
            counters.set(counters.size() - 1, counters.get(counters.size() - 1) + 1);
        }

        int counter = 0;
        for (int i = 0; i < counters.size() - 1; i++) {
            counter += Math.min(counters.get(i), counters.get(i+1));
        }

        return counter;
    }

}
