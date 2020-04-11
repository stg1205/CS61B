public class UnionFind {

    private int[] parent;

    /** Creates a UnionFind data structure holding n vertices.
     * Initially, all vertices are in disjoint sets.
     */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("It must be positive!");
        }
        parent =  new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /** Throws an exception if v1 is not a valid index. */
    public void validate(int v1) {
        if (!(v1 >= 0 && v1 < parent.length)) {
            throw new RuntimeException(v1 + " is not a valid index.");
        }
    }

    /** Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -parent[find(v1)];
    }

    /** Returns the parent of v1.
     * If v1 is the root of a tree, returns the negative size of the tree for which v1 is the root.
     */
    public int parent(int v1) {
        return parent[v1];
    }

    /** Find the root of v. */
    public int find(int v) {
        int r = v;
        int temp;

        while (parent[r] >= 0) {
            r = parent[r];
        }
        // Path compression.
        while (parent[v] >= 0) {
            temp = parent[v];
            parent[v] = r;
            v = temp;
        }
        return r;
    }

    /** Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /** Connects two elements v1 and v2 together.
     * v1 and v2 can be any valid elements, and a union-by-size heuristic is used.
     * If the sizes of the sets are equal, tie break by connecting v1’s root to v2’s root.
     * Unioning a vertex with itself or vertices that are already connected should not change the sets,
     * but it may alter the internal structure of the data structure.
     */
    public void union(int v1, int v2) {
        if (!connected(v1, v2)) {
            int r1 = find(v1);
            int r2 = find(v2);
            int size1 = -parent[r1];
            int size2 = -parent[r2];

            if (size1 >= size2) {
                parent[r2] = r1;
                parent[r1] = -(size1 + size2);
            } else {
                parent[r1] = r2;
                parent[r2] = -(size1 + size2);
            }
        }
    }

}
