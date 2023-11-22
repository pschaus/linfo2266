package uclouvain.linfo2266.localsearch.minils.examples;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SudokuVisu extends Application {


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


    class SudokuPane extends StackPane {

        Rectangle [][] rectangles;
        Text [][] numbers;
        int w = 20;

        SudokuPane(int n, int m)  {

            rectangles = new Rectangle[n][m];
            numbers = new Text[n][m];
            Group root = new Group();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    rectangles [i][j] = new Rectangle(i*w,j*w,w,w);
                    if ((i%2 == 0 && j%2 == 0)||(i%2 == 1 && j%2 == 1) ) {
                        rectangles[i][j].setFill(Color.BEIGE);
                    }
                    numbers [i][j] = new Text(i*w+w/4,j*w+3*w/4,i+"");
                    root.getChildren().add(rectangles[i][j]);
                    root.getChildren().add(numbers[i][j]);
                }
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

        public void setColor(int i, int j, Paint col) {
            rectangles[i][j].setFill(col);
        }

        public void setValue(int i, int j, String value) {
            numbers[i][j].setText(value);
        }


    }


    @Override
    public void start(Stage stage) throws Exception {


        SplitPane sp = new SplitPane();

        SudokuPane sudokuPane = new SudokuPane(9,9);
        sudokuPane.setColor(2,2,Color.GRAY);
        PlotPane plot = new PlotPane();
        Button button = new Button("next");

        int[] instance1 = new int[]{
                0, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 6, 0, 0, 7, 3, 0, 0, 4,
                0, 0, 8, 4, 0, 0, 6, 3, 0,
                8, 0, 0, 6, 0, 0, 0, 9, 0,
                0, 3, 0, 0, 0, 0, 0, 5, 0,
                0, 4, 0, 0, 0, 7, 0, 0, 2,
                0, 7, 5, 0, 0, 4, 1, 0, 0,
                3, 0, 0, 9, 5, 0, 0, 7, 0,
                0, 0, 0, 0, 0, 6, 0, 0, 0};

        int[] instance2 = new int[]{
                0, 0, 0, 2, 0, 0, 0, 6, 3,
                3, 0, 0, 0, 0, 5, 4, 0, 1,
                0, 0, 1, 0, 0, 3, 9, 8, 0,
                0, 0, 0, 0, 0, 0, 0, 9, 0,
                0, 0, 0, 5, 3, 8, 0, 0, 0,
                0, 3, 0, 0, 0, 0, 0, 0, 0,
                0, 2, 6, 3, 0, 0, 5, 0, 0,
                5, 0, 3, 7, 0, 0, 0, 0, 8,
                4, 7, 0, 0, 0, 1, 0, 0, 0};

        int[] instance3 = new int[]{
                5, 0, 0, 0, 0, 8, 0, 7, 0,
                0, 0, 3, 4, 7, 6, 1, 5, 0,
                1, 0, 0, 0, 0, 0, 3, 0, 8,
                0, 6, 5, 0, 4, 2, 0, 0, 1,
                0, 8, 0, 5, 6, 1, 0, 4, 0,
                4, 0, 0, 8, 3, 0, 5, 9, 0,
                6, 0, 7, 0, 0, 0, 0, 0, 5,
                0, 5, 1, 6, 8, 4, 7, 0, 0,
                0, 4, 0, 7, 0, 0, 0, 0, 9};

        int[] problem = instance1;

        Sudoku sudoku = new Sudoku(problem);
        Paint [] colors = new Paint[] {Color.LIGHTGREEN,Color.YELLOW,Color.ORANGE,Color.RED,Color.PURPLE,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK };

        sudoku.attach((iter,i1,j1,i2,j2) -> {

            int viol = sudoku.violation.value();
            final int [][] gridViol = sudoku.violations();

            Platform.runLater(() -> {
                plot.addPoint(viol);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        sudokuPane.setValue(i,j,""+sudoku.grid[i*9+j].value());
                        sudokuPane.setColor(i,j,colors[gridViol[i][j]]);
                        if (problem[i*9+j] > 0) {
                            sudokuPane.setColor(i,j,Color.WHITE);
                        }
                    }
                }
            });
        });

        Runnable run = new Runnable() {
            @Override
            public void run() {
                sudoku.solve();
            }
        };
        new Thread(run).start();

        final int [] iter = new int []{1};
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("click");
                //sudoku.iterationGreedy(iter[0]++);
                sudoku.iterationTabu(iter[0]++,20);
            }
        });

        sp.getItems().addAll(sudokuPane,plot,button);
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