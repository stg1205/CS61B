package bearmaps;

import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    private NaivePointSet naivePointSet;
    private KDTree tree;

    private void buildRandomTree() {
        List<Point> testList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            testList.add(new Point(x, y));
        }
        naivePointSet = new NaivePointSet(testList);
        tree = new KDTree(testList);
    }

    @Test
    public void testNearest() {
        buildRandomTree();
        for (int i = 0; i < 100; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            Point goal = new Point(x, y);
            assertEquals(Point.distance(naivePointSet.nearest(x, y), goal),
                         Point.distance(tree.nearest(x, y), goal), 0.001);
        }
    }

    @Test
    public void testEfficiency() {
        buildRandomTree();
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            points.add(new Point(StdRandom.uniform(100), StdRandom.uniform(100)));
        }
        long start = System.currentTimeMillis();
        for (Point p: points) {
            naivePointSet.nearest(p.getX(), p.getY());
        }
        long end = System.currentTimeMillis();
        System.out.println("The time cost of NaivePointSet is: "
                + (end - start) / 1000.0 + " seconds.");

        long start2 = System.currentTimeMillis();
        for (Point p: points) {
            tree.nearest(p.getX(), p.getY());
        }
        long end2 = System.currentTimeMillis();
        System.out.println("The time cost of KDTree is: "
                + (end2 - start2) / 1000.0 + " seconds.");
    }
}
