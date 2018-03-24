package com.library.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Класс-модель для Publisher.
 *
 */
public class BooksInLibrary {

    private final StringProperty bookTitle;
    private final StringProperty nameLibrary;
    private final IntegerProperty numberOfBook;
    /**
     * Конструктор по умолчанию.
     */
    public BooksInLibrary() {
        this(null);
    }

    public BooksInLibrary(String bookTitle) {
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.nameLibrary = new SimpleStringProperty("");
        this.numberOfBook = new SimpleIntegerProperty(1);
    }

    public String getTitle() {
        return bookTitle.get();
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public void setTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }
    public String getBookTitle() {
        return bookTitle.get();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public String getNameLibrary() {
        return nameLibrary.get();
    }

    public StringProperty nameLibraryProperty() {
        return nameLibrary;
    }

    public void setNameLibrary(String nameLibrary) {
        this.nameLibrary.set(nameLibrary);
    }

    public int getNumberOfBook() {
        return numberOfBook.get();
    }

    public IntegerProperty numberOfBookProperty() {
        return numberOfBook;
    }

    public void setNumberOfBook(int numberOfBook) {
        this.numberOfBook.set(numberOfBook);
    }
}