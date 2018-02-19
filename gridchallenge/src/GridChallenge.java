import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class GridChallenge {

    public static boolean columnIsSorted(String[] grid, int column) {
        for (int r = 1; r < grid.length; r++) {
            if (grid[r].charAt(column) < grid[r-1].charAt(column)) {
                return false;
            }
        }

        return true;
    }

    public static boolean allColumnAreSorted(String[] grid) {
        for (int c = 0; c < grid[0].length(); c++) {
            if (!columnIsSorted(grid, c)) {
                return false;
            }
        }

        return true;
    }

    public static String sortGrid(String[] grid) {
        return Arrays.stream(String.join("", grid).split("")).sorted().collect(Collectors.joining());
    }

    public static String[] stringToGrid(String string, int length) {
        //\G: start at the end of the previous match
        //?<=: via zero-width positive lookbehind
        //.{n}:exactly n characters
        return string.split(String.format("(?<=\\G.{%1$d})", length));
    }

    public static String stringSort(String s) {
        return Arrays.stream(s.split("")).sorted().reduce("", (x,y) -> x + y);
    }

    public static String[] sortRows(String[] grid) {
        return Arrays.stream(grid).map(GridChallenge::stringSort).toArray(String[]::new);
    }

    public static boolean myMethod(String[] grid) {
        String[] sortedGrid = Arrays.stream(grid).map(GridChallenge::stringSort).toArray(String[]::new);
        return allColumnAreSorted(sortedGrid);
    }

}
