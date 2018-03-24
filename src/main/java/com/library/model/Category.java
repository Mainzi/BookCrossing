package com.library.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Класс-модель для Author.
 *
 */
public class Category {

    private final StringProperty name;

    /**
     * Конструктор по умолчанию.
     */
    public Category() {
        this(null);
    }

    public Category(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}