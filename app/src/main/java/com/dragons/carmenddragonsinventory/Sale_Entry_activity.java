package com.dragons.carmenddragonsinventory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sale_Entry_activity extends Activity {
    private InventoryCreature model;
    private String location;
    private Double price;
    private EditText sale_location;
    private EditText sale_price;
    private Button cancel;
    private Button insert_sale;

    public Sale_Entry_activity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sales_dialog_layout);
        model = (InventoryCreature) getIntent().getSerializableExtra("model");
        sale_location = findViewById(R.id.sales_location_edit);
        sale_price = findViewById(R.id.sales_price_edit);
        location = sale_location.getText().toString();
        price =  Double.valueOf(sale_price.getText().toString());
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


                finish();
            }
    });
    }


}
