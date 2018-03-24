package com.library.controller;

import com.library.MainApp;
import com.library.database.DatabaseLibrary;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatisticController {
    @FXML
    protected TableView<BookToCategory> bookToCategoryTable;
    @FXML
    protected TableColumn<BookToCategory, String> categoryColumn;
    @FXML
    protected TableColumn<BookToCategory, String> numberInCategoryColumn;
    @FXML
    protected TableView<BookToLibrary> bookToLibraryTable;
    @FXML
    protected TableColumn<BookToLibrary, String> libraryColumn;
    @FXML
    protected TableColumn<BookToLibrary, String> numberInLibraryColumn;
    MainApp mainApp;
    DatabaseLibrary databaseLibrary = new DatabaseLibrary();
    private ObservableList<BookToCategory> categoryResult = FXCollections.observableArrayList();
    private ObservableList<BookToLibrary> libraryResult = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_books_number_in_library");
            ArrayList<BookToLibrary> booksToLibrary = new ArrayList<>();
            while (resultSet.next()) {
                booksToLibrary.add(new BookToLibrary());
                booksToLibrary.get(booksToLibrary.size() - 1).
                        setLibrary(new SimpleStringProperty(resultSet.getString(1)));
                booksToLibrary.get(booksToLibrary.size() - 1).
                        setNumber(resultSet.getInt(2));

            }

            statement.close();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM get_books_number_in_category");
            ArrayList<BookToCategory> booksToCategory = new ArrayList<>();
            while (resultSet.next()) {
                booksToCategory.add(new BookToCategory());
                booksToCategory.get(booksToCategory.size() - 1).
                        setCategory(new SimpleStringProperty(resultSet.getString(1)));
                booksToCategory.get(booksToCategory.size() - 1).
                        setNumber(resultSet.getInt(2));
            }

            categoryResult.addAll(booksToCategory);
            libraryResult.addAll(booksToLibrary);
        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }

        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());
        numberInCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().getNumber());

        libraryColumn.setCellValueFactory(cellData -> cellData.getValue().getLibrary());
        numberInLibraryColumn.setCellValueFactory(cellData -> cellData.getValue().getNumber());

        bookToCategoryTable.setItems(categoryResult);
        bookToLibraryTable.setItems(libraryResult);
    }

    @FXML
    //Возврат в главное меню
    private void back(ActionEvent mouseEvent) {
        mainApp.showMainWindow();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private class BookToCategory {
        private StringProperty category;
        private Integer number;

        public BookToCategory() {
            category = new SimpleStringProperty("");
            number = 0;
        }

        public StringProperty getCategory() {
            return category;
        }

        public void setCategory(StringProperty category) {
            this.category = category;
        }

        public StringProperty getNumber() {
            return new SimpleStringProperty(number.toString());
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

    private class BookToLibrary {
        private StringProperty library;
        private Integer number;

        public BookToLibrary() {
            library = new SimpleStringProperty();
            number = 0;
        }

        public StringProperty getLibrary() {
            return library;
        }

        public void setLibrary(StringProperty library) {
            this.library = library;
        }

        public StringProperty getNumber() {
            return new SimpleStringProperty(number.toString());
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

}