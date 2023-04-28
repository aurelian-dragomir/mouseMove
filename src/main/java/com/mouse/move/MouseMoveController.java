package com.mouse.move;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MouseMoveController {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> task;
    @FXML
    private VBox rootPane;

    @FXML
    private TextField textField;

    private Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void initialize() {
        rootPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        rootPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    @FXML
    protected void startButtonOnAction(ActionEvent event) {
        Button _thisButton = (Button) event.getSource();
        if (task != null && !task.isCancelled()) {
            task.cancel(true);
            _thisButton.setText("Start");
        } else {
            task = executor.submit(() -> {
                Platform.runLater(() -> _thisButton.setText("Stop"));
                while (!Thread.currentThread().isInterrupted()) {
                    Platform.runLater(() -> {
                        var r = new Robot();
                        if (textField.getText().isEmpty()) {
                            textField.setText("a");
                            r.mouseMove(r.getMouseX() + 5, r.getMouseY() + 5);
                        } else {
                            textField.clear();
                            r.mouseMove(r.getMouseX() - 5, r.getMouseY() - 5);
                        }
                    });
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public MouseMoveController setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    @FXML
    protected void exitButtonOnAction(ActionEvent event) {
        if (executor != null) {
            executor.shutdownNow();
        }
        Platform.exit();
    }
}
