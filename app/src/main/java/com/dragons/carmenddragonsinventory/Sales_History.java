package com.dragons.carmenddragonsinventory;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Sales_History extends AppCompatActivity {
    /**
     * This class provides the interface to see the sales history
     */
    private RecyclerView.LayoutManager layoutManager;
    protected Query inventoryQuery;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales__history);
        setQuery();

        recyclerView = findViewById(R.id.sales_list);

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
                                ic.setName(snapshot.child("name").getValue().toString());
                                ic.setListPrice(snapshot.child("listPrice").getValue(Double.class));
                                ic.setCostToProduce(snapshot.child("costToProduce").getValue(Double.class));
                                ic.setDb_loc(snapshot.getRef().toString());
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
            protected void onBindViewHolder(@NonNull final CreatureHolder holder, int position, @NonNull final InventoryCreature model) {
                holder.getCostToProduce().setText(String.valueOf(model.getCostToProduce()));
                holder.getListPrice().setText(String.valueOf(model.getListPrice()));
                holder.getNameField().setText(model.getName());
            }
        };
    }

    public void setQuery() {
            this.setTitle("Sales_History"); // changes header title
            inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("sea-creature").orderByValue();
    }

    }

