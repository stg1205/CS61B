/**
 * For what N are there the most palindromes in English?
 * What is the longest offByN palindrome for any N?
 */

public class JustForMoreFun {

    public static void main(String[] args) {
        In in;
        Palindrome palindrome = new Palindrome();
        int count, longestN = 0, largestNum = 0, mostN = 0;
        String longestWord = "";
        CharacterComparator cc;


        for (int i = 0; i < 26; i += 1) {
            in = new In("../library-sp18/data/words.txt");
            cc = new OffByN(i);
            count = 0;
            while (!in.isEmpty()) {
                String word = in.readString();
                if (palindrome.isPalindrome(word, cc)) {
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                        longestN = i;
                    }
                    count += 1;
                }
                //To skip while(). Perhaps there's better way.
                if (in.isEmpty()) {
                    int debug = 1;
                }
            }
            if (count > largestNum) {
                largestNum = count;
                mostN = i;
            }
        }
        System.out.println("OffByN " + mostN +
                           " has most palindromes in English——" + largestNum);
        System.out.println("OffByN " + longestN +
                           " has the longest offByN palindrome——" + longestWord);
    }
}
