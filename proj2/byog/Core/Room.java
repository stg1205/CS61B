package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Room extends RectangleHelper {
    private int height;
    private int width;
    private final Position position;

    /** Property of a rectangle room. p is at the bottom left */
    public Room(int height, int width, Position position) {
        this.height = height;
        this.width = width;
        this.position = position;
    }

    /** Overlap checked method.
     * First check if a point in the room.
     * Then calculate four corner points.
     * Finally check if these four points in the room.
     */
    private boolean containPosition(Position p) {
        return (p.x <= position.x + width - 1 && p.x >= position.x)
            && (p.y <= position.y + height - 1 && p.y >= position.y);
    }

    public boolean isOverlap(Room r) {
        Position[] p = cornerPositions(r.position, r.width, r.height);
        for (int i = 0; i < 4; i++) {
            if (containPosition(p[i])) {
                return true;
            }
        }
        return false;
    }

    /** Print room method.
     *  When meet the edge, break.
     */
    public void printRoom(TETile[][] world) {

        // Print vertical wall. Don't crash the edge.
        for (int i = 0; i < height; i++) {
            // If crash the edges, update height and width.
            if (position.y + i == world[0].length - 1) {
                height = i + 1;
                break;
            }
            world[position.x][position.y + i] = Tileset.WALL;
            if (position.x + width - 1 < world.length - 1) {
                world[position.x + width - 1][position.y + i] = Tileset.WALL;
            } else {
                width = world.length - position.x;
            }
        }
        // Print horizontal wall.
        for (int i = 0; i < width; i++) {
            world[position.x + i][position.y] = Tileset.WALL;
            world[position.x + i][position.y + height - 1] = Tileset.WALL;
        }

        // Print floor.
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                world[position.x + j][position.y + i] = Tileset.FLOOR;
            }
        }
    }

    /** Create a random room with position, width and height. */
    private static Room randomRoom(Random RANDOM, TETile[][] world) {
        // The distance between edge and position will over 3,
        // in case of one TETile side length.
        int xP = RANDOM.nextInt((int) (0.5 * (world.length - 2))) * 2;
        int yP = RANDOM.nextInt((int) (0.5 * (world[0].length - 2))) * 2;
        Position p = new Position(xP, yP);

        // Height and width are among {5, 7, 9}
        int height = (3 + RANDOM.nextInt(2)) * 2 - 1;
        int width = (3 + RANDOM.nextInt(2)) * 2 - 1;
        return new Room(height, width, p);
    }

    /** Remove overlapped rooms by check every room.
     * I've tried to sort rooms by x and check by order
     * but it has problems in vector y.
     */
    private static void removeOverlaps(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).isOverlap(rooms.get(j))
                 || rooms.get(j).isOverlap(rooms.get(i))) { // To get rid of one specific condition.
                    rooms.remove(j);
                    j--;
                }
            }
        }
    }

    /** Generate rooms and return rooms.*/
    public static List<Room> roomGenerator(Random RANDOM, TETile[][] world) {
        int numOfRooms = 100 + RANDOM.nextInt(50);
        List<Room> rooms = new LinkedList<>();
        for (int i = 0; i < numOfRooms; i++) {
            rooms.add(randomRoom(RANDOM, world));
        }
        removeOverlaps(rooms);
        for (Room r: rooms) {
            r.printRoom(world);
        }
        return rooms;
    }

    /** Create four random positions on room's edges. */
    private Position[] randomRoomPositions(Random RANDOM) {
        int widthRandom1 = 1 + RANDOM.nextInt(width - 3);
        int heightRandom1 = 1 + RANDOM.nextInt(height - 3);
        int widthRandom2 = -(1 + RANDOM.nextInt(width - 3));
        int heightRandom2 = -(1 + RANDOM.nextInt(height - 3));
        Position[] pArray = new Position[4];

        pArray[0] = new Position(position.x + widthRandom1, position.y);
        pArray[1] = new Position(position.x, position.y + heightRandom1);
        pArray[2] = new Position(position.x + width - 1 + widthRandom2, position.y + height - 1);
        pArray[3] = new Position(position.x + width - 1, position.y + height - 1 + heightRandom2);
        return pArray;
    }

    public void randomRemoveWalls(Random RANDOM, TETile[][] world) {
        Position[] randomPositions = randomRoomPositions(RANDOM);
        for (int i = 0; i < 4; i++) {
            int whichEdge = RANDOM.nextInt(4);
            if (!isOnEdge(randomPositions[whichEdge], world)
             && !isInDeadEnd(randomPositions[whichEdge], world)) {
                world[randomPositions[whichEdge].x][randomPositions[whichEdge].y] = Tileset.FLOOR;
            }
        }
    }
}
