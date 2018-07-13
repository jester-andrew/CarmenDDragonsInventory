package com.dragons.carmenddragonsinventory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Sale_Entry_activity extends Activity {
    private InventoryCreature model;
    private String location;
    private Double price;
    private EditText sale_location;
    private EditText sale_price;
    private Button cancel;
    private Button insert_sale;
    private String TAG = "Sale_Entry_activity";

    public Sale_Entry_activity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sales_dialog_layout);
        model = (InventoryCreature) getIntent().getSerializableExtra("model");
        sale_location = findViewById(R.id.sales_location_edit);
        sale_price = findViewById(R.id.sales_price_edit);

        cancel = findViewById(R.id.sales_cancel);
        insert_sale = findViewById(R.id.Sales_Proceed);
        setListeners();
        }

    private void setListeners() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        insert_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = sale_location.getText().toString();
                price =  Double.valueOf(sale_price.getText().toString());
                POS pos = new POS(model, location,price);
                InsertPOS p = new InsertPOS(pos);
                try {
                    p.insert();
                    Toast.makeText(Sale_Entry_activity.this, "Sale Recorded", Toast.LENGTH_SHORT ).show();
                    Log.i(TAG, "The sale was recorded successfully");
                }catch (Exception e){
                    Toast.makeText(Sale_Entry_activity.this, "Sale was not recorded!", Toast.LENGTH_SHORT ).show();
                    Log.e(TAG, "Something Went wrong uploading to the database");
                }
                finish();
            }
    });
    }



}
