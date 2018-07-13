package com.dragons.carmenddragonsinventory;

import android.location.Location;

public class POS {
   private InventoryCreature model;
   private String location;//Sales location
   private Double price; //sales price
   private Double profit; //profit

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