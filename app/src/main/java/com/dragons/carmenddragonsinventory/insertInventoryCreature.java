package com.dragons.carmenddragonsinventory;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class insertInventoryCreature {
    //member variables
    private InventoryCreature creature;
    private String location;

    /**************************************************************
     * constructors
     *************************************************************/

    //default constructor
    public insertInventoryCreature(){

        this.creature = null;
        this.location = "void";

    }

    //non-default constructor
    public insertInventoryCreature(InventoryCreature creature, String location){

        this.creature = creature;
        this.location = location;

    }

    /**************************************************************
     * setters
     *************************************************************/
    public void setCreature(InventoryCreature creature) {
        this.creature = creature;
    }

    public void setLocation(String location) { this.location = location; }

    /**************************************************************
     * getters
     *************************************************************/
    public InventoryCreature getCreature() {
        return creature;
    }
    public String getLocation(){ return location;  }

    /**************************************************************
     * insertInventoryCreature : Inserts a new inventory creature into the
     * firebase database
     *************************************************************/
    public void insertInventoryCreature(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("inventory/" + location);

        myRef.push().setValue(creature);

    }

    /**************************************************************
     * insertInventoryCreature : with parameters being passed to it
     *************************************************************/
    public void insertInventoryCreature(InventoryCreature c, String location){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("inventory/" + location);

        myRef.push().setValue(c);

    }
}
