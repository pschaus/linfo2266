package branchandbound;

import util.InputReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BranchAndBoundKnapsack {
    public static void main(String[] args) {

        int[] value = new int[]{1, 6, 18, 22, 28};
        int[] weight = new int[]{2, 3, 5, 6, 7};
        int capa = 11;

        OpenNodes<NodeKnapsack> openNodes = new BestFirstOpenNodes<>();
        NodeKnapsack root = new NodeKnapsack(null,value,weight,0,capa,-1,false);
        openNodes.add(root);
        BranchAndBound.minimize(openNodes);


    }
}

class NodeKnapsack implements Node {

    int[] value;
    int[] weight;
    int selectedValue;
    int capaLeft;
    int index;
    boolean selected;
    NodeKnapsack parent;


    // Node where item at index is selected the ones after are undecided
    public NodeKnapsack(NodeKnapsack parent, int[] value, int[] weight, 
                        int selectedValue, int capaLeft, int index, boolean selected) {
        this.parent = parent;
        this.value = value;
        this.weight = weight;
        this.selectedValue = selectedValue;
        this.capaLeft = capaLeft;
        this.index = index;
        this.selected = selected;
    }

    @Override
    public double lowerBound() {
        int valueUb = selectedValue;
        for (int i = index + 1; i < value.length; i++) {
            valueUb += value[i];
        }
        System.out.println(valueUb);
        return -valueUb; // maximization problem
    }

    @Override
    public boolean isFeasible() {
        return index == value.length - 1;
    }

    @Override
    public List<Node> children() {

        List<Node> children = new ArrayList<>();
        if (capaLeft >= weight[index+1]) {
            // select item at index+1
            Node left = new NodeKnapsack(this, value, weight,
                    selectedValue + value[index + 1],
                    capaLeft - weight[index + 1],
                    index + 1, true);
            children.add(left);
        }

        // do not select item at index+1
        Node right = new NodeKnapsack(this, value, weight,
                selectedValue,
                capaLeft,
                index + 1, false);
        children.add(right);
        return children;
    }

    @Override
    public String toString() {
        ArrayList<Integer> selected = new ArrayList<>();
        NodeKnapsack current = this;
        while (current.index != -1) {
            if (current.selected) {
                selected.add(current.index);
            }
            current = current.parent;
        }
        return selected.toString();
    }
}


