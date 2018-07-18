package com.dragons.carmenddragonsinventory;

import android.location.Location;

import java.io.Serializable;

/**
 * This class hold the methods and variables for a Point of Sale
 */
public class POS implements Serializable {
   private InventoryCreature model;
   private String location;//Sales location
   private Double price; //sales price
   private Double profit; //profit
   private String db_loc;
   private String name;

   //default constructor
   public POS(){

   }

   /**
    * non-default constructor takes an inventory creature, a location of the point of sale and the
    * sales price
    * @param aModel InventoryCreature
    * @param aLocation Location of sale
    * @param aPrice Price the item sold for
    */
   public POS(InventoryCreature aModel, String aLocation, Double aPrice) {
      model = aModel;
      this.location = aLocation;
      this.price = aPrice;
      this.profit = calcaProfit();

   }

   //getters and setters
   public String getLocation(){ return location;}

   public void setLocation(String location) {this.location = location;}

   public InventoryCreature getModel() {
      return model;
   }

   public void setModel(InventoryCreature model) {
      this.model = model;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Double getProfit() {
      return profit;
   }

   public void setProfit(Double profit) {
      this.profit = profit;
   }

    public String getDb_loc() {
        return db_loc;
    }

    public void setDb_loc(String db_loc) {
        this.db_loc = db_loc;
    }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   /**
    *  This function calculates The profit produced from a sale
    * @return a profit
    */
   private Double calcaProfit(){

      Double ctp = model.getCostToProduce();

      Double prof = price - ctp;

      return Math.round(prof * 100) / 100.0;
   }
}