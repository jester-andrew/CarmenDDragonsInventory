package com.dragons.carmenddragonsinventory;

public class InventoryCreature {

    //member variables
    private String name;
    private String color;
    private String item;
    private double costToProduce;
    private double listPrice;
    private int stock;
    private String imgFilePath;

    //constructor
    public InventoryCreature(String name, String color, String item, float costToProduce,
                             float listPrice, int stock, String imgFilePath){
        this.name = name;
        this.color = color;
        this.item = item;
        this.costToProduce = costToProduce;
        this.listPrice = listPrice;
        this.stock = stock;
        this.imgFilePath = imgFilePath;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getItem() {
        return item;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public float getCostToProduce() {
        return costToProduce;
    }

    public float getListPrice() {
        return listPrice;
    }

    public int getStock() {
        return stock;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    public void setCostToProduce(float costToProduce) {
        this.costToProduce = costToProduce;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
