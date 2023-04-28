package com.mouse.move;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MouseMoveApp extends Application {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<?> task;

    @Override
    public void start(Stage stage) throws Exception {
//        VBox vBox = new VBox();
//
////        var textField = new TextField();
////        Button button = new Button("Start mouse move");
////        button.setOnAction(e -> {
////            if (task != null && !task.isCancelled()) {
////                task.cancel(true);
////                button.setText("Start mouse move");
////            } else {
////                task = executor.submit(() -> {
////                    while (!Thread.currentThread().isInterrupted()) {
////                        Platform.runLater(() -> {
////                            var r = new Robot();
////                            if (textField.getText().isEmpty()) {
////                                textField.setText("a");
////                                r.mouseMove(r.getMouseX() + 5, r.getMouseY() + 5);
////                            } else {
////                                textField.clear();
////                                r.mouseMove(r.getMouseX() - 5, r.getMouseY() - 5);
////                            }
////                        });
////                        try {
////                            TimeUnit.SECONDS.sleep(3);
////                        } catch (InterruptedException ex) {
////
////                        }
////                    }
////                    System.out.println("Worker thread has finished!");
////                });
////            }
////        });
////
////        vBox.getChildren().addAll(button, textField);
//
//        Pane root = new Pane();
//        root.setStyle("-fx-background-color: transparent");
//        ImageView img = new ImageView();
//        img.setStyle("-fx-background-color: transparent");
//        img.setImage(new Image(getClass().getClassLoader().getResource("mouse.png").toExternalForm(),
//                600, 600, true, true));
//        root.getChildren().add(img);
//        Scene scene = new Scene(root, 1000, 1000);
//        scene.setFill(Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setScene(scene);
//        stage.show();

        stage.initStyle(StageStyle.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getClassLoader()
                .getResource("MouseMoveController.fxml"));
        String css = getClass().getClassLoader().getResource("main.css").toExternalForm();
        Scene scene = new Scene(root, 600, 600, Color.TRANSPARENT);
        scene.getStylesheets().add(css);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
