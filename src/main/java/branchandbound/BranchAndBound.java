package branchandbound;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public abstract class BranchAndBound<N extends  Node> {


    PriorityQueue<N> queue;

    public BranchAndBound(N root) {
        queue = new PriorityQueue<N>(new Comparator<N>() {
            @Override
            public int compare(N o1, N o2) {
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
        queue.add(root);
    }

    abstract List<N> children(N node ,double best);

    public void updateBest(N node) {

    }


    public void minimize() {
        double best = Double.MAX_VALUE;

        while (!queue.isEmpty()) {
            N n = queue.poll();
            if (n.isFeasible()) {
                best = n.lowerBound();
            }
            else if (n.lowerBound() < best) {
                queue.addAll(children(n,best));
            }

        }
    }




}

abstract class Node<T extends Node<T>> {

    abstract double lowerBound();
    abstract boolean isFeasible();

}




