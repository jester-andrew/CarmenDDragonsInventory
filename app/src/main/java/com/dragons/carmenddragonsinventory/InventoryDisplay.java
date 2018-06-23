package com.dragons.carmenddragonsinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class InventoryDisplay extends AppCompatActivity {
    private RecyclerView recyclerView;
    private inventoryCreatureList creatureList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);
        recyclerView = (RecyclerView) findViewById(R.id.inventoryList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        creatureList = new inventoryCreatureList();
        recyclerView.setAdapter(creatureList);

    }
}
