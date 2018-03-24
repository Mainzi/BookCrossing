package com.library.controller;

import com.library.MainApp;
import com.library.database.DatabaseLibrary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class FindBookController {
    @FXML
    protected TextField titleField;
    @FXML
    protected TextField authorsField;
    @FXML
    protected MenuButton publisherButton;
    @FXML
    protected MenuButton categoryButton;
    @FXML
    protected MenuButton libraryButton;
    MainApp mainApp;
    ArrayList<CheckMenuItem> authorsCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> publishersCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> librariesCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> categoriesCMI = new ArrayList<>();
    String title;
    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> publishers = new ArrayList<>();
    ArrayList<String> libraries = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();
    @FXML
    private Button back;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        back.setOnAction((event) -> {
            try {
                back(event);
            } catch (Exception e) {
            }
        });
        categoryButton.getItems().removeAll(categoryButton.getItems());
        libraryButton.getItems().removeAll(libraryButton.getItems());
        publisherButton.getItems().removeAll(publisherButton.getItems());

        DatabaseLibrary databaseLibrary = new DatabaseLibrary();
        Connection connection = databaseLibrary.getConnection();
        ArrayList<String> namesCheckMenuItems = databaseLibrary.getCategories(connection);
        for (String n :
                namesCheckMenuItems) {
            categoriesCMI.add(new CheckMenuItem(n));
        }
        categoryButton.getItems().addAll(categoriesCMI);

        namesCheckMenuItems.clear();
        namesCheckMenuItems = databaseLibrary.getLibraries(connection);
        for (String n :
                namesCheckMenuItems) {
            librariesCMI.add(new CheckMenuItem(n));
        }
        libraryButton.getItems().addAll(librariesCMI);

        namesCheckMenuItems.clear();
        namesCheckMenuItems = databaseLibrary.getPublishers(connection);
        for (String n :
                namesCheckMenuItems) {
            publishersCMI.add(new CheckMenuItem(n));
        }
        publisherButton.getItems().addAll(publishersCMI);
        namesCheckMenuItems.clear();
        databaseLibrary.closeConnection(connection);
    }

    @FXML
    public void resultOnScreen() {
        try {
            DatabaseLibrary databaseLibrary = new DatabaseLibrary();

            getInfo();

            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ResultOnScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            ResultOnScreenController resultOnScreenController = loader.getController();
            resultOnScreenController.initialize(title, authors, publishers, libraries, categories);
            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Книги по вашему запросу");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            resultOnScreenController.setResultOnScreenController(dialogStage);
            resultOnScreenController.setMainApp(mainApp);

        } catch (IOException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
    }

    @FXML
    //Возврат в главное меню
    private void back(ActionEvent mouseEvent) {
        mainApp.showMainWindow();
    }

    private void getInfo() {
        authors.clear();
        categories.clear();
        publishers.clear();
        libraries.clear();

        title = titleField.getText();

        String[] author = authorsField.getText().split(",");
        for (String a : author) {
            a = "%" + a.trim() + "%";
            authors.add(a);
        }
        for (CheckMenuItem c :
                categoriesCMI) {
            if (c.isSelected())
                categories.add(c.getText());
        }
        for (CheckMenuItem p :
                publishersCMI) {
            if (p.isSelected())
                publishers.add(p.getText());
        }
        for (CheckMenuItem l :
                librariesCMI) {
            if (l.isSelected())
                libraries.add(l.getText());
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


}
