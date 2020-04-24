package bearmaps;

import java.util.Arrays;
import java.util.List;

public class NaivePointSet implements PointSet {

    private final List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point nearestPoint = points.get(0), des = new Point(x, y);
        double distance, minDistance = Point.distance(nearestPoint, des);
        for (Point p: points) {
            distance = Point.distance(p, des);
            if (distance < minDistance) {
                nearestPoint = p;
                minDistance = distance;
            }
        }
        return nearestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(Arrays.asList(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
