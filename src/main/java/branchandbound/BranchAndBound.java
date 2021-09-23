package branchandbound;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BranchAndBound {


    public static void minimize(OpenNodes openNode) {

        double best = Double.MAX_VALUE;

        while (!openNode.isEmpty()) {
            Node n = openNode.remove();
            if (n.isFeasible() && n.lowerBound() < best) {
                best = n.lowerBound();
                System.out.println("new best:"+best+" node:"+n);
            }
            else if (n.lowerBound() < best) {
                for (Node child: n.children()) {
                    openNode.add(child);
                }
            }
        }
    }
}

interface Node {
    double lowerBound();
    boolean isFeasible();
    List<Node> children();
}

interface OpenNodes<N extends Node> {
    void add(N n);
    N remove();
    boolean isEmpty();
}

class BestFirstOpenNodes<N extends  Node> implements OpenNodes<N> {

    PriorityQueue<N> queue;

    BestFirstOpenNodes() {
        queue = new PriorityQueue<N>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                double lb1 = o1.lowerBound();
                double lb2 = o2.lowerBound();
                if (lb1 < lb2) {
                    return -1;
                } else if (lb1 == lb2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public void add(N n) {
        queue.add(n);
    }

    public N remove() {
        return queue.remove();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}






