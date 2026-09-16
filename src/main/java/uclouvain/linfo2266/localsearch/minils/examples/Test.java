package uclouvain.linfo2266.localsearch.minils.examples;

public class Test {
    public static void main(String[] args) {
        System.out.println(recur(500,4,0,0));
    }

    public static long recur(int N, int D, int i, int d) {
        if (d == D) return 1;
        long tot = 1;
        for (int j = i+1; j < N ; j++) {
            tot += recur(N,D,j,d+1);
        }
        return tot;
    }
}
