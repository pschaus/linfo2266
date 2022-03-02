package constraintprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueensPrune {

    int [] q;
    int n = 0;

    static int nRecur = 0;

    public NQueensPrune(int n) {
        this.n = n;
        q = new int[n];
    }

    public void dfs(int idx, ArrayList<int[]> solutions) {
        nRecur++;
        if (idx == n) {
            solutions.add(Arrays.copyOf(q, n));
        } else {
            for (int i = 0; i < n; i++) {
                q[idx] = i;
                if (constraintsSatisfied(idx))
                    dfs(idx + 1, solutions);
            }
        }
    }

    public boolean constraintsSatisfied(int j) {
        for (int i = 0; i < j; i++) {
            // no two queens on the same row
            if (q[i] == q[j]) return false;
            // no two queens on the diagonal
            if (Math.abs(q[j] - q[i]) == j - i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueensPrune q = new NQueensPrune(14);
        ArrayList<int []> solutions = new ArrayList<>();
        // collect all the solutions
        long t0 = System.currentTimeMillis();
        q.dfs(0, solutions);
        long t1 = System.currentTimeMillis();
        System.out.println("# solutions: " + solutions.size());
        System.out.println("# recurs: " + nRecur);
        System.out.println("# time(ms): " + (t1-t0));

    }
}
