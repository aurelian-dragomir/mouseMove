package com.mouse.move;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MouseMoveApp extends Application {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<?> task;

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("MouseMoveController.fxml"));
        Parent root = loader.load();
        MouseMoveController controller = loader.getController();
        controller.setStage(stage);
        String css = getClass().getClassLoader().getResource("main.css").toExternalForm();
        Scene scene = new Scene(root, 600, 600, Color.TRANSPARENT);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
