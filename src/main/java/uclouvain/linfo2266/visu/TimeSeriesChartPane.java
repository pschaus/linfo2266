package uclouvain.linfo2266.visu;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class TimeSeriesChartPane extends Pane {
    private LineChart<Number, Number> lineChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    // Store series by ID for easy access when adding points
    private Map<String, XYChart.Series<Number, Number>> seriesMap = new HashMap<>();

    public TimeSeriesChartPane(String xAxisLabel, String yAxisLabel) {
        // Create the x and y axes
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel(xAxisLabel);
        yAxis.setLabel(yAxisLabel);

        // Create the line chart
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false); // Disable animation for real-time plotting

        // Layout the line chart to take the full space of the pane
        lineChart.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        getChildren().add(lineChart);
    }

    // Method to add a new line series
    public void addSeries(String seriesId, String lineName, String color) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(lineName);

        // Set the color for the series
        seriesMap.put(seriesId, series);
        lineChart.getData().add(series);

        // Apply the color style
        series.getNode().setStyle("-fx-stroke: " + color + ";");
    }

    // Method to add a point to a series
    public void addDataPoint(String seriesId, Number xValue, Number yValue) {
        XYChart.Series<Number, Number> series = seriesMap.get(seriesId);
        if (series != null) {
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(xValue, yValue);
            series.getData().add(dataPoint);
        }
    }

    // Method to set axis labels
    public void setAxisLabels(String xLabel, String yLabel) {
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
    }
}