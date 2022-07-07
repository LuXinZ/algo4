/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] arr;
    private int experimentsCount;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        experimentsCount = trials;
        arr = new double[trials];
        for (int i = 0; i < trials; i++) {
            int number = 0;
            Percolation pe = new Percolation(n);
            while (!pe.percolates()) {
                int s1 = StdRandom.uniform(1, n + 1);
                int s2 = StdRandom.uniform(1, n + 1);

                if (!pe.isOpen(s1, s2)) {
                    pe.open(s1, s2);
                    number += 1;
                }
            }
            arr[i] = (double) number / (n * n);
        }


    }

    public double mean() {
        return StdStats.mean(arr);
    }

    public double stddev() {
        return StdStats.stddev(arr);
    }

    public double confidenceLo() {

        return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));

    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, T);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
}
