import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Cache {
    Map<String, Long> ways  = new HashMap<>();

    private String getKey(long amount, int coinPos) {
        return "" + amount + ":" + coinPos;
    }

    public void put(long amount, int coinPos, long ways) {
        this.ways.put(getKey(amount, coinPos), ways);
    }

    public boolean containsKey(long amount, int coinPos) {
        return ways.containsKey(getKey(amount, coinPos));
    }

    public Long get(long amount, int coinPos) {
        System.out.println("Retrieved:" + getKey(amount, coinPos) + " -> " + ways.get(getKey(amount, coinPos)));
        return ways.get(getKey(amount, coinPos));
    }
}

public class CoinChange {
    private static Cache cache = new Cache();

    public static void printMap(Map<Long, Integer> changes) {
        for (Long key: changes.keySet()) {
            System.out.print(key + ":" + changes.get(key) + ",");
        }
        System.out.println();
    }

    public static long change(long amount, long[] coins, int coinPos, Map<Long, Integer> changes) {
        if (amount == 0) {
            printMap(changes);
            return 1;
        }

        if (coinPos >= coins.length) {
            return 0;
        }
        if (amount < 0) {
            System.out.println("Error");
            return 0;
        }

        if (cache.containsKey(amount, coinPos)) {
            return cache.get(amount, coinPos);
        }

        long coinValue = coins[coinPos];
        long ways = 0;

        for (int i = 0; i <= amount / coinValue; i++) {
            changes.put(coinValue, i);
            if (i > 0) {
                changes.put(coinValue, i);
            }
            ways += change(amount - i * coinValue, coins, coinPos + 1, changes);
        }

        cache.put(amount, coinPos, ways);

        return ways;
    }

    public static long getWays(long n, long[] c){
        if (n == 0 ||  c.length == 0) {
            return 0;
        }

        long ways = 0;

        Map<Long, Integer> changes = new HashMap<>();
        ways = change(n, c, 0, changes);

        return ways;
    }

}
