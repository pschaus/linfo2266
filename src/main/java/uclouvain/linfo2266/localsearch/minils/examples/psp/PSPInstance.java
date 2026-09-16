package uclouvain.linfo2266.localsearch.minils.examples.psp;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PSPInstance {

    public int nTypes, nPeriods;
    public int[] stockingCost;
    public int[][] changeoverCost, demand;
    public final int objective;
    public Demand[] demands; // array of demand objects sorted by increasing deadline values

    public PSPInstance(int nPeriods, int nTypes, int[] stockingCost, int[][] changeoverCost, int[][] demand) {
        this.nTypes = nTypes;
        this.nPeriods = nPeriods;
        this.stockingCost = stockingCost;
        this.changeoverCost = changeoverCost;
        this.demand = demand;
        this.objective = -1;
        this.initializeDemands();
    }

    public PSPInstance(int nPeriods, int nTypes) {
        this(nPeriods, nTypes, new int[nTypes], new int[nTypes][nTypes], new int[nTypes][nPeriods]);
    }

    public PSPInstance(String path) {
        int obj = -1;

        try {
            Scanner scan = new Scanner(new File(path));

            nPeriods = scan.nextInt();
            nTypes = scan.nextInt();

            changeoverCost = new int[nTypes][nTypes];
            stockingCost = new int[nTypes];
            demand = new int[nTypes][nPeriods];

            scan.nextInt(); // nDemands

            for (int i = 0; i < nTypes; i++) for (int j = 0; j < nTypes; j++) {
                changeoverCost[i][j] = scan.nextInt();
            }

            for (int i = 0; i < nTypes; i++) {
                stockingCost[i] = scan.nextInt();
            }

            for (int i = 0; i < nTypes; i++) for (int j = 0; j < nPeriods; j++) {
                demand[i][j] = scan.nextInt();
            }

            if (scan.hasNextInt()) {
                obj = scan.nextInt();
            }

            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to read input file");
            System.exit(0);
        }

        objective = obj;

        this.initializeDemands();
    }

    /**
     * Initializes the array of demand objects sorted by increasing deadline time
     * This array can be used to identify demands uniquely by their index in the array
     */
    private void initializeDemands() {
        ArrayList<Demand> demandsByIncreasingTime = new ArrayList<>();
        for (int p = 0; p < this.nPeriods; p++) {
            for (int i = 0; i < this.nTypes; i++) {
                if (this.demand[i][p] == 1) {
                    demandsByIncreasingTime.add(new Demand(i, p));
                }
            }
        }
        this.demands = demandsByIncreasingTime.toArray(new Demand[]{});
    }

    /**
     * Represents a unique demand with a specific item type and deadline
     */
    public class Demand {
        public final int type, deadline;

        public Demand(int type, int deadline) {
            this.type = type;
            this.deadline = deadline;
        }
    }

    public boolean feasible() {
        int rem = 0;
        for (int j = nPeriods - 1; j >= 0; j--) {
            for (int i = 0; i < nTypes; i++) {
                rem += demand[i][j];
            }
            if (rem > 0) rem--;
        }
        return rem == 0;
    }

    @Override
    public String toString() {
        int totalDemand = 0;
        for (int i = 0; i < nTypes; i++) for (int j = 0; j < nPeriods; j++) totalDemand += demand[i][j];

        StringBuilder out = new StringBuilder();
        out.append(nPeriods).append("\n");
        out.append(nTypes).append("\n");
        out.append(totalDemand).append("\n\n");

        for (int i = 0; i < nTypes; i++) {
            for (int j = 0; j < nTypes; j++) {
                out.append(changeoverCost[i][j]).append(" ");
            }
            out.append("\n");
        }
        out.append("\n");

        for (int i = 0; i < nTypes; i++) {
            out.append(stockingCost[i]).append(" ");
        }
        out.append("\n\n");

        for (int i = 0; i < nTypes; i++) {
            for (int j = 0; j < nPeriods; j++) {
                out.append(demand[i][j]).append(" ");
            }
            out.append("\n");
        }

        return out.toString();
    }

    public void save(String filename) {
        File outputFile = new File(filename);
        PrintWriter out = null;
        try {
            outputFile.createNewFile();
            out = new PrintWriter(outputFile);

            out.print(this);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public PSPInstance(int nPeriods, int nTypes, double density, double alpha, int seed) {
        this(nPeriods, nTypes);
        int costAverage = 10000, costSpread = 5000;
        Random rand = new Random(seed);

        int totalOrders = (int) Math.round(density * nPeriods);

        for (int i = 0; i < nTypes; i++) {
            this.stockingCost[i] = (int) Math.rint(alpha * (costAverage + 0.5 * costSpread - rand.nextInt(costSpread)));
            for (int j = 0; j < nTypes; j++) if (i != j) {
                this.changeoverCost[i][j] = (int) Math.rint((1 - alpha) * (costAverage + 0.5 * costSpread - rand.nextInt(costSpread)));
            }
        }

        for (int k = 0; k < totalOrders; k++) {
            while (true) {
                int type = rand.nextInt(nTypes);
                int period = rand.nextInt(nPeriods);

                if (this.demand[type][period] == 0) {
                    this.demand[type][period] = 1;
                    if (this.feasible()) {
                        break;
                    } else {
                        this.demand[type][period] = 0;
                    }
                }
            }
        }

        this.initializeDemands();
    }

    public static void generate(int nPeriods, int nTypes, double density, double alpha, int seed) {
        PSPInstance instance = new PSPInstance(nPeriods, nTypes, density, alpha, seed);
        instance.save("data/PSP/large/psp_" + nPeriods + "_" + nTypes + "_" + ((int) Math.rint(100*density)) + "_" + ((int) Math.rint(1000*alpha)) + "_" + seed);
    }

    public static void main(String[] args) {
        for (int seed = 0; seed < 20; seed++) {
            generate(50000, 10, 0.95, 0.5, seed);
        }
    }
}
