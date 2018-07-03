package com.dragons.carmenddragonsinventory;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * The insertInventoryCreature class provides the methods nesesary to insert an InventoryCreature
 * into the Firebase database.
 */
public class insertInventoryCreature {
    //member variables
    private InventoryCreature creature;
    private String location;
    private static String TAG = "insertInventoryCreature Class";


    /**
     * Default constructor sets creature to null and location to "void" you would then have to set
     * these values or pass the values to the the insertInventoryCreature() function.
     */
    public insertInventoryCreature(){

        this.creature = null;
        this.location = "void";

        Log.i(TAG, "Created new instance of insertInventoryCreature in default constructor");

    }

    /**
     * The Non-default constructor takes the values and sets them. If this constructor is used you
     * can call the insertInventoryCreature() function without sending it parameters.
     * @param creature
     * @param location
     */
    public insertInventoryCreature(InventoryCreature creature, String location){

        this.creature = creature;
        this.location = location;
        Log.i(TAG, "Created new instance of insertInventoryCreature in non-default constructor");
    }


    /**
     * sets the creature. This method expects and InventoryCreature to be passed to it.
     * @param creature
     */
    public void setCreature(InventoryCreature creature) {
        this.creature = creature;
    }

    /**
     * This setter requests a string location for where it will be placed into the database
     * @param location
     */
    public void setLocation(String location) { this.location = location; }

    /**************************************************************
     * getters
     *************************************************************/
    public InventoryCreature getCreature() {
        return creature;
    }
    public String getLocation(){ return location;  }


    /**
     * This insertInventoryCreature() takes no parameters and inserts the saved InventoryCreature
     * into the Firebase database.
     */
    public void insertInventoryCreature() {

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("inventory/" + location);

            myRef.push().setValue(creature);

        } catch (Exception e) {
            Log.e(TAG, "Didn't insert info correctly into the database in: insertInventoryCreature()");
        }
    }

    /**
     * This insertInventoryCreature() method takes two parameters an InventoryCreature and a
     * location and directly inserts it into the Firebase database.
     * @param c
     * @param location
     */
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
