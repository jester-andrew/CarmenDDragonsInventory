package com.dragons.carmenddragonsinventory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * This class creates a holder for the information that will be displayed in the sales history view
 */
public class POS_Holder extends RecyclerView.ViewHolder {
    /**
     * non-default constructor takes a view as a parameter and sets the variables to the iuems in the view
     * @param itemView
     */
    public POS_Holder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.Pos_Name);
        location = itemView.findViewById(R.id.POS_Location);
        price = itemView.findViewById(R.id.POS_Profit);
        profit = itemView.findViewById((R.id.POS_Sold));

    }
    private TextView name;
    private TextView location;
    private TextView price;
    private TextView profit;

    //getters and setters
    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getProfit() {
        return profit;
    }

    public void setProfit(TextView profit) {
        this.profit = profit;
    }
}
