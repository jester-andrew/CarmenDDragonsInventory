package com.dragons.carmenddragonsinventory;

public class InventoryCreature {

    //member variables
    private String name;
    private String color;
    private String item;
    private double costToProduce;
    private double listPrice;
    private Integer stock;
    private String imgFilePath;

    //constructor
    public InventoryCreature(){

    }

    public InventoryCreature(String name, String color, String item, double costToProduce,
                             double listPrice, int stock, String imgFilePath){
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

    public Double getCostToProduce() {
        return costToProduce;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public Integer getStock() {
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

    public void setCostToProduce(double costToProduce) {
        this.costToProduce = costToProduce;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
