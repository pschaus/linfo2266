package uclouvain.linfo2266.branchandbound;

import uclouvain.linfo2266.util.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchAndBoundKnapsack {
    public static void main(String[] args) {

        int[] value = new int[]{1, 6, 18, 22, 28};
        int[] weight = new int[]{2, 3, 5, 6, 7};
        int capa = 11;
        int n = value.length;


        String instance = "data/knapsack/knapsackA";
        InputReader reader = new InputReader(instance);
        n = reader.getInt();
        capa = reader.getInt();
        value = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = reader.getInt();
            weight[i] = reader.getInt();
        }

        // sort decreasing according to value/weight ratio
        /*
        sortValueOverWeight(value,weight);
        for (int i = 0; i < n-1; i++) {
            assert ((double) value[i]/weight[i] > (double) value[i-1]/weight[i+1]);
        }*/


        //OpenNodes<NodeKnapsack> openNodes = new BestFirstOpenNodes<>();
        OpenNodes<NodeKnapsack> openNodes = new DepthFirstOpenNodes<>();

        NodeKnapsack root = new NodeKnapsack(null,value,weight,0,capa,-1,false);
        openNodes.add(root);
        BranchAndBound.minimize(openNodes);


    }

    private static void sortValueOverWeight(int [] value, int [] weight) {
        int n = value.length;
        double [][] item = new double[n][2];
        for (int i = 0; i < n; i++) {
            item[i][0] = value[i];
            item[i][1] = weight[i];
        }
        Arrays.sort(item,(i1,i2)-> (i1[0]/i1[1] > i2[0]/i2[1]) ? -1 : 1 );
        for (int i = 0; i < n; i++) {
            value[i] = (int) item[i][0];
            weight[i] = (int) item[i][1];
        }
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
    double ub;


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
        this.ub = capacityRelaxUBound();
        //this.ub = lpRelaxUBound();
    }

    private double capacityRelaxUBound() {
        int valueUb = selectedValue;
        for (int i = index + 1; i < value.length; i++) {
            valueUb += value[i];
        }
        return valueUb; // maximization problem
    }

    private double lpRelaxUBound() {
        int valueUb = selectedValue;
        int c = capaLeft;
        for (int i = index + 1; i < value.length; i++) {
            if (weight[i] < c) {
                valueUb += value[i];
                c -= weight[i];
            } else {
                valueUb += ((double) c)/weight[i] * value[i];
                break;
            }
        }
        return valueUb; // maximization problem
    }

    @Override
    public double lowerBound() {
        return -ub;
    }

    @Override
    public boolean isFeasible() {
        return index == value.length - 1;
    }

    @Override
    public List<Node> children() {

        List<Node> children = new ArrayList<>();
        // do not select item at index+1
        Node right = new NodeKnapsack(this, value, weight,
                selectedValue,
                capaLeft,
                index + 1, false);
        children.add(right);
        if (capaLeft >= weight[index+1]) {
            // select item at index+1
            Node left = new NodeKnapsack(this, value, weight,
                    selectedValue + value[index + 1],
                    capaLeft - weight[index + 1],
                    index + 1, true);
            children.add(left);
        }
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


