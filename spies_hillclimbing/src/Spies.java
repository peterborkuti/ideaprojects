import com.sun.javafx.UnmodifiableArrayList;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Utils {

    public static List<Integer> minusOne(List<Integer> l) {
        return l.stream().map(i->i-1).collect(Collectors.toList());
    }

    public static List<Integer> plusOne(List<Integer> l) {
        return l.stream().map(i->i+1).collect(Collectors.toList());
    }

    public static List<Integer> stringToList(String s, String... delimiters) {
        String delimiter = " ";
        if (delimiters.length > 0) {
            delimiter = delimiters[0];
        }
        return Arrays.stream(s.split(delimiter)).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
    }

    public static String listToString(List<Integer> l, String... delimiters) {
        String delimiter = " ";
        if (delimiters.length > 0) {
            delimiter = delimiters[0];
        }
        return l.stream().map(i->Integer.toString(i)).collect(Collectors.joining(delimiter));
    }


    private static String getRow(int col, int length, Character... characters) {
        Character character = 'X';
        if (characters.length != 0) {
            character = characters[0];
        }
        StringBuilder s = new StringBuilder(
                String.join("", Collections.nCopies(length, "0"))
        );
        s.setCharAt(col, character);

        return s.toString();

    }

    private static String getRow(int row, int col, int length, int[] wrong) {
        StringBuilder s = new StringBuilder(
                String.join("", Collections.nCopies(length, "0"))
        );

        char c = 'X';

        if (wrong.length > 0) {
            if ((wrong[0] == row && wrong[1] == col) || (wrong[2] == row && wrong[3] == col) ||
                    (wrong[4] == row || wrong[5] == col)) {
                c = '#';
            }
        }

        s.setCharAt(col, c);

        //String wrongs = Arrays.stream(wrong).mapToObj(String::valueOf).collect(Collectors.joining(","));

        return s.toString(); // + ":" + wrong;
    }

    public static void printMatrix(List<Integer> l, int[] wrong) {
        System.out.println("-------------------");
        IntStream.range(0, l.size()).boxed()
                .map(i -> getRow(i, l.get(i), l.size(), wrong)).forEach(System.out::println);
    }
}

class Check {
    public static boolean inLine(int r1, int c1, int r2, int c2, int r3, int c3) {
        c2-=c1;
        c3-=c1;
        r2-=r1;
        r3-=r1;

        float d3 = (float)r3 / (float)c3;
        float d2 = (float)r2 / (float)c2;

        //System.out.println("d3,d2:" + d3 + "," + d2);

        return Math.abs(d3 - d2) < 0.0001;
    }

    private static int[] no3SpiesInLine(List<Integer> spies) {
        for (int spy1 = 0; spy1 < spies.size(); spy1++) {
            for (int spy2 = spy1 + 1; spy2 < spies.size(); spy2++) {
                for (int spy3 = spy2 + 1; spy3 < spies.size(); spy3++) {
                    if (inLine(spy1, spies.get(spy1), spy2, spies.get(spy2), spy3, spies.get(spy3))) {

                        return new int[]{spy1, spies.get(spy1), spy2, spies.get(spy2), spy3, spies.get(spy3)};
                    }
                }
            }
        }

        return new int[]{};
    }


    public static boolean noSpiesIn45Diagonal(List<Integer> cols) {
        long count = IntStream.range(0,cols.size()).map(i -> cols.get(i) - i).distinct().count();

        return cols.size() == count;
    }


    public static boolean noSpiesIn135Diagonal(List<Integer> cols) {
        long count = IntStream.range(0,cols.size()).map(i -> cols.get(i) + i).distinct().count();

        return cols.size() == count;
    }


    public static boolean goodPlacement(List<Integer> cols, boolean debug) {
        boolean res =
                noSpiesIn45Diagonal(cols) &&
                noSpiesIn135Diagonal(cols);
        int[] uncoveredSpies = no3SpiesInLine(cols);

        if (debug) {
            Utils.printMatrix(cols, uncoveredSpies);
        }

        return res && uncoveredSpies.length == 0;
    }

}

class CheckOutput {
    public final boolean noSpiesIn45;
    public final boolean noSpiesIn135;
    public final UnmodifiableArrayList<Integer> threeSpiesInLine;

    public final boolean summary;

    public CheckOutput(boolean noSpiesIn45, boolean noSpiesIn135, UnmodifiableArrayList<Integer> threeSpiesInLine) {
        this.noSpiesIn45 = noSpiesIn45;
        this.noSpiesIn135 = noSpiesIn135;
        this.threeSpiesInLine = threeSpiesInLine;
        summary = noSpiesIn45 && noSpiesIn135 && threeSpiesInLine.size() == 0;
    }
}

class Output {
    public Output(UnmodifiableArrayList<Integer> cols, CheckOutput checks) {
        this.cols = cols;
        this.checks = checks;
    }

    public final UnmodifiableArrayList<Integer> cols;
    public final CheckOutput checks;
}

public class Spies {
    public static String hillClimbing(String input) {
        List<Integer> goodPlacement = Utils.minusOne(Utils.stringToList(input));

        Output biggerPlacement = climbOne(goodPlacement);


        return Utils.listToString(Utils.plusOne(biggerPlacement.cols));
    }

    public static Output climbOne(List<Integer> cols) {
        List<Integer> newCols = new ArrayList<>();
        newCols.addAll(cols);
        newCols.add(0);

        int newCol = cols.size();
        int N = cols.size();

        for (int row = 0; row < cols.size(); row++) {
            int oldCol = newCols.get(row);
            newCols.set(row, newCol);
            newCols.set(N, oldCol);

            CheckOutput checks = Check.goodPlacement(newCols, true);
            if (checks.summary) {
                return newCols;
            }

            newCols.set(row, oldCol);
        }

        newCols.set(N, N);
        if (Check.goodPlacement(newCols, true)) {
            return newCols;
        }

        return cols;
    }

}
