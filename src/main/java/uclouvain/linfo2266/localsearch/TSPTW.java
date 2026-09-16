package uclouvain.linfo2266.localsearch;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import uclouvain.linfo2266.util.InputReader;

import java.util.Arrays;
import java.util.Comparator;

public class TSPTW extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Instance instance = new Instance("data/TSPTW/OhlmannThomas/n150w120.001.txt", 10000);
        Instance instance = new Instance("data/TSPTW/Dumas/n100w20.001.txt", 100);
        //instance = instance.sort();
        TSPTWSolver tsptw = new TSPTWSolver(instance);
        int [] solution = new int[] {88,30,1,68,49,11,57,65,9,42,28,72,20,79,4,50,95,24,22,6,62,60,86,94,53,61,56,64,10,32,8,12,70,5,29,87,34,93,76,85,92,98,78,77,3,43,2,75,74,44,13,27,41,59,16,91,55,23,19,66,47,35,40,38,18,33,96,73,31,15,7,46,83,52,48,58,84,39,97,99,90,67,89,37,82,63,81,17,71,54,14,80,45,25,26,51,21,100,36,69};
                //tsptw.greedy1();

        SplitPane sp = new SplitPane();
        sp.setDividerPositions(0.5f, 0.5f);

        TSPTWPane tsptwPane = new TSPTWPane(instance,solution);
        //PlotPane plotPane = new PlotPane();
        sp.getItems().addAll(tsptwPane/*,plotPane*/);

        Scene scene = new Scene(sp,1000,600);
        stage.setScene(scene);
        stage.setTitle("TSPTW Dumas/n100w20.001.txt");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    static class TSPTWSolver {
        Instance instance;

        TSPTWSolver(Instance instance) {
            this.instance = instance;

        }

        public int[] greedy1() {
            int [] random = new int[instance.n];
            for (int i = 0; i < instance.n; i++) {
                random[i] = i;
            }
            return  random;
        }
    }

    class TSPTWPane extends StackPane {

        Rectangle[] rectangles;
        Circle[] circles;
        int [] solution;
        int h;
        int scaleWidth;

        TSPTWPane(Instance instance, int [] solution)  {
            this.solution = solution;
            setWidth(1000);
            setHeight(1000);
            int maxL = Arrays.stream(instance.L).max().getAsInt();
            scaleWidth = maxL/1000;
            h = 1000/instance.n;

            rectangles = new Rectangle[instance.n];
            circles = new Circle[instance.n];
            Group root = new Group();

            for (int i = 0; i < instance.n-1; i++) {
                    int idx = solution[i];
                    rectangles [i] = new Rectangle(instance.E[idx]/scaleWidth,i*h,(instance.L[idx]-instance.E[idx]+1)/scaleWidth,h);
                    rectangles[i].setFill(Color.BLUE);
                    root.getChildren().add(rectangles[i]);

                    circles[i] = new Circle(instance.E[idx]/scaleWidth,i*h+h/2,h/4);
                    circles[i].setFill(Color.RED);
                    root.getChildren().add(circles[i]);
            }

            int t = 0;
            for (int i = 1; i < instance.n-1; i++) {

                t = t + instance.distMatrix[solution[i-1]][solution[i]];
                t = Math.max(instance.E[solution[i]],t);
                if (t >= instance.L[solution[i]]) {
                    System.out.println("violation "+i);
                }
                //System.out.println(t+" "+instance.L[solution[i]]);
                circles[i].setCenterX(t/scaleWidth);
            }



            getChildren().add(root);



            final double SCALE_DELTA = 1.1;

            setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    event.consume();
                    if (event.getDeltaY() == 0) {
                        return;
                    }
                    double scaleFactor =
                            (event.getDeltaY() > 0)
                                    ? SCALE_DELTA
                                    : 1/SCALE_DELTA;

                    root.setScaleX(root.getScaleX() * scaleFactor);
                    root.setScaleY(root.getScaleY() * scaleFactor);
                }
            });

        }


    }


    class PlotPane extends StackPane {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        XYChart.Series series = new XYChart.Series();
        int current = 0;

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        PlotPane() {
            xAxis.setLabel("Iteration");
            yAxis.setLabel("Objective");
            //creating the chart
            lineChart.setTitle("Objective Function");
            lineChart.setCreateSymbols(false);
            //defining a series
            series.setName("Objective");

            Scene scene  = new Scene(lineChart,800,600);
            lineChart.getData().add(series);

            xAxis.setAutoRanging(true);
            xAxis.setForceZeroInRange(true);
            yAxis.setAutoRanging(true);
            yAxis.setForceZeroInRange(true);
            lineChart.autosize();
            //lineChart.applyCss();

            Group group = new Group(lineChart);
            getChildren().add(group);
        }

        public void addPoint(int objective) {
            if (current < 1000) {
                series.getData().add(new XYChart.Data<>(current++, objective));
                lineChart.autosize();
            }


        }
    }





    static class Instance {

        int n;
        int [][] distMatrix;
        int [] E, L;
        int horizon = Integer.MIN_VALUE;

        Instance(String file, int scale) {
            InputReader reader = new InputReader(file);
            n = reader.getInt();
            distMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distMatrix[i][j] = (int) (scale * reader.getDouble());
                }
            }
            E = new int[n];
            L = new int[n];

            for (int i = 0; i < n; i++) {
                E[i] = scale * reader.getInt();
                L[i] = scale * reader.getInt();
                horizon = Math.max(horizon, L[i]+1);
            }

        }

        private Instance(int [][] distMatrix, int[] E, int [] L) {
            n = E.length;
            this.E = E;
            this.L = L;
            this.distMatrix = distMatrix;
            for (int i = 0; i < n; i++) {
                horizon = Math.max(horizon, L[i]+1);
            }
        }

        public Instance sort() {
            Integer [] perm = new Integer[n];
            for (int i = 0; i < n; i++) {
                perm[i] = i;
            }
            Arrays.sort(perm, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return L[o1]-L[o2];
                }
            });

            int [][] distMatrix_ = new int[n][n];
            int [] E_ = new int[n];
            int [] L_ = new int[n];

            for (int i = 0; i < n; i++) {
                E_[i] = E[perm[i]];
                L_[i] = L[perm[i]];
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distMatrix_[i][j] = distMatrix[perm[i]][perm[j]];
                }
            }
            return new Instance(distMatrix_,E_, L_);
        }

        @Override
        public String toString() {
            return "Instance{" +
                    "n=" + n + "\n" +
                    ", distMatrix=" + Arrays.deepToString(distMatrix) + "\n" +
                    ", E=" + Arrays.toString(E) + "\n" +
                    ", L=" + Arrays.toString(L) + "\n" +
                    ", horizon=" + horizon +
                    '}';
        }
    }
}


