import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class GreedyMaxMin {

    public static String myMethod(String input) {
        return "";
    }

    static int angryChildren(int k, String arr) {
        int[] intArr = Arrays.stream(arr.split(" ")).mapToInt(Integer::parseInt).toArray();

        return angryChildren(k, intArr);
    }
    static int angryChildren(int k, int[] arr) {
        int[] sortedArr =  Arrays.stream(arr).sorted().toArray();

        int[] unfairness = new int[sortedArr.length];

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < sortedArr.length - k + 1; i++) {
            int d = sortedArr[i + k - 1] - sortedArr[i];
            if (d < min) min = d;
        }

        return min;
    }

    public static int[][] sortMatrix(int[][] arr){
        return Arrays.stream(arr).sorted((a,b) -> Integer.compare(a[0],b[0])).toArray(int[][]::new);
    }

}
