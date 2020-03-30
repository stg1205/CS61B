import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offbyone = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < "persiflage".length(); i++) {
            actual.append(d.removeFirst());
        }
        assertEquals("persiflage", actual.toString());
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        String[] testString = {"", "a", "Aa", "Aba", "asdgaerg", "abaa", "abba"};
        assertTrue(palindrome.isPalindrome(testString[0]));
        assertTrue(palindrome.isPalindrome(testString[1]));
        assertTrue(palindrome.isPalindrome(testString[6]));
        assertFalse(palindrome.isPalindrome(testString[2]));
        assertFalse(palindrome.isPalindrome(testString[3]));
        assertFalse(palindrome.isPalindrome(testString[4]));
        assertFalse(palindrome.isPalindrome(testString[5]));
    }

    @Test
    public void testOffByOneIsPalindrome() {
        String[] testString = {"", "a", "AB", "AbB", "AZ", "BCBA", "abba"};
        assertTrue(palindrome.isPalindrome(testString[0], offbyone));
        assertTrue(palindrome.isPalindrome(testString[1], offbyone));
        assertTrue(palindrome.isPalindrome(testString[2], offbyone));
        assertTrue(palindrome.isPalindrome(testString[3], offbyone));
        assertFalse(palindrome.isPalindrome(testString[4], offbyone));
        assertTrue(palindrome.isPalindrome(testString[5], offbyone));
        assertFalse(palindrome.isPalindrome(testString[6], offbyone));
    }
}
