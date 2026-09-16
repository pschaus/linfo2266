package  uclouvain.linfo2266.visu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

public class ArrowBetweenShapes extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        // Create two rectangles
        Rectangle rect1 = new Rectangle(100, 100, 100, 100);
        rect1.setFill(Color.LIGHTBLUE);

        Rectangle rect2 = new Rectangle(300, 300, 100, 100);
        rect2.setFill(Color.LIGHTGREEN);

        // Create an arrow from rect1 to rect2
        Arrow arrow = new Arrow();
        updateArrow(arrow, rect1, rect2);

        // Add the shapes and arrow to the pane
        pane.getChildren().addAll(rect1, rect2, arrow);

        // Scene and stage setup
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("Arrow Between Shapes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Enhanced Arrow class that includes line orientation and positioning
    public static class Arrow extends Group {
        private final Line line;
        private final Polygon arrowHead;

        public Arrow() {
            this.line = new Line();
            this.arrowHead = new Polygon();
            this.getChildren().addAll(line, arrowHead);
            setupArrowHeadStyle();
        }

        private void setupArrowHeadStyle() {
            arrowHead.setFill(Color.BLACK);
            // Style the arrowhead here
        }

        public void setStart(double startX, double startY) {
            line.setStartX(startX);
            line.setStartY(startY);
            updateArrowHead();
        }

        public void setEnd(double endX, double endY) {
            line.setEndX(endX);
            line.setEndY(endY);
            updateArrowHead();
        }

        private void updateArrowHead() {
            double ex = line.getEndX();
            double ey = line.getEndY();
            double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX()) - Math.PI / 2.0;

            double sin = Math.sin(angle);
            double cos = Math.cos(angle);

            //point1
            double x1 = (- 10 * cos + 20 * sin) + ex;
            double y1 = (- 10 * sin - 20 * cos) + ey;

            //point2
            double x2 = (10 * cos + 20 * sin) + ex;
            double y2 = (10 * sin - 20 * cos) + ey;

            arrowHead.getPoints().setAll(
                    ex, ey, // Arrow tip
                    x1, y1,
                    x2, y2
            );
        }
    }

    // Calculate the intersection point of the line with the rectangle
    private Point2D getRectangleIntersection(Rectangle rect, Point2D lineStart, Point2D lineEnd) {
        Point2D center = new Point2D(rect.getX() + rect.getWidth() / 2.0, rect.getY() + rect.getHeight() / 2.0);

        // If the line is within the rectangle bounds, return the center point
        if (rect.contains(lineStart) && rect.contains(lineEnd)) {
            return center;
        }

        Point2D intersection = Shape.intersect(new Line(lineStart.getX(), lineStart.getY(), lineEnd.getX(), lineEnd.getY()),
                        new Rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight()))
                .getLayoutBounds()
                .getMinX() == -0.5 ? center : lineEnd;

        return intersection;
    }

    private void updateArrow(Arrow arrow, Rectangle startRect, Rectangle endRect) {
        Point2D startCenter = new Point2D(startRect.getX() + startRect.getWidth() / 2.0, startRect.getY() + startRect.getHeight() / 2.0);
        Point2D endCenter = new Point2D(endRect.getX() + endRect.getWidth() / 2.0, endRect.getY() + endRect.getHeight() / 2.0);

        Point2D startIntersection = getRectangleIntersection(startRect, startCenter, endCenter);
        Point2D endIntersection = getRectangleIntersection(endRect, endCenter, startCenter);

        arrow.setStart(startIntersection.getX(), startIntersection.getY());
        arrow.setEnd(endIntersection.getX(), endIntersection.getY());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
