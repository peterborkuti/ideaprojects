import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;


public class SpiesTest {
    private static String rowWithDebug(int row, int col, int length, int[] wrong) {
        StringBuilder s = new StringBuilder(
                String.join("", Collections.nCopies(length, "0"))
        );
        if ((wrong[0] == row && wrong[1] == col)|| (wrong[2] == row && wrong[3] == col) ||
                (wrong[4] == row || wrong[5] == col)) {
            s.setCharAt(col, '#');
        }
        else {
            s.setCharAt(col, 'X');
        }

        String wrongs = Arrays.stream(wrong).mapToObj(String::valueOf).collect(Collectors.joining(","));

        return s.toString() + ":" + wrongs;
    }

    private static String row(int col, int length) {
        StringBuilder s = new StringBuilder(
                String.join("", Collections.nCopies(length, "0"))
        );
        s.setCharAt(col, 'X');

        return s.toString();
    }

    private static void printMatrix(int[] cols) {
        String line = String.join("", Collections.nCopies(cols.length, "0"));
        //IntStream.range(0, cols.length).boxed()
        //    .map(i -> row(i, cols.length)).forEach(System.out::println);
    }

    private boolean noSpiesIn135Diagonal(int[] cols) {

        long count = IntStream.range(0,cols.length).map(i -> cols[i] + i).distinct().count();
        if (count != cols.length) {
            System.out.println("Spies in 135 diagonal!");
            printMatrix(cols);
        }

        return cols.length == count;

    }

    private boolean check(int[] cols) {
        boolean res =
            Spies.noSpiesInSameColumns(cols) &&
            Spies.noSpiesIn45Diagonal(cols) &&
            Spies.noSpiesIn135Diagonal(cols);

        //if (!res) {
            printMatrix(cols);
        //}

        return res;
    }

    private boolean no3spiesInLine(int[] cols) {
        return true;
    }

    @Test
    public void noSpiesInSameColumnsTest() {
        assertFalse(Spies.noSpiesInSameColumns(new int[]{0,0,0}) );
        assertTrue(Spies.noSpiesInSameColumns(new int[]{0,1,2}) );
    }

    @Test
    public void noSpiesIn45DiagonalTest() {
        assertFalse(Spies.noSpiesIn45Diagonal(new int[]{0,1,2}) );
        assertTrue(Spies.noSpiesIn45Diagonal(new int[]{0,0,0}) );
    }

    @Test
    public void noSpiesIn135DiagonalTest() {
        assertFalse(Spies.noSpiesIn135Diagonal(new int[]{2,1,0}) );
        assertTrue(Spies.noSpiesIn135Diagonal(new int[]{0,0,0}) );
    }
    //@Test
    public void placeTest() {
        //assertTrue("test input", check(Spies.place(1)));
        //assertTrue("test input", check(Spies.place(3)));
        assertTrue("test input", check(Spies.place(5)));
    }

    private String permHelper(String input) {
        Set<Character> c = new HashSet<>();

        c.addAll(Arrays.stream(input.split("")).map(s->s.charAt(0)).collect(Collectors.toList()));

        List<List<Character>> perms = Spies.permutations(c);

        return perms.stream().map(l -> l.stream().map(e->e.toString()).collect(Collectors.joining())).
                collect(Collectors.joining(","));
    }

    //@Test
    public void permutationTest() {
        assertEquals("1", permHelper("1"));
        assertEquals("21,12", permHelper("12"));
        assertEquals("321,231,312,132,213,123", permHelper("123"));
    }

    //@Test
    public void heapsPermutationTest() {
        HeapSPermutations hp = new HeapSPermutations(3);
        while (!hp.done()) System.out.println(
                hp.generate().stream().
                        map(String::valueOf).
                        collect(Collectors.joining(",")));

    }

    //@Test
    public void generatorTest() {
        Stream s1 = StreamSupport.stream(new Spies.PermutationSpliterator(5), false);
        Stream s2 = StreamSupport.stream(new Spies.PermutationSpliterator(5), false);
        long count = s1.count();
        long distinctCount = s2.distinct().count();
        assertEquals(121, count);
        assertTrue(count == distinctCount);
    }


    private static int[] _3SpiesInLine(List<Integer> spies) {
        for (int spy1 = 0; spy1 < spies.size(); spy1++) {
            for (int spy2 = spy1 + 1; spy2 < spies.size(); spy2++) {
                for (int spy3 = spy2 + 1; spy3 < spies.size(); spy3++) {
                    if (inLine(spy1, spies.get(spy1), spy2, spies.get(spy2), spy3, spies.get(spy3))) {
                        return new int[]{spy1, spies.get(spy1), spy2, spies.get(spy2), spy3, spies.get(spy3)};
                    }
                }
            }
        }

        return new int[0];
    }

    public static void printList(List<Integer> l) {
        int[] spiesInLine = _3SpiesInLine(l);
        System.out.println("-------------------");
        IntStream.range(0, l.size()).boxed()
                .map(i -> row(l.get(i), l.size())).forEach(System.out::println);
    }

    //@Test
    public void eightQueenTest() {
        Stream s = StreamSupport.stream(new Spies.PermutationSpliterator(9), false);
        s.filter(l -> goodPlacement((List<Integer>)l)).forEach(l -> printList((List<Integer>)l));
    }

    private static void printHackerRankList(List<Integer> l) {
        System.out.println("print \"" + l.size() + "\"");

        String list = l.stream().map(i -> new Integer(i+1).toString()).collect(Collectors.joining(" "));
        System.out.println("print \"" + list + "\"");
    }


    @Test
    public void outputForHackerRank() {
        Stream s = StreamSupport.stream(new Spies.PermutationSpliterator(9), true);
        s.filter(l -> goodPlacement((List<Integer>)l)).limit(1).forEach(l -> printHackerRankList((List<Integer>)l));
    }

    private static boolean inLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        c2-=c1;
        c3-=c1;
        r2-=r1;
        r3-=r1;

        float d3 = (float)r3 / (float)c3;
        float d2 = (float)r2 / (float)c2;

        //System.out.println("d3,d2:" + d3 + "," + d2);

        return Math.abs(d3 - d2) < 0.0001;
    }

    //@Test
    public void inLineTest() {
        assertTrue( inLine(0,0 , 4, -2, 10, -5));
        assertTrue( inLine(1,2 , 4, 3, 7, 4));
        assertTrue( inLine(1,2 , 4, 3, 10, 5));
        assertFalse( inLine(1,2 , 4, 3, 10, 4));
        assertFalse( inLine(0,3 , 1, 1, 2, 6));

    }

    private boolean no3SpiesInLine(List<Integer> spies) {
        for (int spy1 = 0; spy1 < spies.size(); spy1++) {
            for (int spy2 = spy1 + 1; spy2 < spies.size(); spy2++) {
                for (int spy3 = spy2 + 1; spy3 < spies.size(); spy3++) {
                    if (inLine(spy1, spies.get(spy1), spy2, spies.get(spy2), spy3, spies.get(spy3))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean goodPlacement(List<Integer> cols) {
        boolean res =
                        Spies.noSpiesIn45Diagonal(cols) &&
                        Spies.noSpiesIn135Diagonal(cols) &&
                        no3SpiesInLine(cols);

        //if (!res) {
        //printMatrix(cols);
        //}

        return res;
    }

}
