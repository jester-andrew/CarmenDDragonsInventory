package com.dragons.carmenddragonsinventory;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class insertInventoryCreature {
    //member variables
    private InventoryCreature creature;
    private String location;
    private static String TAG = "insertInventoryCreature Class";

    /**************************************************************
     * constructors
     *************************************************************/

    //default constructor
    public insertInventoryCreature(){

        this.creature = null;
        this.location = "void";

        Log.i(TAG, "Created new instance of insertInventoryCreature in default constructor");

    }

    //non-default constructor
    public insertInventoryCreature(InventoryCreature creature, String location){

        this.creature = creature;
        this.location = location;
        Log.i(TAG, "Created new instance of insertInventoryCreature in non-default constructor");
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
    public void insertInventoryCreature() {

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("inventory/" + location);

            myRef.push().setValue(creature);

        } catch (Exception e) {
            Log.e(TAG, "Didn't insert info correctly into the database in: insertInventoryCreature()");
        }
    }

    /**************************************************************
     * insertInventoryCreature : with parameters being passed to it
     *************************************************************/
    public void insertInventoryCreature(InventoryCreature c, String location){
    try {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("inventory/" + location);

        myRef.push().setValue(c);
    }catch(Exception e){

        Log.e(TAG, "Didn't insert info correctly into the database in: insertInventoryCreature(InventoryCreature param1, String param2)");

    }

    }
}
