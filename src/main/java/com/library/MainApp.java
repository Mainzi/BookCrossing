package com.library;


import com.library.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library");

        initRootLayout();
        showMainWindow();

    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("BookFinder");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public boolean showMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/MainWindow.fxml"));
            AnchorPane MainWindow = (AnchorPane) loader.load();

            rootLayout.setCenter(MainWindow);

            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * Конструктор
     */
    public MainApp() {
        // В качестве образца добавляем некоторые данные
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showAlert(String title, String contentText) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
