package com.dragons.carmenddragonsinventory;

import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

/**
* The InventoryCreature class creates a new instance of a creature.
*/
public class InventoryCreature implements Serializable{

    //member variables
    private String name;
    private String color;
    private String item;
    private double costToProduce;
    private double listPrice;
    private Integer stock;
    private String imgFilePath;
    private static String  TAG = "Inventory Creature Class";
    private DatabaseReference db_cat;

    //constructor

    InventoryCreature(){

    }

    /**
    * The non-default constructor takes the parameters associated with an InventoryCreature.
    * @param name name of the item
    * @param color color variance of item
    * @param item  the item that is integral to the main item
    * @param costToProduce cost to create the item
    * @param listPrice regular sale price.
    * @param stock how many of the item are in inventory
    * @param imgFilePath the Firestore location of the item that
    */
    InventoryCreature(String name, String color, String item, double costToProduce,
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

    /**
    * the filepath to where the image will be stored in the cloud
    * @param imgFilePath string of filepath....
    */
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



    public DatabaseReference getDb_cat() {
        return db_cat;
    }

    public void setDb_cat(DatabaseReference db_cat) {
        this.db_cat = db_cat;
    }
}
