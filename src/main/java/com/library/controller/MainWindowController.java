package com.library.controller;

import com.library.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindowController {

    MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void FindBookEvent(ActionEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/FindBook.fxml"));
            AnchorPane MainWindow = (AnchorPane) loader.load();

            mainApp.getRootLayout().setCenter(MainWindow);

            FindBookController findBookController = loader.getController();
            findBookController.setMainApp(mainApp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void aboutEvent(ActionEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/About.fxml"));
            AnchorPane MainWindow = (AnchorPane) loader.load();

            mainApp.getRootLayout().setCenter(MainWindow);
            AboutController aboutController =loader.getController();
            aboutController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addDataEvent(ActionEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AddData.fxml"));
            AnchorPane MainWindow = (AnchorPane) loader.load();

            mainApp.getRootLayout().setCenter(MainWindow);
            AddDataController addDataController = loader.getController();
            addDataController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void statisticEvent(ActionEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Statistic.fxml"));
            AnchorPane MainWindow = (AnchorPane) loader.load();

            mainApp.getRootLayout().setCenter(MainWindow);

            StatisticController statisticController = loader.getController();
            statisticController.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitEvent(ActionEvent event){
        mainApp.getPrimaryStage().close();
    }


}
