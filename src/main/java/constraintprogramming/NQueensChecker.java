package constraintprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueensChecker {

    int [] q;
    int n = 0;

    static int nRecur = 0;

    public NQueensChecker(int n) {
        this.n = n;
        q = new int[n];
    }

    public void dfs(int idx, ArrayList<int []> solutions) {
        nRecur++;
        if (idx == n) {
            if (constraintsSatisfied()) {
                System.out.println(Arrays.toString(q));
                solutions.add(Arrays.copyOf(q,n));
            }
        } else {
            for (int i = 0; i < n; i++) {
                q[idx] = i;
                dfs(idx+1, solutions);
            }
        }
    }

    public boolean constraintsSatisfied() {
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // no two queens on the same row
                if (q[i] == q[j]) return false;
                // no two queens on the diagonal
                if (Math.abs(q[j] - q[i]) == j-i) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueensChecker q = new NQueensChecker(8);
        ArrayList<int []> solutions = new ArrayList<>();
        q.dfs(0, solutions);
        System.out.println("# solutions: " + solutions.size());
        System.out.println("# recurs: " + nRecur);
    }
}
