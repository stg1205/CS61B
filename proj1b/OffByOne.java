/**
 * "OffByOne" means "beside"
 */

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int a = x - y;
        return Math.abs(a) == 1;
    }
}
