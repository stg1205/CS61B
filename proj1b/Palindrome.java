public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordList = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i += 1) {
            wordList.addLast(word.charAt(i));
        }
        return wordList;
    }

    /** So excited to implement it in recursive way!! */
    private boolean isPalindrome(Deque<Character> wordList) {
        if (wordList.size() <= 1) {
            return true;
        }
        if (wordList.removeFirst() != wordList.removeLast()) {
            return false;
        }
        return isPalindrome(wordList);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordList = wordToDeque(word);
        return isPalindrome(wordList);
    }

    private boolean isPalindrome(Deque<Character> wordList, CharacterComparator cc) {
        if (wordList.size() <= 1) {
            return true;
        }
        if (!cc.equalChars(wordList.removeFirst(), wordList.removeLast())) {
            return false;
        }
        return isPalindrome(wordList, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);

        return isPalindrome(wordList, cc);
    }


}
