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
    protected FirebaseRecyclerAdapter<POS, POS_Holder> newAdapter(){
        FirebaseRecyclerOptions<POS> options =
                new FirebaseRecyclerOptions.Builder<POS>()
                        .setQuery(inventoryQuery, new SnapshotParser<POS>() {
                            @NonNull
                            @Override
                            public POS parseSnapshot(@NonNull DataSnapshot snapshot) {

                                POS pos = new POS();
                                pos.setLocation(snapshot.child("location").getValue().toString());
                                pos.setPrice(snapshot.child("Price").getValue(Double.class));
                                pos.setProfit(snapshot.child("Profit").getValue(Double.class));
                                pos.setDb_loc(snapshot.getRef().toString());

                                return pos;
                            }
                        })
                        .setLifecycleOwner(this)
                        .build();
        return new FirebaseRecyclerAdapter<POS,POS_Holder>(options) {


            @NonNull
            @Override
            public POS_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new POS_Holder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.pos_holder,parent,false));
            }

            @Override
            protected void onBindViewHolder(@NonNull final POS_Holder holder, int position, @NonNull final POS model) {

                holder.getLocation().setText(String.valueOf(model.getLocation()));
                holder.getPrice().setText(String.valueOf(model.getPrice()));
                holder.getProfit().setText(String.valueOf(model.getProfit()));
            }
        };
    }

    public void setQuery() {
            this.setTitle("Sales_History"); // changes header title
            inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("sea-creature").orderByValue();
    }

    }

