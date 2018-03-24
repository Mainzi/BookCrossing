package com.library.controller;

import com.library.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AboutController {
    @FXML
    private Button back;

    MainApp mainApp;
    @FXML
    private void initialize() {
        back.setOnAction((event) -> {
            try {
                back(event);
            } catch (Exception e) {
            }
        });
    }

    @FXML
    //Возврат в главное меню
    private void back(ActionEvent mouseEvent) {
        mainApp.showMainWindow();
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
