package com.mouse.move;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MouseMoveController {
    @FXML
    private Button okButton;

    @FXML
    protected void okButtonOnAction(ActionEvent event) {
        System.out.println("this is a click");
    }

    @FXML
    protected void exitButtonOnAction(ActionEvent event) {
        System.out.println("exiting app...");
    }
}
