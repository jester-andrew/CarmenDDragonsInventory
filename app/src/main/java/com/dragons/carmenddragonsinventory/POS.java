package com.dragons.carmenddragonsinventory;

import android.location.Location;

public class POS {

   private String location;//Sales location
   private Integer price; //sales price
   private Integer profit; //profit

   //default constructor
   POS() {
     String location = "Nowhere";
     Integer price = 0;
     Integer profit = 0;
   }

   //non-default constructor
   POS(String location, Integer price, Integer profit) {
      this.location = location;
      this.price = price;
      this.profit = profit;

   }

   //getters and setters
   public String getLocation(){ return location;}
   public Integer getPrice(){ return price;}
   public Integer getProfit(){ return profit;}
   public void setLocation(String location) {this.location = location;}
   public void setPrice(Integer price) {this.price = price;}
   public void setLocation(Integer profit) {this.profit = profit;}
}