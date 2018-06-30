package com.dragons.carmenddragonsinventory;

import android.util.Log;

public class InventoryCreature {

    //member variables
    private String name;
    private String color;
    private String item;
    private double costToProduce;
    private double listPrice;
    private Integer stock;
    private String imgFilePath;
    private static String  TAG = "Inventory Creature Class";

    //constructor
    public InventoryCreature(){

    }

    public InventoryCreature(String name, String color, String item, double costToProduce,
                             double listPrice, Integer stock, String imgFilePath){
        this.name = name;
        this.color = color;
        this.item = item;
        this.costToProduce = costToProduce;
        this.listPrice = listPrice;
        this.stock = stock;
        this.imgFilePath = imgFilePath;

        Log.i(TAG, "InventoryCreature created with " + name + " " + color + " "+ item + " "+
                costToProduce + " "+ listPrice + " "+ stock + " "+ imgFilePath);
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

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
