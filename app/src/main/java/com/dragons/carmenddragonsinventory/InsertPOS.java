package com.dragons.carmenddragonsinventory;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertPOS {
    //member variable
    private POS sale;
    private String TAG = "InsertPOS class";

    //default constructor
    public InsertPOS(){

    }

    //non-default constructor
    public InsertPOS(POS p){
        this.sale = p;
    }

    //getters and setters
    public POS getSale() {
        return sale;
    }

    public void setSale(POS sale) {
        this.sale = sale;
    }

    //member functions

    /**
     * Inserts a POS(point of sale) object into the Firebase database. Use this if you have
     * already passed the constructor a POS
     */
    public void insert(){

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("sales-history");

            myRef.push().setValue(sale);

        } catch (Exception e) {
            Log.e(TAG, "Didn't insert info correctly into the database in: insert()");
        }

    }

    /**
     * Inserts a POS(point of sale) object into the Firebase database. Use this if you used the
     * default constructor
     * @param s is a sale variable
     */
    public void insert(POS s){

        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("sales-history");

            myRef.push().setValue(s);

        } catch (Exception e) {
            Log.e(TAG, "Didn't insert info correctly into the database in: insert()");
        }

    }
}

