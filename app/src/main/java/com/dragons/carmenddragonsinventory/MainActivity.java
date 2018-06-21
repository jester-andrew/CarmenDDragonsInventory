package com.dragons.carmenddragonsinventory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // THIS IS MY COMMENT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // write an item to the database option 1 using an initialized object
        InventoryCreature a = new InventoryCreature("shark", "blue", "walrus", 2.00, 2.00, 10, "test");
        insertInventoryCreature insert = new insertInventoryCreature(a, "fantasy-creature");
        insert.insertInventoryCreature();

        // write an item to the database option 2 using an unitialized object
        InventoryCreature b = new InventoryCreature("cow", "chocolate", "bow-staff", 3.12, 6.12, 12, "test");
        insertInventoryCreature insertTwo = new insertInventoryCreature();
        insertTwo.insertInventoryCreature(b, "woodland-creature");

        // write an item to the database option 2 using an unitialized object
        InventoryCreature c = new InventoryCreature("cat", "purple", "bow-staff", 3.12, 6.12, 12, "test");
        insertInventoryCreature insertThree = new insertInventoryCreature();
        insertThree.insertInventoryCreature(c, "sea-creature");





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
