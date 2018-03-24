package com.library.controller;

import com.library.MainApp;
import com.library.database.DatabaseLibrary;
import com.library.model.*;
import com.library.util.MyString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;

public class AddDataController {
    @FXML
    protected TableView<BooksInLibrary> booksInLibraryTable;
    @FXML
    protected TableColumn<BooksInLibrary, String> titleColumn;
    @FXML
    protected TableColumn<BooksInLibrary, String> libraryWithBookColumn;
    @FXML
    protected Label numberOfBooks;
    @FXML
    protected TextField titleField;
    @FXML
    protected TextField yearField;
    @FXML
    protected TextField descriptionField;
    @FXML
    protected TextField numberField;
    @FXML
    protected TextField numberInLibraryField;
    @FXML
    protected MenuButton publisherButton;
    @FXML
    protected MenuButton categoryButton;
    @FXML
    protected MenuButton libraryButton;
    @FXML
    protected MenuButton authorsButton;

    @FXML
    protected TableView<Author> authorTable;
    @FXML
    protected TableColumn<Author, String> authorNameTableColumn;
    @FXML
    protected TextField authorsField;
    @FXML
    protected TableView<Publisher> publisherTable;
    @FXML
    protected TableColumn<Publisher, String> publisherNameTableColumn;
    @FXML
    protected TextField publisherField;
    @FXML
    protected TableView<Category> categoryTable;
    @FXML
    protected TableColumn<Category, String> categoryTableColumn;
    @FXML
    protected TextField categoryField;
    @FXML
    protected TableView<Library> libraryTable;
    @FXML
    protected TableColumn<Library, String> libraryNameTableColumn;
    @FXML
    protected TextField libraryNameField;
    @FXML
    protected TextField libraryAddressField;
    ArrayList<CheckMenuItem> authorsCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> publishersCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> librariesCMI = new ArrayList<>();
    ArrayList<CheckMenuItem> categoriesCMI = new ArrayList<>();
    BooksInLibrary nowBook = null;
    private Selected selected;
    private DatabaseLibrary databaseLibrary = new DatabaseLibrary();
    private MainApp mainApp;
    @FXML
    private Button back;
    @FXML
    private TabPane tabPane;
    private ObservableList<Author> authors = FXCollections.observableArrayList();
    private ObservableList<BooksInLibrary> booksInLibraries = FXCollections.observableArrayList();
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<Library> libraries = FXCollections.observableArrayList();
    private ObservableList<Publisher> publishers = FXCollections.observableArrayList();
    private ArrayList<BooksInLibrary> booksInLibraryArrayList = new ArrayList<>();
    private ArrayList<Author> authorsArrayList = new ArrayList<>();
    private ArrayList<Library> librariesArrayList = new ArrayList<>();
    private ArrayList<Category> categoriesArrayList = new ArrayList<>();
    private ArrayList<Publisher> publishersArrayList = new ArrayList<>();

