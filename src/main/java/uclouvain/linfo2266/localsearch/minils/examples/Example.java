package uclouvain.linfo2266.localsearch.minils.examples;

import uclouvain.linfo2266.localsearch.minils.engine.core.IntVarLS;
import uclouvain.linfo2266.localsearch.minils.engine.core.SolverLS;

import static uclouvain.linfo2266.localsearch.minils.Factory.*;

public class Example {

    public static void main(String[] args) {
        SolverLS ls = makeSolver();

        IntVarLS x1 = makeIntVar(ls,1);
        IntVarLS x2 = makeIntVar(ls,2);
        IntVarLS x3 = makeIntVar(ls,3);

        IntVarLS y = sum(x1,x2,x3);

        IntVarLS x4 = makeIntVar(ls,4);
        IntVarLS x5 = makeIntVar(ls,5);

        IntVarLS z = sum(y,x4,x5);


        System.out.println(z.value());

        x1.setValue(2);

        System.out.println(z.value());


    }
}
