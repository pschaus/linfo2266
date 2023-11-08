package uclouvain.linfo2266.visu;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Visu extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        TimeSeriesChartPane timeSeriesChartPane = new TimeSeriesChartPane("x","y");
        timeSeriesChartPane.addSeries("algo1","algo1","blue");
        timeSeriesChartPane.addDataPoint("algo1",0,10);
        timeSeriesChartPane.addDataPoint("algo1",20,20);

        DrawingCanvas drawingCanvas = new DrawingCanvas(1000,1000);
        drawingCanvas.addRectangle(0,0,100,100, "MyRect",Color.BLUE);


        GridVisu gridVisu = new GridVisu(2,2);
        gridVisu.add(drawingCanvas,0,0);
        gridVisu.add(timeSeriesChartPane,0,1);



        Scene scene = new Scene(gridVisu,1000,600);
        stage.setScene(scene);
        stage.setTitle("Pane");


        stage.show();


    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

}



