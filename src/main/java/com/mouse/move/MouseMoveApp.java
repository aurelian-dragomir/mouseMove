package com.mouse.move;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MouseMoveApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("MouseMoveController.fxml"));
        Parent root = loader.load();
        MouseMoveController controller = loader.getController();
        stage.setOnCloseRequest(e -> controller.shutdownApplication());
        String css = getClass().getClassLoader().getResource("main.css").toExternalForm();
        Scene scene = new Scene(root, 250, 100, Color.WHEAT);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
