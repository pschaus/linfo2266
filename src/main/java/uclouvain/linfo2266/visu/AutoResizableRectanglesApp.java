package uclouvain.linfo2266.visu;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AutoResizableRectanglesApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(300, 300);

        // Create a rectangle and bind its size to the size of the pane
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.BLUE);

        rectangle.xProperty().bind(Bindings.divide(pane.widthProperty(), 10)); // 10% padding from left
        rectangle.yProperty().bind(Bindings.divide(pane.heightProperty(), 10)); // 10% padding from top
        rectangle.widthProperty().bind(Bindings.divide(pane.widthProperty(), 1.2)); // 80% width of pane
        rectangle.heightProperty().bind(Bindings.divide(pane.heightProperty(), 1.2)); // 80% height of pane

        pane.getChildren().add(rectangle);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Auto-Resizable Rectangles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}