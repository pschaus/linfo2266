package uclouvain.linfo2266.visu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ScaledRectangleApp extends Application {

    private static final double VIRTUAL_WIDTH = 1000;
    private static final double VIRTUAL_HEIGHT = 1000;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        // Define a rectangle in the virtual coordinate system
        Rectangle virtualRectangle = createRectangle(0, 0, 100, 100);

        // Add rectangle to the pane
        pane.getChildren().add(virtualRectangle);

        // Initial scaling
        Scale scale = new Scale(1, 1);
        virtualRectangle.getTransforms().add(scale);

        // Bind rectangle dimensions to scale with the pane
        pane.widthProperty().addListener(observable -> updateScale(scale, pane.getWidth() / VIRTUAL_WIDTH, scale.getY()));
        pane.heightProperty().addListener(observable -> updateScale(scale, scale.getX(), pane.getHeight() / VIRTUAL_HEIGHT));

        primaryStage.setTitle("Scaled Rectangles with Virtual Coordinates");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial scale update
        updateScale(scale, pane.getWidth() / VIRTUAL_WIDTH, pane.getHeight() / VIRTUAL_HEIGHT);
    }

    private Rectangle createRectangle(double x, double y, double width, double height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setStroke(Color.BLUE);
        rectangle.setFill(Color.TRANSPARENT);
        return rectangle;
    }

    private void updateScale(Scale scale, double scaleX, double scaleY) {
        scale.setX(scaleX);
        scale.setY(scaleY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
