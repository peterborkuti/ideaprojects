import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Abbrev {
    public static boolean abbrev(String input, String goal) {
        if (input.length() < goal.length()) {
            return false;
        }

        if (input.toUpperCase().equals(goal)) {
            return true;
        }

        if (goal.equals(input.replaceAll("[a-z]", ""))) {
            return true;
        }

        if (input.equals(input.toUpperCase()) && !input.equals(goal)) {
            return false;
        }

        StringBuilder s = new StringBuilder("[a-z]*?");
        for (int g = 0; g < goal.length(); g++) {
            s.append(goal.charAt(g) + ".*?");
        }


        /*
        int j = -1;
        for (int i = 0; i < goal.length(); i++) {
            if (j >= input.length() - 1) {
                return false;
            }
            j = input.indexOf(goal.charAt(i), j + 1);
            if (j == -1) {
                return false;
            }
        }
        */
        return true;
    }

}