    @FXML
    private void initialize() {
        back.setOnAction((event) -> {
            try {
                back(event);
            } catch (Exception e) {
            }
        });

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().bookTitleProperty());
        libraryWithBookColumn.setCellValueFactory(cellData -> cellData.getValue().nameLibraryProperty());
        booksInLibraryTable.setItems(booksInLibraries);
        booksInLibraryTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBooksNumber(newValue));

        authorNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        authorTable.setItems(authors);
        libraryNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        libraryTable.setItems(libraries);
        categoryTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        categoryTable.setItems(categories);
        publisherNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        publisherTable.setItems(publishers);

        updateInfo();
    }

    @FXML
    //Возврат в главное меню
    private void back(ActionEvent mouseEvent) {
        mainApp.showMainWindow();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void getBooksInLibrary() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_books_in_library");
            booksInLibraries.clear();
            booksInLibraryArrayList.clear();
            while (resultSet.next()) {
                booksInLibraryArrayList.add(new BooksInLibrary());
                booksInLibraryArrayList.get(booksInLibraryArrayList.size() - 1).setTitle(resultSet.getString(1));
                booksInLibraryArrayList.get(booksInLibraryArrayList.size() - 1).setNameLibrary(resultSet.getString(2));
                booksInLibraryArrayList.get(booksInLibraryArrayList.size() - 1).setNumberOfBook(resultSet.getInt(3));
                booksInLibraries.add(booksInLibraryArrayList.get(booksInLibraryArrayList.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
        showBooksNumber(null);

        try (Connection connection = databaseLibrary.getConnection()) {
            categoryButton.getItems().removeAll(categoryButton.getItems());
            categoriesCMI.clear();
            ArrayList<String> namesCheckMenuItems = databaseLibrary.getCategories(connection);
            for (String n :
                    namesCheckMenuItems) {
                categoriesCMI.add(new CheckMenuItem(n));
            }
            categoryButton.getItems().addAll(categoriesCMI);
            libraryButton.getItems().removeAll(libraryButton.getItems());
            librariesCMI.clear();
            namesCheckMenuItems.clear();
            namesCheckMenuItems = databaseLibrary.getLibraries(connection);
            for (String n :
                    namesCheckMenuItems) {
                librariesCMI.add(new CheckMenuItem(n));
            }
            libraryButton.getItems().addAll(librariesCMI);
            publisherButton.getItems().removeAll(publisherButton.getItems());
            publishersCMI.clear();
            namesCheckMenuItems.clear();
            namesCheckMenuItems = databaseLibrary.getPublishers(connection);
            for (String n :
                    namesCheckMenuItems) {
                publishersCMI.add(new CheckMenuItem(n));
            }
            publisherButton.getItems().addAll(publishersCMI);
            namesCheckMenuItems.clear();
            authorsButton.getItems().removeAll(authorsButton.getItems());
            authorsCMI.clear();
            namesCheckMenuItems = databaseLibrary.getAuthors(connection);
            for (String n :
                    namesCheckMenuItems) {
                authorsCMI.add(new CheckMenuItem(n));
            }
            authorsButton.getItems().addAll(authorsCMI);
            namesCheckMenuItems.clear();
            databaseLibrary.closeConnection(connection);
        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }

    }

    @FXML
    public void getAuthors() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_authors");
            authorsArrayList.clear();
            authors.clear();
            while (resultSet.next()) {
                authorsArrayList.add(new Author());
                authorsArrayList.get(authorsArrayList.size() - 1).setName(resultSet.getString(1));
                authors.add(authorsArrayList.get(authorsArrayList.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
    }

    @FXML
    public void getLibraries() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_libraries");
            librariesArrayList.clear();
            libraries.clear();
            while (resultSet.next()) {
                librariesArrayList.add(new Library());
                librariesArrayList.get(librariesArrayList.size() - 1).setName(resultSet.getString(1));
                libraries.add(librariesArrayList.get(librariesArrayList.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
    }

    @FXML
    public void getCategory() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_categories");
            categoriesArrayList.clear();
            categories.clear();
            while (resultSet.next()) {
                categoriesArrayList.add(new Category());
                categoriesArrayList.get(categoriesArrayList.size() - 1).setName(resultSet.getString(1));
                categories.add(categoriesArrayList.get(categoriesArrayList.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
    }

    @FXML
    public void getPublishers() {
        try (Connection connection = databaseLibrary.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM get_publishers");
            publishersArrayList.clear();
            publishers.clear();
            while (resultSet.next()) {
                publishersArrayList.add(new Publisher());
                publishersArrayList.get(publishersArrayList.size() - 1).setName(resultSet.getString(1));
                publishers.add(publishersArrayList.get(publishersArrayList.size() - 1));
            }

        } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
    }

    @FXML
    public void updateNumberOfBook() {
        if (nowBook != null) {
            if (numberInLibraryField.getText().matches("[-+]?\\d+")) {
                int numberOfBooks = Integer.parseInt(numberInLibraryField.getText());
                if (numberOfBooks > 0) {
                    try (Connection connection = databaseLibrary.getConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE library_book " +
                                "SET nubmer_of_book = (?) " +
                                "WHERE library_id = (SELECT library_id " +
                                "                    FROM library " +
                                "                    WHERE library_name = (?)) " +
                                "      AND book_id = (SELECT book_id " +
                                "                     FROM book " +
                                "                     WHERE title = (?))");
                        preparedStatement.setInt(1, numberOfBooks);
                        preparedStatement.setString(2, nowBook.getNameLibrary());
                        preparedStatement.setString(3, nowBook.getBookTitle());
                        preparedStatement.execute();
                        nowBook.setNumberOfBook(numberOfBooks);
                        showBooksNumber(nowBook);
                    } catch (SQLException e) {
                        mainApp.showAlert("Ошибка базы данных",e.getMessage());
                    }
                } else {
                    mainApp.showAlert("Книг меньше одной?", "Запишите число > 0");
                }
            } else {
                mainApp.showAlert("Неверное количество книг", "Запишите число > 0");
            }
        } else {
            mainApp.showAlert("Какая книга?", "Выберите книгу");
        }
    }

    @FXML
    public void addBookInLibrary() {
        String errorMessage = "";
        String title;
        ArrayList<String> authorsSelected = new ArrayList<>();
        ArrayList<String> categorySelected = new ArrayList<>();
        ArrayList<String> publishersSelected = new ArrayList<>();
        ArrayList<String> librariesSelected = new ArrayList<>();
        int book_id = 0;
        int category_id = 0;
        int publisher_id = 0;
        int library_id = 0;
        ArrayList<Integer> authors_id = new ArrayList<>();
        String description;
        int number = 0;
        int year = 0;
        for (CheckMenuItem a :
                authorsCMI) {
            if (a.isSelected())
                authorsSelected.add(a.getText());
        }
        for (CheckMenuItem c :
                categoriesCMI) {
            if (c.isSelected())
                categorySelected.add(c.getText());
        }
        for (CheckMenuItem p :
                publishersCMI) {
            if (p.isSelected())
                publishersSelected.add(p.getText());
        }
        for (CheckMenuItem l :
                librariesCMI) {
            if (l.isSelected())
                librariesSelected.add(l.getText());
        }
        title = titleField.getText();
        if (title == null || title.isEmpty()) errorMessage += "Введите название книги!\n";

        if (authorsSelected.size() == 0) errorMessage += "Выберите хотя бы одного автора!\n";
        if (publishersSelected.size() != 1) errorMessage += "Выберите одно издательство!\n";
        if (categorySelected.size() != 1) errorMessage += "Выберите одну категорию!\n";
        if (librariesSelected.size() != 1) errorMessage += "Выберите одну библиотеку!\n";

        if (numberField.getText().matches("[-+]?\\d+")) {
            number = Integer.parseInt(numberField.getText());
            if (number <= 0) errorMessage += "Введите количество книг > 0\n";
        } else {
            errorMessage += "Введите количество книг!\n";
        }
        if (yearField.getText().matches("[-+]?\\d+")) {
            year = Integer.parseInt(yearField.getText());
            if (year < 0 && year > 2100) errorMessage += "Введите год настоящий год > 0\n";
        } else {
            errorMessage += "Введите год!\n";
        }
        description = descriptionField.getText();
        if (errorMessage.length() == 0) {
            try (Connection connection = databaseLibrary.getConnection()) {
                PreparedStatement getPublisher = connection.prepareStatement("SELECT publisher_id " +
                        "FROM publisher WHERE publisher_name =(?)");
                getPublisher.setString(1, publishersSelected.get(0));
                ResultSet rs = getPublisher.executeQuery();
                if (rs.next()) {
                    publisher_id = rs.getInt(1);
                } else {
                    mainApp.showAlert("Издание отсутствует","Попробуйте еще раз");
                    return;
                }
                getPublisher.close();

                PreparedStatement getLibrary = connection.prepareStatement("SELECT library_id" +
                        " FROM library WHERE library_name = (?)");
                getLibrary.setString(1, librariesSelected.get(0));
                rs = getLibrary.executeQuery();
                if (rs.next()) {
                    library_id = rs.getInt(1);
                } else {
                    mainApp.showAlert("Библиотека отсутствует","Попробуйте добавить еще раз");
                    return;
                }
                getLibrary.close();

                PreparedStatement getCategory = connection.prepareStatement("SELECT category_id" +
                        " FROM category WHERE category_name = (?)");
                getCategory.setString(1, categorySelected.get(0));
                rs = getCategory.executeQuery();
                if (rs.next()) {
                    category_id = rs.getInt(1);
                } else {
                    mainApp.showAlert("Категория отсутствует","Попробуйте добавить еще раз");
                    return;
                }
                getCategory.close();

                PreparedStatement getAuthors = connection.prepareStatement("SELECT author_id" +
                        " FROM author WHERE author_name = (?)");
                for (int i = 0; i < authorsSelected.size(); i++) {
                    getAuthors.setString(1, authorsSelected.get(0));
                    rs = getAuthors.executeQuery();
                    if (rs.next()) {
                        authors_id.add(rs.getInt(1));
                    }
                }
                getAuthors.close();

            } catch (SQLException e) {
                mainApp.showAlert("Ошибка базы данных",e.getMessage());
            }

            if(checkBook(title,authorsSelected, categorySelected.get(0), publishersSelected.get(0),
                    librariesSelected.get(0), description,year)){
                mainApp.showAlert("Ошибка","Такая книга уже лежит в этой библиотеке");
                return;
            }

            try (Connection connection = databaseLibrary.getConnection()) {
                try {


                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.
                            prepareStatement("INSERT INTO book (title, description, year, publisher_id) VALUES (?,?,?,?)"
                                    , new String[]{"book_id"});

                    preparedStatement.setString(1, title);
                    preparedStatement.setString(2, description);
                    preparedStatement.setInt(3, year);
                    preparedStatement.setInt(4, publisher_id);
                    preparedStatement.executeUpdate();

                    ResultSet resultSet = preparedStatement.getGeneratedKeys();

                    if (resultSet.next()) {
                        book_id = resultSet.getInt(1);
                    }
                    preparedStatement.close();
                    preparedStatement = connection.
                            prepareStatement("INSERT INTO book_category (book_id, category_id) VALUES (?,?)");
                    preparedStatement.setInt(1, book_id);
                    preparedStatement.setInt(2, category_id);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    preparedStatement = connection.
                            prepareStatement("INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (?,?,?)");
                    preparedStatement.setInt(1, library_id);
                    preparedStatement.setInt(2, book_id);
                    preparedStatement.setInt(3, number);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    preparedStatement = connection.
                            prepareStatement("INSERT INTO book_author (book_id, author_id) VALUES (?,?)");
                    for (int i = 0; i < authors_id.size(); i++) {
                        preparedStatement.setInt(1, book_id);
                        preparedStatement.setInt(2, authors_id.get(i));
                        preparedStatement.executeUpdate();
                    }
                    preparedStatement.close();
                    // Завершаем транзакцию - подтверждаем
                    connection.commit();
                    // Возвращаем свойство AutoCommit в true
                    connection.setAutoCommit(true);
                    BooksInLibrary newBook = new BooksInLibrary();
                    newBook.setTitle(title);
                    newBook.setNumberOfBook(number);
                    newBook.setNameLibrary(librariesSelected.get(0));
                    booksInLibraries.add(0,newBook);
                } catch (SQLException e) {
                    connection.rollback();
                    mainApp.showAlert("Ошибка базы данных",e.getMessage());
                }
            } catch (SQLException e) {
                mainApp.showAlert("Ошибка базы данных",e.getMessage());
            }

        } else {
            mainApp.showAlert("Введите правильные данные",errorMessage);
        }
    }

    @FXML
    public void addAuthors() {
        String authorName = authorsField.getText();
        Boolean newAuthor = true;
        for (Author a :
                authorsArrayList) {
            if (authorName.equals(a.getName())) {
                newAuthor = false;
                break;
            }
        }
        if (authorName != null && !authorName.isEmpty() && newAuthor) {
            try (Connection connection = databaseLibrary.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO author (author_name) VALUES (?)");
                preparedStatement.setString(1, authorName);
                preparedStatement.execute();
                authorsArrayList.add(new Author());
                authorsArrayList.get(authorsArrayList.size() - 1).setName(authorName);
                authors.add(authorsArrayList.get(authorsArrayList.size() - 1));
            } catch (SQLException e) {
                mainApp.showAlert("Ошибка базы данных",e.getMessage());
            }
        } else {
            if (!newAuthor)
                mainApp.showAlert("Неверные данные","Пожалуйста введите нового автора");
            else
                mainApp.showAlert("Неверные данные","Поалуйста, введите автора");
        }
    }

    @FXML
    public void addCategory() {
        String categoryName = categoryField.getText();
        Boolean newCategory = true;
        for (Category category :
                categoriesArrayList) {
            if (categoryName.equals(category.getName())) {
                newCategory = false;
                break;
            }
        }
        if (categoryName != null && !categoryName.isEmpty() && newCategory) {
            try (Connection connection = databaseLibrary.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO category (category_name) VALUES (?)");
                preparedStatement.setString(1, categoryName);
                preparedStatement.execute();
                categoriesArrayList.add(new Category());
                categoriesArrayList.get(categoriesArrayList.size() - 1).setName(categoryName);
                categories.add(categoriesArrayList.get(categoriesArrayList.size() - 1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (!newCategory)
                mainApp.showAlert("Неверные данные","Пожалуйста, введите новую категорию");
            else
                mainApp.showAlert("Неверные данные","Пожалуйста, введите категорию");
        }
    }

    @FXML
    public void addLibrary() {
        String libraryName = libraryNameField.getText();
        String libraryAddress = libraryAddressField.getText();
        Boolean newLibrary = true;
        for (Library library :
                librariesArrayList) {
            if (libraryName.equals(library.getName())) {
                newLibrary = false;
                break;
            }
        }

        if (libraryAddress != null && !libraryAddress.isEmpty()) {
            if (libraryName != null && !libraryName.isEmpty() && newLibrary) {
                try (Connection connection = databaseLibrary.getConnection()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO library (library_name,adress) VALUES (?,?)");
                    preparedStatement.setString(1, libraryName);
                    preparedStatement.setString(2, libraryAddress);
                    preparedStatement.execute();
                    librariesArrayList.add(new Library());
                    librariesArrayList.get(librariesArrayList.size() - 1).setName(libraryName);
                    libraries.add(librariesArrayList.get(librariesArrayList.size() - 1));
                } catch (SQLException e) {
                    mainApp.showAlert("Ошибка базы данных",e.getMessage());
                }
            } else {
                if (!newLibrary)
                    mainApp.showAlert("Неверные данные","Пожалуйста, введите новое название");
                else
                    mainApp.showAlert("Неверные данные","Пожалуйста, введите название");
            }
        } else {
            mainApp.showAlert("Неверные данные","Пожалуйста, введите адрес");
        }
    }

    @FXML
    public void addPublisher() {
        String publisherName = publisherField.getText();
        Boolean newPublisher = true;
        for (Publisher publisher :
                publishersArrayList) {
            if (publisherName.equals(publisher.getName())) {
                newPublisher = false;
                break;
            }
        }
        if (publisherName != null && !publisherName.isEmpty() && newPublisher) {
            try (Connection connection = databaseLibrary.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publisher (publisher_name) VALUES (?)");
                preparedStatement.setString(1, publisherName);
                preparedStatement.execute();
                publishersArrayList.add(new Publisher());
                publishersArrayList.get(publishersArrayList.size() - 1).setName(publisherName);
                publishers.add(publishersArrayList.get(publishersArrayList.size() - 1));
            } catch (SQLException e) {
                mainApp.showAlert("Ошибка базы данных",e.getMessage());
            }
        } else {
            if (!newPublisher)
              mainApp.showAlert("Неверный данные!", "Пожалуйста, введите новое издательство");
            else
              mainApp.showAlert("Неверный данные!", "Пожалуйста, введите издательство");
        }
    }

    public void updateInfo() {
        Selected buffer = selected;
        for (Tab t :
                tabPane.getTabs()) {
            if (t.isSelected()) {

                switch (t.getText()) {
                    case "Книга":
                        selected = Selected.BOOK;
                        break;
                    case "Автор":
                        selected = Selected.AUTHOR;
                        break;
                    case "Библиотека":
                        selected = Selected.LIBRARY;
                        break;
                    case "Категория":
                        selected = Selected.CATEGORY;
                        break;
                    case "Издательство":
                        selected = Selected.PUBLISHER;
                        break;
                }
            }
        }
        if (selected.equals(buffer)) return;
        switch (selected) {
            case BOOK:
                getBooksInLibrary();
                break;
            case AUTHOR:
                getAuthors();
                break;
            case LIBRARY:
                getLibraries();
                break;
            case CATEGORY:
                getCategory();
                break;
            case PUBLISHER:
                getPublishers();
                break;
        }
    }

    private void showBooksNumber(BooksInLibrary booksInLibrary) {
        nowBook = booksInLibrary;
        if (booksInLibrary != null) {
            numberOfBooks.setText(Integer.toString(booksInLibrary.getNumberOfBook()));
        } else {
            numberOfBooks.setText("");
        }
    }

    private boolean checkBook(String title, ArrayList<String> authors, String category, String publisher,
                              String library, String description, int year){

        try(Connection connection = databaseLibrary.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "  book.title                            AS title " +
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
                            "      AND category.category_name =(?) " +
                            "      AND publisher.publisher_name = (?) " +
                            "      AND library.library_name = (?) " +
                            "      AND book.year = (?) " +
                            "GROUP BY (title, description, year, category_name, publisher_name)");

                preparedStatement.setString(1,title);
                preparedStatement.setArray(2, new MyString(authors));
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, publisher);
                preparedStatement.setString(5, library);
                preparedStatement.setInt(6, year);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    connection.close();
                    return true;
                }
                else{
                    connection.close();
                    return false;
                }
            } catch (SQLException e) {
            mainApp.showAlert("Ошибка базы данных",e.getMessage());
        }
        return true;
    }

    enum Selected {BOOK, AUTHOR, LIBRARY, CATEGORY, PUBLISHER}
}
