/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF qu;
    private int[][] a;
    private int[] b;
    private int openSite = 0;

    public Percolation(int n) {
        qu = new WeightedQuickUnionUF(n * n);
        b = new int[n * n];
        a = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            b[i] = 0;
        }
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                a[i][j] = 0;
            }
        }
    }

    private void twoDArraytoOne(int[][] twoD) {

        for (int row = 0; row < twoD.length; row++) {
            for (int col = 0; col < twoD.length; col++) {
                b[(row * twoD.length) + col] = twoD[row][col];
            }
        }

    }

    // search row and col in one array
    private int searchInArray(int row, int col, int length) {
        return row * length + col;
    }

    public void open(int row, int col) {
        a[row - 1][col - 1] = 1;
        openSite += 1;
        twoDArraytoOne(a);
        int currentInArray = searchInArray(row - 1, col - 1, a.length);
        int up = row - 1;
        int down = row + 1;
        int left = col - 1;
        int right = col + 1;
        if (up > 0 && isOpen(up, col)) {
            int otherInArray = searchInArray(up - 1, col - 1, a.length);
            qu.union(currentInArray, otherInArray);
        }
        if (down <= a.length && isOpen(down, col)) {
            int otherInArray = searchInArray(down - 1, col - 1, a.length);
            qu.union(currentInArray, otherInArray);
        }
        if (left > 0 && isOpen(row, left)) {
            int otherInArray = searchInArray(row - 1, left - 1, a.length);
            qu.union(currentInArray, otherInArray);
        }
        if (right <= a.length && isOpen(row, right)) {
            int otherInArray = searchInArray(row - 1, right - 1, a.length);
            qu.union(currentInArray, otherInArray);
        }
        // union

    }

    public boolean isOpen(int row, int col) {
        if (a[row - 1][col - 1] == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFull(int row, int col) {
        if (row == 1 && isOpen(row, col)) {
            return true;
        }
        int currentInArray = searchInArray(row - 1, col - 1, a.length);
        int z = qu.find(currentInArray);
        for (int i = 0; i < a.length; i++) {
            int x = qu.find(i);
            if (z == x && isOpen(row, col)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        for (int i = 0; i < a.length; i++) {
            if (a[a.length - 1][i] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
