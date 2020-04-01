/**
 * "OffByOne" means "beside"
 */

public class OffByOne implements CharacterComparator {

    private boolean isCharacter(char x) {
        int ascX = x;
        return (ascX >= 65 && ascX <= 90) || (ascX >= 97 && ascX <= 122);
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (!(isCharacter(x) && isCharacter(y))) {
            return false;
        }
        int a = x - y;
        return Math.abs(a) == 1;
    }
}
