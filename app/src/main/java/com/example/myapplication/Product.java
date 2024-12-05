package com.example.myapplication;

public class Product {
    private int id;  // Unique identifier for each product (used in the database)
    private String name;
    private String expiryDate;
    private String category;
    private boolean isFinished;

    // Constructor for adding new products (without ID)
    public Product(String name, String expiryDate, String category) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.category = category;
        this.isFinished = false;
    }

    // Constructor for retrieving products from the database (with ID)
    public Product(int id, String name, String expiryDate, String category, boolean isFinished) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.category = category;
        this.isFinished = isFinished;
    }

    // Getter and setter methods

    public int getId() {
        return id;  // Getter for ID
    }

    public void setId(int id) {
        this.id = id;  // Setter for ID
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
