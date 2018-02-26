import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonChildTest {
    private String listToString(List<Letter> l) {
        return l.stream().map(i -> "" + i.letter + i.count).collect(Collectors.joining(""));
    }

    //@Test
    public void getLetterNumbersTest() {
        assertEquals("test input", "A4", listToString(CommonChild.getLetterNumbers("AAAA")));
        assertEquals("test input", "A4B3", listToString(CommonChild.getLetterNumbers("AAAABBB")));
        assertEquals("test input", "A4B3A2C1", listToString(CommonChild.getLetterNumbers("AAAABBBAAC")));
        //assertEquals("test input", "ABB", CommonChild.process("AABB","ABBB"));
    }

    //@Test
    public void deleteDiffsTest() {
        assertEquals("test input", "AB", CommonChild.deleteNonExisitingChars("ABC", "AB"));
        assertEquals("test input", "AB", CommonChild.deleteNonExisitingChars("AB", "ABC"));
        assertEquals("test input", "HNHAN", CommonChild.deleteNonExisitingChars("SHINCHAN", "NOHARAAA"));
        assertEquals("test input", "NHAAAA", CommonChild.deleteNonExisitingChars("NOHARAAA", "SHINCHAN"));
    }

    private String mergeHelper(String s1, String s2) {
        List<Letter> a = CommonChild.getLetterNumbers(s1);
        List<Letter> b = CommonChild.getLetterNumbers(s2);

        //System.out.println(listToString(a) + "," + listToString(b));

        return listToString(CommonChild.merge(a, b));
    }

    private boolean checkResult(String expected, String result) {
        List<String> exp = Arrays.asList(expected.split(","));

        if (!exp.contains(result))
            System.out.println("Expected:" + expected + ", but got " + result);

        return exp.contains(result);
    }
    //@Test
    public void mergeTest() {
        assertEquals("test input", "A1B1", mergeHelper("AB", "AB"));
        assertEquals("test input", "A1B1", mergeHelper("AB", "AABB"));
        assertEquals("test input", "A1B1", mergeHelper("AABB", "AB"));
        assertEquals("test input", "A1B2", mergeHelper("AABB", "ABBBA"));
        assertEquals("test input", "A1B2", mergeHelper("AABBBA", "ABB"));
        assertEquals("test input", "A1B2A1B1", mergeHelper("AABBBABB", "ABBAB"));
        assertTrue("test input", checkResult("A1B2C1B1,A1B2C1A1", mergeHelper("AABBBCBA", "ABBCAB")));
        assertTrue("test input", checkResult("A1B2C1B1,A1B2C1A1", mergeHelper("ABBCAB", "AABBBCBA")));
        assertTrue("test input", checkResult("A1B2C1A1B3,A1B2C1B4", mergeHelper("ABBCABBBB", "AABBBCBABBB")));
        assertTrue("test input", checkResult("A1B2C1A1B2A4", mergeHelper("ABBCABBAAAA", "AABBBCBABBAAAA")));
        assertTrue("test input", checkResult("N1H1A1", mergeHelper("HNHAN", "NHAAAA")));
    }

    //@Test
    public void processTest() {
        assertEquals("test input", 0, CommonChild.process("AA", "BB"));
        assertEquals("test input", 2, CommonChild.process("HARRY", "SALLY"));
        assertEquals("test input", 3, CommonChild.process("ABCD", "ABDC"));
        assertEquals("test input", 3, CommonChild.process("SHINCHAN", "NOHARAAA"));
        assertEquals("test input", 2, CommonChild.process("ABCDEF", "FBDAMN"));
    }

    @Test
    public void LCSLengthTest() {
        //assertEquals("test input", 0, CommonChild.LCSLength("AA", "BB"));
        assertEquals("test input", 2, CommonChild.LCSLength("HARRY", "SALLY"));
        //assertEquals("test input", 3, CommonChild.LCSLength("ABCD", "ABDC"));
        //assertEquals("test input", 3, CommonChild.LCSLength("SHINCHAN", "NOHARAAA"));
        //assertEquals("test input", 2, CommonChild.LCSLength("ABCDEF", "FBDAMN"));
    }
}
