package uclouvain.linfo2266.dynamicprogramming.knapsack;

// https://github.com/pschaus/linfo2266

import uclouvain.linfo2266.util.InputReader;

public class KnapsackNaive {

    int [] value;
    int [] weight;
    int capa;

    int calls = 0;

    public KnapsackNaive(int [] value, int [] weight, int capa) {
        this.value = value;
        this.weight = weight;
        this.capa = capa;
    }

    public int solve() {
        return opt(value.length-1 /*n*/, capa /*C*/);
    }

    /**
     * Best solution on items [0..j] and max capa k
     */
    private int opt(int j, int k) {
        calls++;
        if (j < 0) return 0;
        else {
            if (weight[j] > k) return opt(j-1,k);
            else {
                int optWithout = opt(j-1,k); // don't take item j
                int optWith = value[j] + opt(j-1,k-weight[j]); // take item j
                return Math.max(optWithout, optWith);
            }
        }
    }



    public static void main(String[] args) {
        int [] value = new int [] {1,6,18,22,28};
        int [] weight = new int [] {1,2,5,6,7};
        int capa = 11;

        String instance = "data/knapsack/knapsackA";
        InputReader reader = new InputReader(instance);
        int n = reader.getInt();
        capa = reader.getInt();
        value = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = reader.getInt();
            weight[i] = reader.getInt();
        }

        KnapsackNaive kp = new KnapsackNaive(value,weight,capa);
        int best = kp.solve();
        System.out.println("best objective:" + best);
        System.out.println("nRecur: "+kp.calls);
        assert (best == 40);

    }
}
