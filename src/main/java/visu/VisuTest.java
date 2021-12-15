package visu;

import javafx.scene.shape.Rectangle;

public class VisuTest extends Visu{
    @Override
    public void visuMain(DrawPane drawPane, PlotPane plotPane) {
        plotPane.addPoint(5);
        plotPane.addPoint(10);

        Rectangle rect = new Rectangle(0,0,100,100);
        drawPane.addShape(rect);

    }

    public static void main(String[] args) {
        launch();
    }
}
