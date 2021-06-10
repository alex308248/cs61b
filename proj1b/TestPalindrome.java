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


        CharacterComparator cc = new OffByOne();
        String word10 = "abcqcba";
        boolean actual10 = false;
        assertEquals(palindrome.isPalindrome(word10, cc), actual10);

        String word11 = "acddb";
        boolean actual11 = true;
        assertEquals(palindrome.isPalindrome(word11, cc), actual11);

        String word12 = "abcqqcba";
        boolean actual12 = false;
        assertEquals(palindrome.isPalindrome(word12, cc), actual12);

        String word13 = "z";
        boolean actual13 = true;
        assertEquals(palindrome.isPalindrome(word13, cc), actual13);

        String word14 = "";
        boolean actual14 = true;
        assertEquals(palindrome.isPalindrome(word14, cc), actual14);


        CharacterComparator ccN = new OffByN(5);

        assertEquals(palindrome.isPalindrome("abcqcba", ccN), false);
        assertEquals(palindrome.isPalindrome("azdxd", ccN), true);
        assertEquals(palindrome.isPalindrome("abcqqcba", ccN), false);
        assertEquals(palindrome.isPalindrome("ehjc", ccN), true);

    }
}
