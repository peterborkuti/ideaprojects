import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

/**
 * LongestCommonSubsequence (LCS)
 *
 * X,Y: two strings
 * Xi: X.substring(0, i) = X[0..i-1]
 * LCS(Xi,Yj) =
 *      () if i <= 0 or j <= 0
 *      LCS(Xi-1, Yj-1) + X[i-1], if X[i-1] == Y[j-1]
 *      longest( LCS(X-i-1, Yj) , LCS(Xi, Yj-1)
 */

class LCS2 {
    private final String x,y;

    public LCS2(String s1, String s2) {
        x = s1;
        y = s2;
    }

    public int LCS() {
        return LCS(x.length(), y.length());
    }

    private int LCS(int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (x.charAt(i - 1) == y.charAt(j - 1)) {
            return 1 + LCS(i -1, j - 1);
        }

        return Math.max(
                LCS(i - 1, j),
                LCS(i, j - 1)
        );
    }
}

public class CommonChildDP {
    public static int LCS(String x, String y) {
        int i = x.length();
        int j = y.length();
        if (i == 0 || j == 0) {
            return 0;
        }

        if (x.charAt(i - 1) == y.charAt(j - 1)) {
            return 1 + LCS(x.substring(0, i - 1), y.substring(0, j - 1));
        }

        return Math.max(
                LCS(x.substring(0, i - 1), y),
                LCS(x, y.substring(0, j - 1))
        );
    }

}
