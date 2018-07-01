package com.dragons.carmenddragonsinventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;



public class InventoryDisplayActivity extends AppCompatActivity {


    private RecyclerView.LayoutManager layoutManager;
    protected static final Query inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("fantasy-creature").orderByValue();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);
        recyclerView = (RecyclerView) findViewById(R.id.inventoryList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        attachRecyclerViewAdapter();
    }
    // probably needed for when Auth is implemented
    private void attachRecyclerViewAdapter(){
        final RecyclerView.Adapter adapter = newAdapter();
        recyclerView.setAdapter(adapter);
    }
    protected FirebaseRecyclerAdapter<InventoryCreature, CreatureHolder> newAdapter(){
       FirebaseRecyclerOptions<InventoryCreature> options =
               new FirebaseRecyclerOptions.Builder<InventoryCreature>()
                  .setQuery(inventoryQuery, new SnapshotParser<InventoryCreature>() {
                      @NonNull
                      @Override
                      public InventoryCreature parseSnapshot(@NonNull DataSnapshot snapshot) {


                              InventoryCreature ic = new InventoryCreature();
                              ic.setColor(snapshot.child("color").getValue().toString());
                              ic.setName(snapshot.child("name").getValue().toString());
                              ic.setItem(snapshot.child("item").getValue().toString());
                              ic.setListPrice(snapshot.child("listPrice").getValue(Double.class));
                              ic.setCostToProduce(snapshot.child("costToProduce").getValue(Double.class));
                              ic.setStock(snapshot.child("stock").getValue(Integer.class));
                              return ic;
                          }
                  })
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
                holder.getItemHeld().setText(model.getItem());
                holder.getInStock().setText(String.valueOf(model.getStock()));
                holder.getCostToProduce().setText(String.valueOf(model.getCostToProduce()));
                holder.getListPrice().setText(String.valueOf(model.getListPrice()));
                holder.getColor().setText(model.getColor());
                holder.getNameField().setText(model.getName());

           }
       };


    }


}
