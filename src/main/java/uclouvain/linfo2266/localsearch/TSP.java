package uclouvain.linfo2266.localsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TSP {

    @FunctionalInterface
    interface
    TSP2OptObserver {
        void update(int iteration);
    }

    public static void main(String[] args) {
        TSPInstance instance = new TSPInstance(200,0, 200);
        System.out.println("2opt:");
        TSPLocalSearch ls = new TSP2Opt(instance);
        ls.optimize();
        System.out.println(ls.tourDistance());
        System.out.println("kopt:");
        ls = new TSPKOpt(instance,10);
        ls.optimize();
        System.out.println(ls.tourDistance());
    }

    static class  TSP2Opt extends TSPLocalSearch {

        TSP2Opt(TSPInstance data) {
            super(data);
        }

        @Override
        boolean iteration() {
            int bestLeft = 0, bestRight = 0, bestDelta = 0;
            // 2-opt move
            for (int left = 0; left < n; left++) {
                for (int right = left+1; right < n ; right++) {
                    int delta = deltaTwoOpt(left,right);
                    if (delta < bestDelta) {
                        bestDelta = delta;
                        bestLeft = left;
                        bestRight = right;
                    }
                }
            }
            twoOpt(bestLeft,bestRight);
            //System.out.println(Arrays.toString(tour));
            //System.out.println("bestDelta:"+bestDelta);
            return bestDelta < 0;
        }
    }

    static class TSPKOpt extends TSPLocalSearch {

        int K;

        TSPKOpt(TSPInstance data, int K) {
            super(data);
            this.K = K;
        }

        public boolean kOptFrom(int i) {
            saveTour();
            int cumulatedDelta = 0;
            int bestCumulatedDelta = cumulatedDelta;
            int bestK = 0;
            int k = 0;
            int prev = -1;
            do {
                k += 1;
                int bestDelta = Integer.MAX_VALUE;
                int bestj = -1;
                for (int j = 0; j < n; j++) {
                    int dist = Math.abs(j-i);
                    if (1 < dist && dist < n-1 && j != prev) { // no stupid 2-opt or direct cycling
                        int delta = deltaTwoOpt(i,j);
                        if (delta < bestDelta) {
                            bestDelta = delta;
                            bestj = j;
                        }
                    }
                }
                prev = bestj;
                twoOpt(i,bestj);
                cumulatedDelta += bestDelta;
                if (cumulatedDelta < bestCumulatedDelta) {
                    bestCumulatedDelta = cumulatedDelta;
                    bestK = k;
                    saveTour();
                }
            } while (k < K);
            restoreSaved();
            return bestCumulatedDelta < 0;
        }

        @Override
        boolean iteration() {
            var found = false;
            for (int i = 0; i < n; i++) {
                //println("bestCumulated:"+bestCumulatedDelta+" k:"+bestK)
                if (kOptFrom(i)) {
                    found = true;
                }
            }
            return found;
        }
    }


    static abstract class TSPLocalSearch {
        int n;
        int [][] dist;
        int [] tour;
        int [] tourSaved;

        private List<TSP2OptObserver> observers = new ArrayList<TSP2OptObserver>();


        TSPLocalSearch(TSPInstance data) {
            this.n = data.n;
            this.dist = data.distanceMatrix;
            this.tour = new int[data.n+1]; // first and last node are the same
            for (int i = 0; i < n; i++) {
                tour[i] = i;
            }
            tourSaved = Arrays.copyOf(tour,n+1);
        }


        public void attach(TSP2OptObserver observer){
            observers.add(observer);
        }

        public void notifyAllObservers(int iter){
            for (TSP2OptObserver observer : observers) {
                observer.update(iter);
            }
        }

        public int tourDistance() {
            int distance = 0;
            for (int i = 1; i < tour.length; i++) {
                distance += dist[tour[i-1]][tour[i]];
            }
            return  distance;
        }

        public int[] currentTour() {
            return Arrays.copyOf(tour,n+1);
        }

        public void saveTour() {
            System.arraycopy(tour,0,tourSaved,0,n+1);
        }

        public void restoreSaved() {
            System.arraycopy(tourSaved,0,tour,0,n+1);
        }

        /**
         * Let left = min(i,j)
         *     right = max(i,j)
         * Swap the sub-array
         *
         * tour[left+1,left+2,...,right]
         *
         * Before
         *
         *   v0  v1  v2  v3  v4  v5  v6  v7  v0
         * +---+---+---+---+---+---+---+---+---+
         *   0  1=l  2   3   4  5=r  6   7   8
         *
         *  After (left = 1, right = 5)
         *
         *             swapped
         *         |----------------|
         *
         *   v0  v1  v5  v4  v3  v2  v6  v7  v0
         * +---+---+---+---+---+---+---+---+---+
         *   0   1  2   3   4    5  6   7   8
         *
         */
        public void twoOpt(int i, int j) {
            int left = Math.min(i,j);
            int right = Math.max(i,j);

            assert (left >= 0 && right < n);
            for (int k = 0; k < (right - left + 1) / 2; k++) {
                int tmp = tour[left + 1 + k];
                tour[left + k + 1] = tour[right - k];
                tour[right - k] = tmp;
            }
        }

        abstract boolean iteration();

        public void optimize() {
            int iter = 0;
            long t0 = System.currentTimeMillis();
            boolean improved = false;
            do {
                //System.out.println("distance:"+tourDistance());
                improved = iteration();
                notifyAllObservers(iter);
                iter += 1;
                
                try {
                    Thread.sleep(1);
                } catch (Exception e){

                }
            } while (improved);
            long t1 = System.currentTimeMillis();
            System.out.println("#iter"+iter);
            System.out.println("time:"+(t1-t0));
            System.out.println("distance:"+tourDistance());


        }


        /**
         * @param i 0 <= i!=j < nNodes-1
         * @param j 0 <= i!=j < nNodes-1
         * @return cost change of the tour if we would execute twoOpt(left,right)
         */
        public int deltaTwoOpt(int i, int j) {
            int left = Math.min(i,j);
            int right = Math.max(i,j);
            assert (left >= 0 && right < n);
            int distLeft = dist[tour[left]][tour[left+1]];
            int distRight = dist[tour[right]][tour[right+1]];
            int distLeftNew = dist[tour[left]][tour[right]];
            int distRightNew = dist[tour[left+1]][tour[right+1]];
            return  distLeftNew + distRightNew - distLeft - distRight;
        }


    }

    static class TSPInstance {
        int [][] distanceMatrix;
        int [] xCoord;
        int [] yCoord;
        int n;

        public TSPInstance(int n, int seed, int squareLength) {
            this.n = n;
            Random rand = new Random(seed);
            xCoord =  new int[n];
            yCoord = new int[n];
            distanceMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                xCoord[i] = rand.nextInt(squareLength);
                yCoord[i] = rand.nextInt(squareLength);
            }
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    distanceMatrix[i][j] = dist(xCoord[i],yCoord[i],xCoord[j],yCoord[j]);
                    distanceMatrix[j][i] = distanceMatrix[i][j];
                }
            }
        }

        public int dist(int x1, int y1, int x2, int y2) {
            int dx = x1-x2;
            int dy = y1-y2;
            return (int) Math.sqrt(dx*dx+dy*dy);
        }
    }



}
