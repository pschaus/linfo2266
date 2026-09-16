package uclouvain.linfo2266.visu;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

import java.util.HashMap;
import java.util.Map;

public class DrawingCanvas extends Pane {

    // Store connections between shapes
    private final Map<Shape, Line> connections = new HashMap<>();

    double virtualWidth = 1000;
    double virtualHeight = 1000;
    private static final double VIRTUAL_HEIGHT = 1000;

    // Initial scaling
    private Scale scale = new Scale(1, 1);

    public DrawingCanvas(int virtualWidth, int virtualHeight) {
        this.virtualWidth = virtualWidth;
        this.virtualHeight = virtualHeight;
        setPrefSize(600, 600); // Default size, could be parameterized

        widthProperty().addListener(observable -> updateScale(scale, getWidth() / virtualWidth, scale.getY()));
        heightProperty().addListener(observable -> updateScale(scale, scale.getX(), getHeight() / virtualHeight));

    }

    private void updateScale(Scale scale, double scaleX, double scaleY) {
        scale.setX(scaleX);
        scale.setY(scaleY);
    }

    // Add a rectangle to the canvas
    public Rectangle addRectangle(double x, double y, double width, double height, String label, Color color) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setFill(color);
        Text text = new Text(label);
        text.setX(x + width / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY(y + height / 2);

        addShape(rect);
        getChildren().add(text); // Add the label to the canvas

        // Add event handler for the rectangle
        setupInteraction(rect, text);
        rect.getTransforms().add(scale);
        text.getTransforms().add(scale);

        return rect;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    // Add a circle to the canvas
    public Circle addCircle(double centerX, double centerY, double radius, String label, Color color) {
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(color);
        Text text = new Text(label);
        text.setX(centerX - text.getLayoutBounds().getWidth() / 2);
        text.setY(centerY);

        addShape(circle);
        getChildren().add(text); // Add the label to the canvas

        // Add event handler for the circle
        setupInteraction(circle, text);
        return circle;
    }

    // Add a line (arrow) connecting two shapes
    public Line addArrow(Shape from, Shape to) {
        Line line = new Line();
        updateArrowPosition(line, from, to);

        getChildren().add(line);
        // Store the connection
        connections.put(from, line);

        // Here you would need a more complex mechanism to update line when shapes move.
        // This is a complex problem and usually involves binding properties and listeners.

        return line;
    }

    private void updateArrowPosition(Line line, Shape from, Shape to) {
        line.setStartX(from.getBoundsInParent().getMinX() + from.getBoundsInParent().getWidth() / 2);
        line.setStartY(from.getBoundsInParent().getMinY() + from.getBoundsInParent().getHeight() / 2);
        line.setEndX(to.getBoundsInParent().getMinX() + to.getBoundsInParent().getWidth() / 2);
        line.setEndY(to.getBoundsInParent().getMinY() + to.getBoundsInParent().getHeight() / 2);
    }

    // Generic method to add any shape to the canvas
    private void addShape(Shape shape) {
        getChildren().add(shape);
    }

    // Sets up interaction with a shape
    private void setupInteraction(Shape shape, Text text) {
        shape.setOnMousePressed(event -> {
            // Implement what happens when a shape is clicked
            System.out.println("Shape clicked!");
        });

        // Example of moving the shape
        shape.setOnMouseDragged(event -> {
            double offsetX = event.getX() - shape.getBoundsInParent().getWidth() / 2;
            double offsetY = event.getY() - shape.getBoundsInParent().getHeight() / 2;
            shape.relocate(offsetX, offsetY);

            text.setX(offsetX + shape.getBoundsInParent().getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
            text.setY(offsetY + shape.getBoundsInParent().getHeight() / 2);

            // Update arrows if connected
            Line line = connections.get(shape);
            if (line != null) {
                // updateArrowPosition(line, shape, /* destination shape here */);
            }
        });
    }
}