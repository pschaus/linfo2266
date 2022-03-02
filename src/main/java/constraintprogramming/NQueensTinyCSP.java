package constraintprogramming;

import java.util.ArrayList;

public class NQueensTinyCSP {
    public static void main(String[] args) {

        int n = 14;
        TinyCSP csp = new TinyCSP();
        TinyCSP.Variable[] q = new TinyCSP.Variable[n];

        for (int i = 0; i < n; i++) {
            q[i] = csp.makeVariable(n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // queens q[i] and q[i] not on ...
                csp.notEqual(q[i],q[j],0); // ... the same line
                csp.notEqual(q[i],q[j],i-j); // ... the same left diagonal
                csp.notEqual(q[i],q[j],j-i); // ... the same right diagonal
            }
        }

        ArrayList<int []> solutions = new ArrayList<>();

        // collect all the solutions
        long t0 = System.currentTimeMillis();
        csp.dfs(solutions);
        long t1 = System.currentTimeMillis();

        System.out.println("# solutions: " + solutions.size());
        System.out.println("# recurs: " + csp.nRecur);
        System.out.println("# time(ms): " + (t1-t0));


    }
}
