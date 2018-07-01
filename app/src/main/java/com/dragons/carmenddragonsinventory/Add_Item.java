package com.dragons.carmenddragonsinventory;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private String location;
    private static String TAG = "Add_Item Activity";

    TextView _image;
    TextView itemname;
    TextView item_Color;
    TextView holding_Item;
    TextView _ctp;
    TextView listingprice;
    TextView _stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Fantasy-Creature", "Sea-Creature", "Woodland-Creature"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(this);

        _image = findViewById(R.id.imageName);
        itemname = findViewById(R.id.item_Name);
        item_Color = findViewById(R.id.item_color);
        holding_Item =findViewById(R.id.Item_Item);
        _ctp = findViewById(R.id.cost_to_Produce);
        listingprice = findViewById(R.id.list_Price);
        _stock = findViewById(R.id.inStock);


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


        try {

            InventoryCreature ic = new InventoryCreature(itemName.getText().toString(), itemColor.getText().toString(),
                    holdingItem.getText().toString(), Double.parseDouble(ctp2), Double.parseDouble(listPrice2),
                    Integer.parseInt(stock2), img.getText().toString());

            insertInventoryCreature iic = new insertInventoryCreature();
            iic.insertInventoryCreature(ic, location);

            Context context = getApplicationContext();
            CharSequence text = "Item Added Successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }catch (Exception e){
            Log.e(TAG, "Failed to insert new item in: Add_Item");

            Context context = getApplicationContext();
            CharSequence text = "Item NOT Added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        try{
            _image.setText("");
            itemname.setText("");
            item_Color.setText("");
            holding_Item.setText("");
            _ctp.setText("");
            listingprice.setText("");
            _stock.setText("");
        } catch(Exception e){
            Log.e(TAG, "failed to delete all fields");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                location = "fantasy-creature";
                break;
            case 1:
                location = "sea-creature";
                break;
            case 2:
                location = "woodland-creature";
                break;
        }

        Context context = getApplicationContext();
        CharSequence text = "Location: " + location;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Context context = getApplicationContext();
        CharSequence text = "Nothing Selected";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }


}
