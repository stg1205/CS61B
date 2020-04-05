package byog.Core;

public class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Return middle position. */
    public Position middlePosition(Position des) {
        int middleX = des.x - this.x;
        int middleY = des.y - this.y;
        return new Position((int) (this.x + 0.5 * middleX), (int) (this.y + 0.5 * middleY));
    }
}
