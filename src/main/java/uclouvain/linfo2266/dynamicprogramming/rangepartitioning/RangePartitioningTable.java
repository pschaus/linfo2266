package uclouvain.linfo2266.dynamicprogramming.rangepartitioning;


import java.util.Arrays;

class RangePartitioningTable {

    int[] input;
    int[] prefixSum;
    int n;
    int nPartitions;

    int[][] cache;

    public RangePartitioningTable(int[] input, int k) {
        this.input = input;
        n = input.length;
        this.nPartitions = k;


        // cache[k][i] = optimal solution with k partition on input[0..i]
        cache = new int[k + 1][n];

        // precomputation
        prefixSum = new int[n];
        prefixSum[0] = input[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + input[i]; // input[0]+....+input[i]
        }
    }

    // compute input[start]+input[start+1] ... +input[end]
    private int sumInterval(int start, int end) {
        return prefixSum[end] - prefixSum[start - 1];
    }

    int solve() {
        // base cases
        for (int k = 1; k <= nPartitions; k++) {
            cache[k][0] = input[0]; // k partition on sequence of 1 element
        }
        for (int i = 1; i < n; i++) {
            cache[1][i] = cache[1][i - 1] + input[i]; // 1 partition up to i
        }
        // recurrence
        for (int k = 2; k <= nPartitions; k++) {
            for (int i = 1; i < n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j <= i - 1; j++) {
                    int res = Math.max(sumInterval(j + 1, i), cache[k - 1][j]);
                    if (res < min) {
                        min = res;
                    }
                }
                cache[k][i] = min;
            }
        }
        return cache[nPartitions][n - 1];
    }

    public void printCache() {
        for (int k = 1; k < cache.length; k++) {
            System.out.println("k=" + k + ":" + Arrays.toString(cache[k]));
        }
    }

    public static void main(String[] args) {

        //int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] input = new int[] {1, 2, 6, 3, 1, 4, 5, 6, 7, 8, 5};
        RangePartitioningTable rp = new RangePartitioningTable(input, 4);
        int objective = rp.solve();
        System.out.println("cost of max partition:" + objective);
        rp.printCache();

    }

}

