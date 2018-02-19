import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class GamingArray {
    public static List<Long> wipeList(List<Long> arr) {
        Long max = Collections.max(arr);
        int index = arr.indexOf(max);

        return arr.subList(0, index);
    }

    public static int numberOfWipes(List<Long> arr) {
        int counter = 0;
        while (!arr.isEmpty()) {
            arr = wipeList(arr);
            counter++;
        }

        return counter;
    }

    public static String myMethod(List<Long> input) {
        Long.M
        return (numberOfWipes(input) % 2 == 0) ? "ANDY" : "BOB";
    }

}
