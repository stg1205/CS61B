public class BubbleGrid {
    
    private int[][] grid;
    private UnionFind unionGrid;
    private int R;
    private int C;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    /** 两个helper方法 */
    private int cvt2dArray(int row, int col) {
        return row * C + col;
    }
    /* 连接主方向上四个点 */
    private void orthogonallyUnion(int[][] grid, int row, int col) {
        for (int i = 0; i < 4; i++) {
            if ((row + dx[i] >= 0 && row + dx[i] < R) && (col + dy[i] >= 0 && col + dy[i] < C)) { //保证不超出grid范围
                if (grid[row + dx[i]][col + dy[i]] == 1) { //如果主方向某点是1，才连接
                    unionGrid.union(cvt2dArray(row, col), cvt2dArray(row + dx[i], col + dy[i]));
                }
            }
        }
    }
    
    /** 在去掉所有darts点后，初始化UnionFind，遍历所有grid */
    private void gridToUnionFind(int[][] grid) {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (grid[row][col] == 1) {
                    if (row == 0) {
                        unionGrid.union(R * C, col);
                    } else {
                        orthogonallyUnion(grid, row, col); //其实不用连接四个方向
                    }
                }
            }
        }
    }

    /** 构造函数，把grid的长宽保存，方便一点 */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        unionGrid = new UnionFind(R * C + 1); //单独建一个R * C节点，所有的顶部泡泡都与他相连，方便统计
    }

    /**
     * @param darts: darts is an array of 2-element arrays representing the grid positions
     * (in [row, column] format) at which darts are thrown in sequence
     * (i.e. a dart is thrown at position darts[t] at time t).
     * You may assume all elements of darts are unique, valid positions within the grid.
     * @return: an array where the i-th element is the number of bubbles that fall
     * after the i-th dart is thrown (popped bubbles do not fall!).
     */
    public int[] popBubbles(int[][] darts) {
        int[] count = new int[darts.length];
        int[][] gridCopy = new int[R][C];

        // 拷贝一个新数组，把pop的泡泡变0, 初始化UnionFind
        for (int i = 0; i < R; i++) {
            gridCopy[i] = grid[i].clone();
        }
        for (int[] dart: darts) {
            gridCopy[dart[0]][dart[1]] = 0;
        }
        gridToUnionFind(gridCopy);
        // 初始与顶部相连的泡泡数量
        int connectToCeiling = unionGrid.sizeOf(R * C);

        // 每加一个泡泡，统计增加了多少泡泡与顶部相连
        for (int i = darts.length - 1; i >= 0; i--) {
            int r = darts[i][0];
            int c = darts[i][1];
            if (grid[r][c] != 0) { //如果dart之前也没有泡泡，dart就没用，count = 0；如果有，就将该点设为1
                gridCopy[r][c] = 1;
                if (r == 0) { //如果该点是顶部，还要与R * C相连
                    unionGrid.union(R * C, cvt2dArray(r, c));
                }
                orthogonallyUnion(gridCopy, r, c); //与该点主方向四个点相连
                int cur = unionGrid.sizeOf(R * C); //计算现在与顶部相连的泡泡数量
                int inc = cur - connectToCeiling; //计算增加的与顶部相连的泡泡数量
                if (inc != 0) { //不算pop的泡泡
                    count[i] = inc - 1;
                } else {
                    count[i] = 0;
                }
                connectToCeiling = cur; //更新
            } else {
                count[i] = 0;
            }
        }
        return count;
    }
}
