import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        String word0 = "aba";
        boolean actual0 = true;
        assertEquals(palindrome.isPalindrome(word0), actual0);

        String word1 = "abcqqcba";
        boolean actual1 = true;
        assertEquals(palindrome.isPalindrome(word1), actual1);

        String word2 = "abcbaa";
        boolean actual2 = false;
        assertEquals(palindrome.isPalindrome(word2), actual2);

        String word3 = "z";
        boolean actual3 = true;
        assertEquals(palindrome.isPalindrome(word3), actual3);

        String word4 = "";
        boolean actual4 = true;
        assertEquals(palindrome.isPalindrome(word4), actual4);
    }
    /*
    @Test
    public void testisPalindrome1() {
        CharacterComparator cc = new OffByOne();

        String word0 = "abcqcba";
        boolean actual0 = true;
        assertEquals(palindrome.isPalindrome(word0, cc), actual0);

        String word1 = "abcqqcba";
        boolean actual1 = true;
        assertEquals(palindrome.isPalindrome(word1, cc), actual1);

        String word2 = "abcbaa";
        boolean actual2 = false;
        assertEquals(palindrome.isPalindrome(word2, cc), actual2);

        String word3 = "z";
        boolean actual3 = true;
        assertEquals(palindrome.isPalindrome(word3, cc), actual3);

        String word4 = "";
        boolean actual4 = true;
        assertEquals(palindrome.isPalindrome(word4, cc), actual4);
    }*/
}
