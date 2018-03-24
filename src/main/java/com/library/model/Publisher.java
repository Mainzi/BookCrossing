package com.library.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Класс-модель для Publisher.
 *
 */
public class Publisher {

    private final StringProperty name;

    /**
     * Конструктор по умолчанию.
     */
    public Publisher() {
        this(null);
    }

    public Publisher(String name) {
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