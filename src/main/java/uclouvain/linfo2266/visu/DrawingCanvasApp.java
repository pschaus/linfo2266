package uclouvain.linfo2266.visu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingCanvasApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        DrawingCanvas drawingCanvas = new DrawingCanvas();

        // Binding canvas size to stack pane size.
        drawingCanvas.widthProperty().bind(root.widthProperty());
        drawingCanvas.heightProperty().bind(root.heightProperty());

        root.getChildren().add(drawingCanvas);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Resizable Drawing Canvas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class DrawingCanvas extends Canvas {

        public DrawingCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);

            // Your drawing code goes here
            gc.setStroke(Color.BLUE);
            gc.strokeRect(10, 10, width - 20, height - 20);
        }

        // Override layoutChildren to resize canvas
        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
    }
}