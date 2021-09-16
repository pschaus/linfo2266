package dynamicprogramming;

import java.util.Arrays;

public class KnapsacTable {

    int [] value;
    int [] weight;
    int capa;

    int [][] table;

    public KnapsacTable(int [] value, int [] weight, int capa) {
        this.value = value;
        this.weight = weight;
        this.capa = capa;

        // table[k][i] = best solution on items [0..i] and max capa k
        table = new int[capa+1][this.value.length];
    }

    public int solve() {
        for (int k = weight[0]; k <= capa; k++) {
            table[k][0] = value[0];
        }
        for (int i = 1; i < value.length; i++) {
            for (int k = 0; k < weight[i]; k++) {
                table[k][i] = table[k][i-1];
            }
            for (int k = weight[i]; k <= capa; k++) {
                int optWithout = table[k][i-1];
                int optWith = value[i] + table[k-weight[i]][i-1];
                table[k][i] = Math.max(optWith, optWithout);
            }
        }
        return table[capa][value.length-1];
    }

    public static void main(String[] args) {
        int [] value = new int [] {1,6,18,22,28};
        int [] weight = new int [] {1,2,5,6,7};

        KnapsacTable kp = new KnapsacTable(value,weight,12);
        int best = kp.solve();
        System.out.println("best objective:" + best);
        assert (best == 46);

    }

}
