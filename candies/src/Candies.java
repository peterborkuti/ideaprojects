import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Candies {
    public static String candies(String input)
    {
        List<Integer> ratings = new ArrayList<>();

        ratings.add(0);
        ratings.addAll(Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        ratings.add(ratings.get(ratings.size() - 1));
        ratings.set(0, ratings.get(1));

        List<Integer> candies = new ArrayList<>();
        candies.addAll(Collections.nCopies(ratings.size(), 1));

        boolean modified = true;
        while (modified) {
            modified = false;
            for (int i = 1; i < ratings.size() - 1; i++) {
                int leftRate = ratings.get( i - 1);
                int rightRate = ratings.get( i + 1);
                int rate = ratings.get(i);

                int leftCandies = candies.get( i - 1);
                int rightCandies = candies.get( i + 1);
                int candy = candies.get(i);

                if ((rate > leftRate) && (candy <= leftCandies)) {
                    candies.set(i, candy + 1);
                    modified = true;
                    continue;
                }
                if ((rate > rightRate) && (candy <= rightCandies)) {
                    candies.set(i, candy + 1);
                    modified = true;
                    continue;
                }
            }
        }
        return candies.subList(1, candies.size() - 1).stream().map(i -> Integer.toString(i)).collect(Collectors.joining(","));
    }

}
