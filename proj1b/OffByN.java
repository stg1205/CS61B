public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int i) {
        N = i;
    }

    public boolean equalChars(char x, char y) {
        int a = x - y;
        return Math.abs(a) == N;
    }
}
