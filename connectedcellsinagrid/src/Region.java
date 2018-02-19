import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Coord {
    public final int row;
    public final int col;

    Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Region {
    public static String myMethod(String input) {
        return "";
    }

    public static List<Coord> neighbours(Coord c, int[][] matrix) {
        List<Coord> n = new ArrayList<Coord>();
        for (int row = c.row - 1; row <= c.row + 1; row++) {
            for (int col = c.col -1; col <= c.col + 1; col++) {
                if (row >=0 && row < matrix.length && col >= 0 && col < matrix[0].length
                        && matrix[row][col] == 1) {
                        n.add(new Coord(row, col));
                        matrix[row][col] = 2;
                }
            }
        }
        return n;
    }

    public static int fill(int r, int c, int[][] matrix) {
        Queue<Coord> coords = new ArrayDeque<Coord>();
        matrix[r][c] = 2;
        coords.add(new Coord(r, c));

        int count = 0;

        while (!coords.isEmpty()) {
            Coord actual = coords.remove();
            count++;
            coords.addAll(neighbours(actual, matrix));
        }

        return count;
    }

    public static int connectedCell(int[][] matrix) {
        int max = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length;  c++) {
               if (matrix[r][c] == 1) {
                   int count = fill(r, c, matrix);
                   System.out.println("Fill:" + count);
                   if (count > max) {
                       max = count;
                   }
               }
            }
        }

        return max;
    }

}
