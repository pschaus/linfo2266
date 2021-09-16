package dynamicprogramming;

public class KnapsackNaive {

    int [] value;
    int [] weight;
    int capa;

    public KnapsackNaive(int [] value, int [] weight, int capa) {
        this.value = value;
        this.weight = weight;
        this.capa = capa;
    }

    public int solve() {
        return opt(value.length-1, capa);
    }

    /**
     * Best solution on items [0..j] and max capa k
     */
    private int opt(int j, int k) {
        if (j < 0) return 0;
        else {
            if (weight[j] > k) return opt(j-1,k);
            else {
                int optWithout = opt(j-1,k);
                int optWith = value[j] + opt(j-1,k-weight[j]);
                return Math.max(optWithout, optWith);
            }
        }
    }



    public static void main(String[] args) {
        int [] value = new int [] {1,6,18,22,28};
        int [] weight = new int [] {2,3,5,6,7};

        KnapsackNaive kp = new KnapsackNaive(value,weight,12);
        int best = kp.solve();
        System.out.println("best objective:" + best);
        assert (best == 46);

    }
}
