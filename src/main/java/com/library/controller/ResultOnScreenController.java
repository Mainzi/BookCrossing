package com.library.controller;

import com.library.MainApp;
import com.library.database.DatabaseLibrary;
import com.library.model.Book;
import com.library.util.MyString;
import com.library.util.XmlFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultOnScreenController {
    @FXML
    protected TableView<Book> bookTable;
    @FXML
    protected TableColumn<Book, String> titleColumn;
    @FXML
    protected Label titleLabel;
    @FXML
    protected Label descriptionLabel;
    @FXML
    protected Label yearLabel;
    @FXML
    protected Label authorsLabel;
    @FXML
    protected Label categoryLabel;
    @FXML
    protected Label publisherLabel;
    @FXML
    protected Label libraryLabel;

    private String title;
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> publishers = new ArrayList<>();
    private ArrayList<String> libraries = new ArrayList<>();
    private ArrayList<String> categories = new ArrayList<>();
    private Stage resultOnScreenController;
    private ObservableList<Book> booksResult = FXCollections.observableArrayList();

    //DatabaseLibrary databaseLibrary = new DatabaseLibrary();

    public ResultOnScreenController() {

    }

    @FXML
    private void initialize() {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        bookTable.setItems(booksResult);
        // Очистка дополнительной информации об адресате.
        showBookDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    void initialize(String title, ArrayList<String> authors, ArrayList<String> publishers, ArrayList<String> libraries, ArrayList<String> categories) {
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.libraries = libraries;
        this.categories = categories;

        DatabaseLibrary databaseLibrary = new DatabaseLibrary();
        Connection connection = databaseLibrary.getConnection();
        findBooksInDB(connection);
        databaseLibrary.closeConnection(connection);
    }

    private void findBooksInDB(Connection connection) {

        /*
         1 - title
         2 - author_name
         3 - category_name
         4 - publisher_name
         5 - library_name
         */
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT " +
                                "  book.title                            AS title, " +
                                "  string_agg(DISTINCT(author.author_name), ',')   AS author_name, " +
                                "  book.description                      AS description, " +
                                "  book.year                             AS year, " +
                                "  category.category_name                AS category_name, " +
                                "  publisher.publisher_name              AS publisher_name, " +
                                "  string_agg(DISTINCT(library.library_name), ',') AS library_name " +
                                "FROM book, author, book_author, category, book_category, library_book, library, publisher " +
                                "WHERE book.book_id = book_author.book_id " +
                                "      AND book_author.author_id = author.author_id " +
                                "      AND book.book_id = book_category.book_id " +
                                "      AND book_category.category_id = category.category_id " +
                                "      AND book.publisher_id = publisher.publisher_id " +
                                "      AND book.book_id = library_book.book_id " +
                                "      AND library_book.library_id = library.library_id " +
                                "      AND book.title ILIKE ?" +
                                "      AND author.author_name ILIKE ANY (?) " +
                                "      AND category.category_name ILIKE ANY (?) " +
                                "      AND publisher.publisher_name ILIKE ANY (?) " +
                                "      AND library.library_name ILIKE ANY (?) " +
                                "GROUP BY (title, description, year, category_name, publisher_name)")) {

            preparedStatement.setString(1, "%" + title + "%");
            if (authors.isEmpty()) {
                authors.add("%");
            }
            preparedStatement.setArray(2, new MyString(authors));
            if (categories.isEmpty()) {
                categories.add("%");
            }
            preparedStatement.setArray(3, new MyString(categories));
            if (publishers.isEmpty())
                publishers.add("%");
            preparedStatement.setArray(4, new MyString(publishers));
            if (libraries.isEmpty())
                libraries.add("%");
            preparedStatement.setArray(5, new MyString(libraries));
            //System.out.println(preparedStatement.toString());
            ArrayList<Book> books = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                books.add(new Book(""));
                books.get(books.size() - 1).setTitle(resultSet.getString(1));
                books.get(books.size() - 1).setAuthors(resultSet.getString(2).split(","));
                books.get(books.size() - 1).setDescription(resultSet.getString(3));
                books.get(books.size() - 1).setYear(resultSet.getInt(4));
                books.get(books.size() - 1).setCategory(resultSet.getString(5));
                books.get(books.size() - 1).setPublisher(resultSet.getString(6));
                books.get(books.size() - 1).setLibraries(resultSet.getString(7).split(","));
                booksResult.add(books.get(books.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }


    }

    private void showBookDetails(Book book) {
        if (book != null) {
            // Заполняем метки информацией из объекта book.
            titleLabel.setText(book.getTitle());
            descriptionLabel.setText(book.getDescription());
            yearLabel.setText(book.getYearString());
            authorsLabel.setText(book.getAuthorsString());
            categoryLabel.setText(String.valueOf(book.getCategory()));
            publisherLabel.setText(book.getPublisher());
            libraryLabel.setText(book.getLibrariesString());
        } else {
            // Если book = null, то убираем весь текст.
            titleLabel.setText("");
            descriptionLabel.setText("");
            yearLabel.setText("");
            authorsLabel.setText("");
            categoryLabel.setText("");
            publisherLabel.setText("");
            libraryLabel.setText("");
        }
    }

    @FXML
    public void resultInXML() {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Save Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(resultOnScreenController);//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            XmlFormatter xmlFormatter = new XmlFormatter();
            xmlFormatter.saveBooksInXML(booksResult, file.toString());
        }
    }

    void setResultOnScreenController(Stage resultOnScreenController) {
        this.resultOnScreenController = resultOnScreenController;
    }
    MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}