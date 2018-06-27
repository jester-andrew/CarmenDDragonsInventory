package com.dragons.carmenddragonsinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Add_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);
    }

    public void addItem(View view){
        EditText img = (EditText) findViewById(R.id.imageName);
        EditText itemName = (EditText) findViewById(R.id.item_Name);
        EditText itemColor = (EditText) findViewById(R.id.item_color);
        EditText holdingItem = (EditText) findViewById(R.id.Item_Item);
        EditText ctp = (EditText) findViewById(R.id.cost_to_Produce);
        String ctp2 = ctp.getText().toString();
        EditText listPrice = (EditText) findViewById(R.id.list_Price);
        String listPrice2 = listPrice.getText().toString();
        EditText stock = (EditText) findViewById(R.id.Stock);
        String stock2 = stock.getText().toString();


        InventoryCreature ic = new InventoryCreature(itemName.getText().toString(), itemColor.getText().toString(),
                holdingItem.getText().toString(), Double.parseDouble(ctp2), Double.parseDouble(listPrice2),
                Integer.parseInt(stock2), img.getText().toString());

        insertInventoryCreature iic = new insertInventoryCreature();






    }


}
