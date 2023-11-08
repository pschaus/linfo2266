package uclouvain.linfo2266.branchandbound;

import java.util.*;

public class BranchAndBound {


    public static void minimize(OpenNodes openNode) {

        double upperBound = Double.MAX_VALUE;
        int iter = 0;

        while (!openNode.isEmpty()) {
            iter++;
            Node n = openNode.remove();
            if (n.isFeasible() && n.lowerBound() < upperBound) {
                upperBound = n.lowerBound();
                System.out.println("new best:"+upperBound+" node:"+n);
            }
            else if (n.lowerBound() < upperBound) {
                for (Node child: n.children()) {
                    openNode.add(child);
                }
            }
        }
        System.out.println("#iter:"+iter);
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
    int size();
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

    @Override
    public int size() {
        return queue.size();
    }
}

class DepthFirstOpenNodes<N extends  Node> implements OpenNodes<N> {

    Stack<N> stack;

    DepthFirstOpenNodes() {
        stack = new Stack<N>();
    }

    public void add(N n) {
        stack.push(n);
    }

    public N remove() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}






