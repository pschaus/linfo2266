package uclouvain.linfo2266.localsearch.minils.examples;

import static uclouvain.linfo2266.localsearch.minils.Factory.*;

import uclouvain.linfo2266.localsearch.minils.engine.core.Constraint;
import uclouvain.linfo2266.localsearch.minils.engine.core.IntVarLS;
import uclouvain.linfo2266.localsearch.minils.engine.core.SolverLS;
import uclouvain.linfo2266.localsearch.minils.engine.constraints.AllDifferent;


import java.util.Random;

public class NQueens {

    public static void main(String[] args) {
        int n = 8;
        SolverLS ls = makeSolver();

        IntVarLS[] q = makeIntVarArray(n, i -> makeIntVar(ls,i));

        for (int i = 0; i < n; i++) {
            System.out.print(q[i].value()+",");
        }
        System.out.print("\n");


        Constraint allDiffCol = new AllDifferent(q);
        Constraint allDiffDiagLeft = new AllDifferent(makeIntVarArray(n,i -> plus(q[i],i)));
        Constraint allDiffDiagRight = new AllDifferent(makeIntVarArray(n,i -> plus(q[i],-i)));

        IntVarLS violation = sum(allDiffCol.violation(),
                allDiffDiagLeft.violation(),
                allDiffDiagRight.violation());

        System.out.println(violation.value());


        Random rand = new Random();
        int best = violation.value();

        int iter = 0;
        while (violation.value() > 0) {
            iter++;

            if (violation.value() == 0) {
                break;
            }

            if (iter % 10 == 0) {
                for (int k = 0; k < n; k++) {
                    int i = rand.nextInt(n);
                    int j = rand.nextInt(n);
                    int vi = q[i].value();
                    int vj = q[j].value();
                    q[i].setValue(vj);
                    q[j].setValue(vi);
                }
            }
            int bestSwap = 0;
            int bestViol = violation.value();
            int besti = 0;
            int bestj = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int vi = q[i].value();
                    int vj = q[j].value();
                    q[i].setValue(vj);
                    q[j].setValue(vi);
                    if (violation.value() < bestViol) {
                        besti = i;
                        bestj = j;
                        bestViol = violation.value();
                    }
                    q[i].setValue(vi);
                    q[j].setValue(vj);
                }
            }

            int vi = q[besti].value();
            int vj = q[bestj].value();
            q[besti].setValue(vj);
            q[bestj].setValue(vi);

            if (violation.value() < best) {
                best = violation.value();
                System.out.println("best:"+violation.value());
                for (int i = 0; i < n; i++) {
                    System.out.print(q[i].value()+",");
                }
                System.out.print("\n");
            }


        }

        System.out.println("violation:"+violation.value());
        for (int i = 0; i < n; i++) {
            System.out.println("\t".repeat(q[i].value())+"x");
            //System.out.println("---".repeat(n));
        }



    }
}
