package com.mouse.move;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MouseMoveController {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @FXML
    private TextField textField;

    @FXML
    private void initialize() {
        executor.submit(new IdleTask(textField));
    }

    public void shutdownApplication() {
        if (executor != null) {
            executor.shutdownNow();
        }
        Platform.exit();
    }
}
