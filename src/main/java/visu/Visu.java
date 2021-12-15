package visu;

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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import localsearch.minils.examples.Sudoku;
import localsearch.minils.examples.SudokuVisu;
import util.InputReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class Visu extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        SplitPane sp = new SplitPane();
        sp.setDividerPositions(0.5f, 0.5f);

        DrawPane drawPane = new DrawPane();
        PlotPane plotPane = new PlotPane();
        sp.getItems().addAll(drawPane,plotPane);

        visuMain(drawPane,plotPane);

        Scene scene = new Scene(sp,1000,600);
        stage.setScene(scene);
        stage.setTitle("Pane");


        stage.show();
    }

    public abstract void visuMain(DrawPane drawPane, PlotPane plotPane);

    class DrawPane extends StackPane {


        Group root;

        DrawPane()  {
            setWidth(1000);
            setHeight(1000);

            root = new Group();

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

            setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    System.out.println("drag"+event);
                    root.setTranslateX(event.getScreenX());
                    root.setTranslateY(event.getScreenY());
                    event.consume();
                }
            });
/*
            setOnMouseDragged(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    //Translate translate = new Translate();
                    root.setTranslateX(event.getScreenX());
                    root.setTranslateY(event.getSceneY());
                    event.consume();
                }
            });*/

        }

        public void addShape(Shape shape) {
            root.getChildren().add(shape);
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
            series.getData().add(new XYChart.Data<>(current++, objective));
            lineChart.autosize();
        }
    }

}



