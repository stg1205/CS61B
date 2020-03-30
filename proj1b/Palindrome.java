public class Palindrome {

    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z]", "");
    }

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordList = new LinkListDeque<>();
        String cleanWord = cleanString(word);
        for (int i = 0; i < cleanWord.length(); i += 1) {
            wordList.addLast(cleanWord.charAt(i));
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
