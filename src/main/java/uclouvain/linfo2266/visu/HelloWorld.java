package uclouvain.linfo2266.visu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a Label component with "Hello World" text
        Label helloWorldLabel = new Label("Hello World!");

        // Create a layout pane to hold our label
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(helloWorldLabel);

        // Create a scene specifying the root layout pane and the dimensions
        Scene scene = new Scene(rootPane, 300, 250);

        // Set the title of the stage (window)
        primaryStage.setTitle("Hello World JavaFX Application");
        // Set the scene to the stage
        primaryStage.setScene(scene);
        // Show the stage, this makes the application visible
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
