package com.library.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


/**
 * Класс-модель для Book.
 */
public class Book {

    private StringProperty title = null;
    private ArrayList<StringProperty> authors = new ArrayList<>();
    private StringProperty description = null;
    private IntegerProperty year = null;
    private StringProperty category = null;
    private StringProperty publisher = null;
    private ArrayList<StringProperty> libraries = new ArrayList<>();

    /**
     * Конструктор по умолчанию.
     */
    public Book(String title) {
        if (title.length() != 0)
            this.title = new SimpleStringProperty(title);
        else
            this.title = new SimpleStringProperty("");
        // Какие-то фиктивные начальные данные для удобства тестирования.
        this.description = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
        this.publisher = new SimpleStringProperty("");
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public ArrayList<StringProperty> getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        for (String a :
                authors) {
            this.authors.add(new SimpleStringProperty(a.trim()));
        }
    }

    public String getAuthorsString() {
        String result = "";
        for (int i = 0; i < authors.size() - 1; i++) {
            result += authors.get(i).get() + ", ";
        }
        result += authors.get(authors.size() - 1).get();
        return result;
    }

    public ArrayList<String> getAuthorsArrayList() {
        ArrayList<String> result = new ArrayList<>();
        for (StringProperty a :
                authors) {
            result.add(a.get());
        }

        return result;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public ArrayList<StringProperty> getLibraries() {
        return libraries;
    }

    public void setLibraries(String[] library) {
        for (String l :
                library) {
            this.libraries.add(new SimpleStringProperty(l.trim()));
        }
    }

    public String getLibrariesString() {
        String result = "";
        for (int i = 0; i < libraries.size() - 1; i++) {
            result += libraries.get(i).get() + ", ";
        }
        result += libraries.get(libraries.size() - 1).get();
        return result;
    }

    public ArrayList<String> getLibrariesArrayList() {
        ArrayList<String> result = new ArrayList<>();
        for (StringProperty a :
                libraries) {
            result.add(a.get());
        }

        return result;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year = new SimpleIntegerProperty(year);
    }

    public String getYearString() {

        String yearString = this.year.toString();
        // yearString
        return yearString.substring(24, yearString.length() - 1);
    }

    public IntegerProperty yearProperty() {
        return year;
    }
}