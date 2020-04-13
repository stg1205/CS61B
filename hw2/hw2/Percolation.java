package hw2;

//import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int[][] grid;
    private final int N;
    private final int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private WeightedQuickUnionUF unionGrid;
    private WeightedQuickUnionUF unionGrid2;
    //private QuickUnionUF unionGrid;
    private int numberOfOpenSites;

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    /* union a point with points that are orthogonally adjacent to it */
    private void orthogonallyUnion(WeightedQuickUnionUF ug, int row, int col) {
        for (int i = 0; i < 4; i++) {
            if ((row + dx[i] >= 0 && row + dx[i] < N)
             && (col + dy[i] >= 0 && col + dy[i] < N)) {
                if (grid[row + dx[i]][col + dy[i]] == 1) {
                    ug.union(xyTo1D(row, col),
                                    xyTo1D(row + dx[i], col + dy[i]));
                }
            }
        }
    }

    private void validate(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
    }
    /* create N-by-N grid, with all sites initially blocked。 */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        unionGrid = new WeightedQuickUnionUF(N * N + 2);
        unionGrid2 = new WeightedQuickUnionUF(N * N + 1);
        //unionGrid = new QuickUnionUF(N * N + 2);
        this.N = N;
        numberOfOpenSites = 0;
    }

    /* open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        validate(row, col);
        if (grid[row][col] != 1) {
            grid[row][col] = 1;
            if (row == 0) {
                unionGrid.union(N * N, xyTo1D(row, col));
                unionGrid2.union(N * N, xyTo1D(row, col));
            }
            if (row == N - 1) {
                unionGrid.union(N * N + 1, xyTo1D(row, col));
            }
            orthogonallyUnion(unionGrid, row, col);
            orthogonallyUnion(unionGrid2, row, col);
            numberOfOpenSites++;
        }
    }

    /* is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == 1;
    }

    /* is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return unionGrid2.connected(xyTo1D(row, col), N * N);
    }

    /* number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /* does the system percolate? */
    public boolean percolates() {
        return unionGrid.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) {

    }
}
