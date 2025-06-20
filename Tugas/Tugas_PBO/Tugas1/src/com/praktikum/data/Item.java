package com.praktikum.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    private final StringProperty itemName;
    private final StringProperty description;
    private final StringProperty location;
    private final StringProperty status;

    // Constructor
    public Item(String itemName, String description, String location, String status) {
        this.itemName = new SimpleStringProperty(itemName);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.status = new SimpleStringProperty(status);
    }

    // Getter biasa
    public String getItemName() {
        return itemName.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getLocation() {
        return location.get();
    }

    public String getStatus() {
        return status.get();
    }

    // Setter biasa
    public void setItemName(String value) {
        itemName.set(value);
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public void setLocation(String value) {
        location.set(value);
    }

    public void setStatus(String value) {
        status.set(value);
    }

    // Property getters untuk TableView
    public StringProperty itemNameProperty() {
        return itemName;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty statusProperty() {
        return status;
    }
}
