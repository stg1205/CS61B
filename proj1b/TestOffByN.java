import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {

    Palindrome palindrome = new Palindrome();
    CharacterComparator offByN = new OffByN(5);

    @Test
    public void testequalChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));
        assertFalse(offByN.equalChars('y', 'a'));
        assertFalse(offByN.equalChars('a', 'a'));
    }

    @Test
    public void testOffByNIsPalindrome() {
        String[] testString = {"", "a", "AF", "AbF", "AW", "FGBA", "aGbF"};
        assertTrue(palindrome.isPalindrome(testString[0], offByN));
        assertTrue(palindrome.isPalindrome(testString[1], offByN));
        assertTrue(palindrome.isPalindrome(testString[2], offByN));
        assertTrue(palindrome.isPalindrome(testString[3], offByN));
        assertFalse(palindrome.isPalindrome(testString[4], offByN));
        assertTrue(palindrome.isPalindrome(testString[5], offByN));
        assertFalse(palindrome.isPalindrome(testString[6], offByN));
    }
}
