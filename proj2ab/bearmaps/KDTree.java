package bearmaps;

import java.util.Arrays;
import java.util.List;

public class KDTree implements PointSet {

    private Node root;

    private static class Node {
        final Point point;
        Node left;
        Node right;
        final boolean xOrY;

        Node(Point point, boolean xOrY) {
            this.point = point;
            this.xOrY = xOrY;
        }

        Point getPoint() { return point; }
    }

    // iterative method
    private void insert(Point p) {
        if (root == null) {
            root = new Node(p, true);
            return;
        }
        Node cur = root;
        while (true) {
            boolean isGreat = (cur.xOrY)
                    ? p.getX() >= cur.getPoint().getX() : p.getY() >= cur.getPoint().getY();
            if (isGreat) {
                if (cur.right == null) {
                    cur.right = new Node(p, !cur.xOrY);
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    cur.left = new Node(p, !cur.xOrY);
                    break;
                } else {
                    cur = cur.left;
                }
            }
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            insert(p);
        }
    }

    private Point nearest(Node n, Point goal, Point best) {
        if (n == null) return best;
        if (Point.distance(n.getPoint(), goal) < Point.distance(best, goal))
            best = n.getPoint(); //如果发现更近的，替换
        boolean isGreat = (n.xOrY)
                ? goal.getX() >= n.getPoint().getX() : goal.getY() >= n.getPoint().getY();
        Node goodSide = isGreat ? n.right : n.left;
        Node badSide = isGreat ? n.left : n.right;

        best = nearest(goodSide, goal, best);
        Point bestBadSidePoint = (n.xOrY)
                ? new Point(n.getPoint().getX(), goal.getY())
                : new Point(goal.getX(), n.getPoint().getY());
        if (Point.distance(best, goal) > Point.distance(bestBadSidePoint, goal))
            best = nearest(badSide, goal, best);
        return best;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root.getPoint());
    }

    private void printInOrder(Node n) {
        if (n == null) return;
        printInOrder(n.left);
        System.out.println(n.getPoint().toString());
        printInOrder(n.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(1, 5); // constructs a Point with x = 1.1, y = 2.2
        Point p5 = new Point(3, 3);
        Point p6 = new Point(4, 4);

        KDTree nn = new KDTree(Arrays.asList(p1, p2, p3, p4, p5, p6));
        nn.printInOrder();
        /*Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4*/
    }
}
