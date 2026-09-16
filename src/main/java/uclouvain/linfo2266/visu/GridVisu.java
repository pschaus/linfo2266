package uclouvain.linfo2266.visu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;

public class GridVisu extends GridPane {

    public GridVisu(int nbColumns, int nbRows) {
        for (int i = 0; i < nbColumns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / nbColumns);
            colConst.setHgrow(Priority.ALWAYS); // Allow column to grow
            getColumnConstraints().add(colConst); // Assign the column constraints to the grid
        }

        for (int j = 0; j < nbRows; j++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / nbRows);
            rowConst.setVgrow(Priority.ALWAYS); // Allow row to grow
            getRowConstraints().add(rowConst); // Assign the row constraints to the grid
        }

        // Set the grid lines visible for debugging
        // This can be removed or commented out for a production application
        setGridLinesVisible(true);

        // Additional grid setup (optional)
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
    }

    public void addPane(int colIndex, int rowIndex, Pane pane) {
        // Assuming i = column index, j = row index according to your parameter names
        // Make the Pane fill its cell completely
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        add(pane, colIndex, rowIndex);

        // Set the Pane to grow to fill its cell
        setHgrow(pane, Priority.ALWAYS);
        setVgrow(pane, Priority.ALWAYS);
    }

}


