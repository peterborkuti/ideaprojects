import java.util.*;
import java.util.function.Consumer;
import java.util.stream.*;
import java.util.concurrent.*;

class Coord {
    public final int row;
    public final int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Coord(int index, int rows, int cols) {
        this.row = index / rows;
        this.col = index - row * rows;
    }

    public boolean isMainCorner(int rows, int cols) {
        return (row == 0 && col == 0) || (row == rows -1 && col == cols -1);
    }

    public boolean isIn(int rows, int cols) {
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public List<Coord> neighbours(int rows, int cols) {
        List<Coord> n = new ArrayList<>();

        for (int r: new int[]{-1, 1}) {
            Coord check = new Coord(row + r, col);

            n.add(check.isIn(rows, cols) ? check : this);
        }

        for (int c: new int[]{-1, 1}) {
            Coord check = new Coord(row, col + c);

            n.add(check.isIn(rows, cols) ? check : this);
        }

        return n;
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public String getAction(Coord from) {
        if (from.row > this.row) return "^";
        if (from.row < this.row) return "D";
        if (from.col > this.col) return "<";

        return ">";
    }

}

public class RL {
    public final int rows;
    public final int cols;

    public final double discount = 1.0;

    double[][] value;

    public final double policy = 0.25;

    public RL(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        value = new double[rows][cols];
    }

    public double getReward(Coord c) {
        return (c.isMainCorner(rows, cols) ? 0 : -1);
    }

    public double getValue(Coord c) {
        return value[c.row][c.col];
    }

    public double getAllValues(Coord main) {
        if (main.isMainCorner(rows,cols)) return 0;

        double sum = 0;

        for (Coord neighbour : main.neighbours(rows, cols)) {
            sum += policy * getValue(neighbour);
        }

        return sum;
    }

    public double[][] updateValue() {
        double[][] newValue = new double[rows][cols];

        for (int r = 0; r < value.length; r++) {
            for (int c = 0; c < value.length; c++) {
                Coord center = new Coord(r, c);
                newValue[r][c] = getReward(center) + discount * getAllValues(center);
            }
        }

        return newValue;
    }

    public Consumer<double[]> printLine = new Consumer<double[]>() {
        @Override
        public void accept(double[] doubles) {
            System.out.println(
                Arrays.stream(doubles).
                        mapToObj(d -> String.format("%05.1f", d)).
                        collect(Collectors.joining(" "))
            );
        }
    };

    public void printValues() {
        Arrays.stream(value).forEach(this.printLine);
    }

    public Consumer<Coord> printAction = new Consumer<Coord>() {
        @Override
        public void accept(Coord coord) {
            if (coord.isMainCorner(rows, cols)) {
                System.out.print("  o  |");
                return;
            }

            List<Coord> neighbours = ((Coord)coord).neighbours(rows,cols);
            List<Coord> maxNeighbours = new ArrayList<Coord>();
            double max = Integer.MIN_VALUE;
            for (Coord n : neighbours) {
                double m = getValue(n);
                if (m > max) max = m;
            }

            final double mmax = max;

            String actions = neighbours.stream().
                filter(c -> getValue(c) == mmax).
                    map(c -> c.getAction((Coord)coord)).
                    collect(Collectors.joining());
            System.out.print(actions.format("%5s|", actions));
        }
    };


    public Consumer<Integer> printActionRow = new Consumer<Integer>() {
        @Override
        public void accept(Integer row) {
            IntStream.range(0, cols).
                    mapToObj(col -> new Coord(row, col)).forEach(printAction);
            System.out.println();
        }
    };

    public void printActions() {
        IntStream.range(0, rows).boxed().forEach(printActionRow);

    }


    public void doOneStep(int k) {
        System.out.println("----STEP " + k + " BEGIN-----------");
        printValues();
        value = updateValue();
        System.out.println("--->>>>");
        printValues();
        printActions();
        System.out.println("----STEP " + k + " END-----------\n\n\n");
    }

}
