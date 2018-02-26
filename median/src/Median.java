import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Median {
    public List<Integer> lower = new ArrayList<>();
    public List<Integer> upper = new ArrayList<>();
    public Integer lowerMax = Integer.MAX_VALUE;
    public Integer upperMin = Integer.MIN_VALUE;


    public void balance() {
        while (lower.size() > 0 && (lower.size() - upper.size()) > 1) {
            upperMin = lowerMax;
            lower.remove(lowerMax);
            upper.add(lowerMax);
            lowerMax = Collections.max(lower);
        }

        while (upper.size() > 0 && (upper.size() - lower.size()) > 1) {
            lowerMax = upperMin;
            upper.remove(upperMin);
            lower.add(upperMin);
            upperMin = Collections.min(upper);
        }
    }

    public void put(int in) {
        if (in <= lowerMax) {
            if (lower.size() == 0) lowerMax = in;
            lower.add(in);
        }
        else {
            if (upper.size() == 0) upperMin = in;
            upper.add(in);
        }

        if (Math.abs(lower.size()-upper.size())>2) {
            balance();
        }
    }

    public int get() {
        if (lower.size() == upper.size()) {
            return lowerMax;
        }

        if (lower.size() < upper.size()) {
            return upperMin;
        }

        return lowerMax;
    }

}
