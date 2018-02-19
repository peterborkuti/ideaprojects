import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.*;
import java.util.concurrent.*;

class HeapSPermutations {
    private int[] c;
    private int[] A;
    private int i;
    private final int n;
    private boolean first = true;

    public boolean done() {
        return i >= n;
    }

    public HeapSPermutations(int N) {
        this.n = N;
        c = Collections.nCopies(n, 0).stream().mapToInt(Integer::intValue).toArray();
        A = IntStream.range(0, n).toArray();
        i = 0;
    }

    public static String arrToString(int[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    public void print() {
        System.out.println("i:" + i);
        System.out.println("c:" + arrToString(c));
        System.out.println("A:" + arrToString(A));
    }

    private void swapA(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public List<Integer> getArray() {
        return Arrays.stream(A).mapToObj(Integer::new).collect(Collectors.toList());
    }

    private boolean isSolution() {
        return true;
    }

    public List<Integer> generate() {
        boolean found = false;

        if (first) {
            first = false;
            found = isSolution();
        }

        while (i < n && !found) {
            //print();
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swapA(0, i);
                } else {
                    swapA(c[i], i);
                }

                c[i]++;
                i = 0;
                found = isSolution();
            }
            c[i] = 0;
            i++;
        }

        if (found) return getArray();

        return new ArrayList<Integer>();
    }
}

public class Spies {
    public static class PermutationSpliterator extends
            Spliterators.AbstractSpliterator
    {
        private HeapSPermutations hp;

        protected PermutationSpliterator(int N)
        {
            super(Long.MAX_VALUE, 0);
            hp = new HeapSPermutations(N);
        }

        @Override
        public boolean tryAdvance(Consumer action)
        {
            if (action == null)
            {
                throw new NullPointerException();
            }

            if (hp.done())
            {
                return false;
            }

            action.accept(hp.generate());

            return true;
        }
    }

    public static <T> List<List<T>> permutations(Set<T> elements) {
        Set<T> work = new HashSet<>();
        work.addAll(elements);
        List<List<T>> perm = new ArrayList<>();

        if (elements.isEmpty()) {
            return perm;
        }

        if (elements.size() == 1) {
            List<T> p = new ArrayList<>();
            p.addAll(elements.stream().collect(Collectors.toList()));
            perm.add(p);

            return perm;
        }


        for (T e: elements) {
            work.clear();
            work.addAll(elements);
            work.remove(e);
            List<List<T>> shorterPerms = permutations(work);
            for (List<T> sp: shorterPerms) {
                sp.add(e);
                perm.add(sp);
            }
        }

        return perm;
    }

    public static boolean noSpiesInSameColumns(int[] cols) {
        long count = Arrays.stream(cols).distinct().count();
        if (count != cols.length) {
            //System.out.println("Spies in the same column!");
            //printMatrix(cols);
        }
        return cols.length == count;
    }

    public static boolean noSpiesIn45Diagonal(int[] cols) {
        long count = IntStream.range(0,cols.length).map(i -> cols[i] - i).distinct().count();
        if (count != cols.length) {
            //System.out.println("Spies in 45 diagonal!");
            //printMatrix(cols);
        }

        return cols.length == count;

    }

    public static boolean noSpiesIn45Diagonal(List<Integer> cols) {
        long count = IntStream.range(0,cols.size()).map(i -> cols.get(i) - i).distinct().count();
        if (count != cols.size()) {
            //System.out.println("Spies in 45 diagonal!");
            //printMatrix(cols);
        }

        return cols.size() == count;
    }


    public static boolean noSpiesIn135Diagonal(int[] cols) {

        long count = IntStream.range(0,cols.length).map(i -> cols[i] + i).distinct().count();
        if (count != cols.length) {
            //System.out.println("Spies in 135 diagonal!");
            //printMatrix(cols);
        }

        return cols.length == count;

    }

    public static boolean noSpiesIn135Diagonal(List<Integer> cols) {

        long count = IntStream.range(0,cols.size()).map(i -> cols.get(i) + i).distinct().count();
        if (count != cols.size()) {
            //System.out.println("Spies in 135 diagonal!");
            //printMatrix(cols);
        }

        return cols.size() == count;

    }

    public static boolean check(int[] cols) {
        return
            noSpiesInSameColumns(cols) &&
            noSpiesIn45Diagonal(cols) &&
            noSpiesIn135Diagonal(cols);
    }

    public static boolean no3spiesInLine(int[] cols) {
        return true;
    }

    public static int[] place(int N) {
        int[] places = Collections.nCopies(N, 0).stream().mapToInt(Integer::intValue).toArray();
        while (places[N-1] < N) {
            if (check(places)) {
                return places;
            }

            for (int r = 0; r < N; r++) {
                places[r]++;
                if (places[r] == N) {
                    places[r] = 0;
                }
                else {
                    break;
                }
            }

        }

        return new int[]{};
    }

}
