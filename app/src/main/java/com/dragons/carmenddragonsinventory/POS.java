package com.dragons.carmenddragonsinventory;

import android.location.Location;

import java.io.Serializable;

public class POS implements Serializable {
   private InventoryCreature model;
   private String location;//Sales location
   private Double price; //sales price
   private Double profit; //profit
   private String db_loc;

   POS(){

   }
   //non-default constructor
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

   /**
    *  This function calculates The profit produced from a sale
    * @return a profit
    */
   private Double calcaProfit(){

      Double ctp = model.getCostToProduce();

      Double prof = price - ctp;

      return prof;
   }
}