package com.dragons.carmenddragonsinventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class InventoryDisplayActivity extends AppCompatActivity {


    private RecyclerView.LayoutManager layoutManager;
    protected static final Query inventoryQuery = FirebaseDatabase.getInstance().getReference().child("inventory");
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);
        recyclerView = (RecyclerView) findViewById(R.id.inventoryList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(newAdapter());
    }
    // probably needed for when Auth is implemented
    private void attachRecyclerViewAdapter(){
        final RecyclerView.Adapter adapter = newAdapter();
        recyclerView.setAdapter(adapter);
    }
    protected FirebaseRecyclerAdapter<InventoryCreature, CreatureHolder> newAdapter(){
       FirebaseRecyclerOptions<InventoryCreature> options =
               new FirebaseRecyclerOptions.Builder<InventoryCreature>()
                  .setQuery(inventoryQuery,InventoryCreature.class)
                  .setLifecycleOwner(this)
                  .build();
       return new FirebaseRecyclerAdapter<InventoryCreature,CreatureHolder>(options) {




           @NonNull
           @Override
           public CreatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               return new CreatureHolder(LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.inventorycreaturelayout,parent,false));
           }

           @Override
           protected void onBindViewHolder(@NonNull CreatureHolder holder, int position, @NonNull InventoryCreature model) {

           }
       };


    }


}
