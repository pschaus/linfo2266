package dynamicprogramming;

import util.Pair;

import java.util.HashMap;

public class KnapsackHash {

    int [] value;
    int [] weight;
    int capa;

    HashMap<Pair<Integer,Integer>, Integer> cache;

    public KnapsackHash(int [] value, int [] weight, int capa) {
        this.value = value;
        this.weight = weight;
        this.capa = capa;
        cache = new HashMap<>();
    }

    public int solve() {
        return optCache(value.length-1, capa);
    }

    /**
     * Best solution on items [0..j] and max capa k
     */
    private int optCache(int j, int k) {
        Pair entry = new Pair<>(j,k);
        if (cache.containsKey(entry)) {
            return cache.get(entry);
        } else {
            int res = opt(j,k);
            cache.put(new Pair<>(j,k),res);
            return res;
        }
    }

    /**
     * Best solution on items [0..j] and max capa k
     */
    private int opt(int j, int k) {
        if (j < 0) return 0;
        else {
            if (weight[j] > k) return optCache(j-1,k);
            else {
                int optWithout = optCache(j-1,k);
                int optWith = value[j] + optCache(j-1,k-weight[j]);
                return Math.max(optWithout, optWith);
            }
        }
    }



    public static void main(String[] args) {
        int [] value = new int [] {1,6,18,22,28};
        int [] weight = new int [] {1,2,5,6,7};

        KnapsackHash kp = new KnapsackHash(value,weight,12);
        int best = kp.solve();
        System.out.println("best objective:" + best);
        assert (best == 46);

    }
}