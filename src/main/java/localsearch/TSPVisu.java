package localsearch;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TSPVisu extends Application {


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



    class TSPPane extends StackPane {

        Line [] line;
        TSP.TSPInstance instance;

        TSPPane(TSP.TSPInstance instance)  {
            this.instance = instance;
            line = new Line[instance.n];
            for (int i = 0; i < instance.n; i++) {
                line[i] = new Line();;
                line[i].setStartX(instance.xCoord[i]);
                line[i].setStartY(instance.yCoord[i]);
                line[i].setEndX(instance.xCoord[i]);
                line[i].setEndY(instance.yCoord[i]);
            }

            Group root = new Group(line);
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

        public void updateTour(int[] tour) {
            for (int i = 0; i < instance.n; i++) {
                int orig = tour[i];
                int dest = tour[i + 1];
                line[i].setStartX(instance.xCoord[orig]);
                line[i].setStartY(instance.yCoord[orig]);
                line[i].setEndX(instance.xCoord[dest]);
                line[i].setEndY(instance.yCoord[dest]);
            }
        }

    }


    @Override
    public void start(Stage stage) throws Exception {

        TSP.TSPInstance instance = new TSP.TSPInstance(500,0, 200);
        TSP.TSPLocalSearch ls = new TSP.TSP2Opt(instance);
        //TSP.TSPLocalSearch ls = new TSP.TSPKopt(instance);

        SplitPane sp = new SplitPane();

        TSPPane tspPane = new TSPPane(instance);
        PlotPane plot = new PlotPane();
        ls.attach(iter -> {
            int dist = ls.tourDistance();
            int [] tour = ls.currentTour();
            Platform.runLater(() -> {
                    plot.addPoint(dist);
                    tspPane.updateTour(tour);
            });
        });

        Runnable run = new Runnable() {
            @Override
            public void run() {
                ls.optimize();
            }
        };
        new Thread(run).start();



        sp.getItems().addAll(tspPane,plot);
        sp.setDividerPositions(0.5f, 0.5f);
        Scene scene = new Scene(sp,1000,600);
        stage.setScene(scene);
        stage.setTitle("Pane");

        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}