import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

class Letter {
    public final char letter;
    public final int count;
    Letter(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter1 = (Letter) o;

        return letter == letter1.letter;
    }

    @Override
    public int hashCode() {
        return (int) letter;
    }
}
public class CommonChild {

    public static void printMatrix(int[][] m){
        Arrays.stream(m).
                map(
                    row ->Arrays.stream(row).boxed().map(i -> i.toString()).collect(Collectors.joining())
                ).
                forEach(s -> System.out.println(s));
    }

    public static int LCSLength(String X, String Y) {
        System.out.println("LCSLength:" + X + "," + Y);
        int m = X.length();
        int n = Y.length();
        int[][] C = new int[m + 1][n + 1];
        for (int i=1; i <= m; i++) {
            for (int j=1; j <= n; j++) {
                int Xpos = i - 1;
                int Ypos = j - 1;
                char a = X.charAt(Xpos);
                char b = Y.charAt(Ypos);
                System.out.println("Checking " + a + "," + b + " at " + i + "," + j);
                if (a == b) {
                    C[i][j] = C[i - 1][j - 1]+1;
                }
                else {
                    C[i][j] = Math.max(C[i][j - 1], C[i - 1][j]);
                }
                printMatrix(C);
            }
        }
        return C[m][n];
    }

    public static String deleteNonExisitingChars(String deleteFrom, String checkIn){
        return Arrays.stream(deleteFrom.split("")).filter(c -> checkIn.contains(c)).collect(Collectors.joining(""));
    }

    public static List<Letter> merge(List<Letter> l1, List<Letter> l2) {
        List<Letter> l = new ArrayList<>();

        for (int i = 0, j = 0; i < l1.size() && j < l2.size();) {
            Letter a = l1.get(i);
            Letter b = l2.get(j);
            if (a.letter == b.letter) {
                l.add(new Letter(a.letter, Math.min(a.count, b.count)));
                i++;
                j++;
            }
            else {
                if ((l1.size() - i) > (l2.size() - j)) {
                    i++;
                }
                else {
                    j++;
                }
            }
        }

        return l;
    }

    public static int getListLetterLength(List<Letter> l) {
        return l.stream().mapToInt(i -> i.count).sum();
    }
    public static int process(String s1, String s2) {
        s1 = deleteNonExisitingChars(s1, s2);
        s2 = deleteNonExisitingChars(s2, s1);
        List<Letter> l1 = getLetterNumbers(s1);
        List<Letter> l2 = getLetterNumbers(s2);
        List<Letter> merged = merge(l1, l2);
        return getListLetterLength(merged);
    }

    public static List<Letter> getLetterNumbers(String s) {
        List<Letter> l = new ArrayList<>();

        if (s.length() == 0) return l;

        char prevc = s.charAt(0);
        int counter = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if ( c == prevc ) {
                counter++;
            }
            else {
                l.add(new Letter(prevc, counter));
                prevc = c;
                counter = 1;
            }
        }
        l.add(new Letter(prevc, counter));

        return l;
    }

}
