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

/**
 * This activity is used to display the full category chosen from the
 * main menu.
 * @author Justen
 */
public class InventoryDisplayActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    protected Query inventoryQuery;
    private RecyclerView recyclerView;
    private String category;
    private static String TAG =  " Inventory Display Activity";
    private Context context;
    private Integer image_width;


    /**
     * Overrides the Activity on create and initializes variables
     * @param savedInstanceState contains all the information from a closed instance in order to re-initialize it
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_display);
        category = (String) getIntent().getExtras().get("CATEGORY");
        image_width = (Resources.getSystem().getDisplayMetrics().widthPixels) / 3 ;
        setQuery(category);
        context = this;

        recyclerView = findViewById(R.id.inventoryList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        attachRecyclerViewAdapter();
    }

    /**
     * this method creates a new adapter to allow connection to the RecyclerView and
     * then attaches it.
     */
    // probably needed for when Auth is implemented
    private void attachRecyclerViewAdapter(){
        final RecyclerView.Adapter adapter = newAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * this class is where the major labor of attaching this app to the database happens
     * @return an instance of the class
     *
     * In order to make this more readable, individual function calls are included here.
     *  the options class is a template that the adapter uses to choose where to place data
     *  the .setQuery call uses the variable returned by setQuery to find the reference of
     *  the Firebase Database section we wish to display, and the embedded class SnapshotParser
     *  transfers the data from the reference into each instance of the InventoryCreature model class.
     */
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
                                ic.setListPrice( snapshot.child("listPrice").getValue(Double.class));
                                ic.setCostToProduce( snapshot.child("costToProduce").getValue(Double.class));
                                ic.setStock(snapshot.child("stock").getValue(Integer.class));
                                ic.setImgFilePath(String.valueOf(snapshot.child("imgFilePath").getValue()));
                                Log.i(TAG, "parseSnapshot: getRef().toString Value:" + snapshot.getRef().toString());
                                ic.setDb_loc(snapshot.getRef().toString());
                                Log.i(TAG, "parseSnapshot: " + snapshot.toString());
                                return ic;

                          }
                  })
                  .setLifecycleOwner(this)
                  .build();
       return new FirebaseRecyclerAdapter<InventoryCreature,CreatureHolder>(options) {


           /**
            * this method allows us to insert ViewHolders into the associated RecyclerView
            * @param parent the recyclerview to populate
            * @param viewType the view id of the layout to use with the CreatureHolder
            * @return a new instance of the view container CreatureHolder
            */
           @NonNull
           @Override
           public CreatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               return new CreatureHolder(LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.inventory_creature_layout,parent,false));
           }

           /**
            * called by BindViewHolder to make the adapter contents show on screen.
             * @param holder individual part of the recycler view to bind to.
            * @param position the place the inventory creature holds in the list
            * @param model the specific creature to put into the holder at the position
            */
           @Override
           protected void onBindViewHolder(@NonNull final CreatureHolder holder, int position, @NonNull final InventoryCreature model) {
                holder.getItemHeld().setText(model.getItem());
                holder.getInStock().setText(String.valueOf(model.getStock()));
                holder.getCostToProduce().setText(String.valueOf(model.getCostToProduce()));
                holder.getListPrice().setText(String.valueOf(model.getListPrice()));
                holder.getColor().setText(model.getColor());
                holder.getNameField().setText(model.getName());
                Log.i(TAG, "onBindViewHolder: "+ model.getImgFilePath());
                Picasso.get()
                        .load(model.getImgFilePath())
                        .resize(image_width,300)
                        .centerCrop()
                        .into(holder.getPhoto());
                holder.getSale().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "onClick: onClick called");
                        Intent sale = new Intent(InventoryDisplayActivity.this, Sale_Entry_activity.class);
                        Log.i(TAG, "onClick: intent created");
                        sale.putExtra("model", model);
                        startActivity(sale);

                    }
                });
               holder.getDecStock().setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       DatabaseReference current_ref = FirebaseDatabase.getInstance().getReferenceFromUrl(model.getDb_loc());
                       Integer temp = model.getStock();
                       temp--;
                       current_ref.child("stock").setValue(temp);
                   }
               });
               holder.getIncStock().setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       DatabaseReference current_ref = FirebaseDatabase.getInstance().getReferenceFromUrl(model.getDb_loc());
                       Integer temp = model.getStock();
                       temp++;
                       current_ref.child("stock").setValue(temp);
                   }
               });

           }
       };


    }


    /**
     * this function takes in the string passed via the intent's extras parameters and uses it to create the necessary query for the database
     * @param category a string of one of three values. it is compared with a hard coded value to create the correct Query
     */
    public void setQuery(String category) {
        Log.i(TAG, "setQuery: "+ category + " is the value of the extras");
        if (category.equals("fantasy")){
            this.setTitle("Fantasy Creatures"); // changes header title
            inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("fantasy-creature").orderByValue();
        }
        else if (category.equals("woodland")){
            this.setTitle("Woodland Creatures"); // changes header title
            inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("woodland-creature").orderByValue();
        }
        else if ( category.equals("underwater")){
            this.setTitle("Underwater Creatures"); // changes header title
            inventoryQuery = FirebaseDatabase.getInstance().getReference("inventory").child("sea-creature").orderByValue();
        }


    }
}
