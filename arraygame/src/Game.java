import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Game {

    public static void increment(int fromI, int incValue, List<Integer> list) {
        for (int i = fromI + 1; i < list.size(); i++) {

                list.set(i, list.get(i) + incValue );

        }
    }

    public static void printList(List<Integer> l) {
        System.out.println(
        l.stream().map(i->i.toString()).collect(Collectors.joining(","))
        );
    }
    public static int go(int[] arr) {

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            l.add(arr[i]);
        }
        Collections.sort(l);
        List<Integer> diff = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            diff.add(l.get(i) - l.get(i-1));
        }

        //Collections.reverse(l);

        printList(diff);
        int count = 0;
        int prev = l.get(0);
        for (int i = 0; i < diff.size() - 1; i++) {
            count += diff.get(i);

            diff.set(i + 1, diff.get(i + 1) + diff.get(i));
        }

        count += diff.get(diff.size()-1);

        return count;
    }

}
