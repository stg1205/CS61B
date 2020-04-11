
import static org.junit.Assert.*;
import org.junit.*;

public class UnionFindTest {

    @Test
    public void testComponentSize() {

        UnionFind uf = new UnionFind(5);
        assertEquals(uf.sizeOf(0), 1);
        assertEquals(uf.sizeOf(1), 1);
        assertEquals(uf.sizeOf(2), 1);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(0, 1);
        assertEquals(uf.sizeOf(0), 2);
        assertEquals(uf.sizeOf(1), 2);
        assertEquals(uf.sizeOf(2), 1);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(1, 0);
        assertEquals(uf.sizeOf(0), 2);
        assertEquals(uf.sizeOf(1), 2);
        assertEquals(uf.sizeOf(2), 1);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(1, 2);
        assertEquals(uf.sizeOf(0), 3);
        assertEquals(uf.sizeOf(1), 3);
        assertEquals(uf.sizeOf(2), 3);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(0, 2);
        assertEquals(uf.sizeOf(0), 3);
        assertEquals(uf.sizeOf(1), 3);
        assertEquals(uf.sizeOf(2), 3);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(2, 1);
        assertEquals(uf.sizeOf(0), 3);
        assertEquals(uf.sizeOf(1), 3);
        assertEquals(uf.sizeOf(2), 3);
        assertEquals(uf.sizeOf(3), 1);
        assertEquals(uf.sizeOf(4), 1);

        uf.union(3, 4);
        assertEquals(uf.sizeOf(0), 3);
        assertEquals(uf.sizeOf(1), 3);
        assertEquals(uf.sizeOf(2), 3);
        assertEquals(uf.sizeOf(3), 2);
        assertEquals(uf.sizeOf(4), 2);

        uf.union(4, 3);
        assertEquals(uf.sizeOf(0), 3);
        assertEquals(uf.sizeOf(1), 3);
        assertEquals(uf.sizeOf(2), 3);
        assertEquals(uf.sizeOf(3), 2);
        assertEquals(uf.sizeOf(4), 2);

        uf.union(1, 3);
        assertEquals(uf.sizeOf(0), 5);
        assertEquals(uf.sizeOf(1), 5);
        assertEquals(uf.sizeOf(2), 5);
        assertEquals(uf.sizeOf(3), 5);
        assertEquals(uf.sizeOf(4), 5);

        uf.union(4, 0);
        assertEquals(uf.sizeOf(0), 5);
        assertEquals(uf.sizeOf(1), 5);
        assertEquals(uf.sizeOf(2), 5);
        assertEquals(uf.sizeOf(3), 5);
        assertEquals(uf.sizeOf(4), 5);
    }

    @Test
    public void testConnectivity() {

        int sz = 7;
        UnionFind uf = new UnionFind(sz);

        for (int i = 0; i < sz; i++) assertTrue(uf.connected(i, i));

        uf.union(0, 2);

        assertTrue(uf.connected(0, 2));
        assertTrue(uf.connected(2, 0));

        assertFalse(uf.connected(0, 1));
        assertFalse(uf.connected(3, 1));
        assertFalse(uf.connected(6, 4));
        assertFalse(uf.connected(5, 0));

        for (int i = 0; i < sz; i++) assertTrue(uf.connected(i, i));

        uf.union(3, 1);

        assertTrue(uf.connected(0, 2));
        assertTrue(uf.connected(2, 0));
        assertTrue(uf.connected(1, 3));
        assertTrue(uf.connected(3, 1));

        assertFalse(uf.connected(0, 1));
        assertFalse(uf.connected(1, 2));
        assertFalse(uf.connected(2, 3));
        assertFalse(uf.connected(1, 0));
        assertFalse(uf.connected(2, 1));
        assertFalse(uf.connected(3, 2));

        assertFalse(uf.connected(1, 4));
        assertFalse(uf.connected(2, 5));
        assertFalse(uf.connected(3, 6));

        for (int i = 0; i < sz; i++) assertTrue(uf.connected(i, i));

        uf.union(2, 5);
        assertTrue(uf.connected(0, 2));
        assertTrue(uf.connected(2, 0));
        assertTrue(uf.connected(1, 3));
        assertTrue(uf.connected(3, 1));
        assertTrue(uf.connected(0, 5));
        assertTrue(uf.connected(5, 0));
        assertTrue(uf.connected(5, 2));
        assertTrue(uf.connected(2, 5));

        assertFalse(uf.connected(0, 1));
        assertFalse(uf.connected(1, 2));
        assertFalse(uf.connected(2, 3));
        assertFalse(uf.connected(1, 0));
        assertFalse(uf.connected(2, 1));
        assertFalse(uf.connected(3, 2));

        assertFalse(uf.connected(4, 6));
        assertFalse(uf.connected(4, 5));
        assertFalse(uf.connected(1, 6));

        for (int i = 0; i < sz; i++) assertTrue(uf.connected(i, i));

        // Connect everything
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(4, 6);

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                assertTrue(uf.connected(i, j));
            }
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testBadUnionFindCreation1() {
        new UnionFind(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadUnionFindCreation2() {
        new UnionFind(-3463);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadUnionFindCreation3() {
        new UnionFind(0);
    }
}
