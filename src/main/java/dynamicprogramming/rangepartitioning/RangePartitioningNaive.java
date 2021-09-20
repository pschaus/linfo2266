package dynamicprogramming.rangepartitioning;


import java.util.Arrays;

class RangePartitioningNaive {

    int[] input;
    int[] prefixSum;
    int n;
    int nPartitions;



    public RangePartitioningNaive(int[] input, int k) {
        this.input = input;
        n = input.length;
        this.nPartitions = k;

        // precomputation
        prefixSum = new int[n];
        prefixSum[0] = input[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + input[i];
        }
    }

    // compute input[start]+input[start-1] ... +input[end]
    private int sumInterval(int start, int end) {
        return prefixSum[end] - (start > 0 ? prefixSum[start - 1] : 0);
    }

    int solve(int k, int i) {
        if (i == 0 || k == 1) {
            return sumInterval(0,i);
        } else {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <= i - 1; j++) {
                int res = Math.max(sumInterval(j + 1, i), solve(k - 1,j));
                if (res < min) {
                    min = res;
                }
            }
            return min;
        }
    }

    int solve() {
        return solve(nPartitions,n-1);
    }



    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        //int [] input = new int[] {1, 2, 6, 3, 1, 4, 5, 6, 7, 8, 5};
        RangePartitioningNaive rp = new RangePartitioningNaive(input, 5);
        int objective = rp.solve();
        System.out.println("cost of max partition:" + objective);

    }

}

