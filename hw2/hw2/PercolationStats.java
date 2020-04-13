package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {

    int T;
    double[] x;
    Percolation test;

    /* perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        test = pf.make(N);
        this.T = T;
        x = new double[T];

        for (int i = 0; i < T; i++) {
            while (!test.percolates()) {
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                test.open(randomRow, randomCol);
            }
            x[i] = (double) test.numberOfOpenSites() / (N * N);
            test = pf.make(N);
        }
    }

    /* sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(x);
    }

    /* sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(x);
    }

    /* low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /* high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        Stopwatch timer = new Stopwatch();
        int N = 200, T = 100;
        PercolationStats test = new PercolationStats(N, T, pf);
        System.out.println("N: " + N + "\nT: " + T + "\ncost " + timer.elapsedTime() + " seconds");
    }
}
