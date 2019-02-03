package nl.hsleiden.model;


import nl.hsleiden.persistence.ItemDAO;

public class Item {
    private int itemCode;
    private String name;
    private double price;
    private String description;
    private String image;

    public Item(int itemCode, String name, double price, String description, String image) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public Item(){
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }
}



