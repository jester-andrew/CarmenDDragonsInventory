package com.dragons.carmenddragonsinventory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

class CreatureHolder extends RecyclerView.ViewHolder {
    public TextView getNameField() {
        return nameField;
    }

    public TextView getItemHeld() {
        return itemHeld;
    }

    public TextView getColor() {
        return color;
    }

    public TextView getCostToProduce() {
        return costToProduce;
    }

    public TextView getInStock() {
        return inStock;
    }

    public TextView getListPrice() {
        return listPrice;
    }

    public ImageView getPhoto() {
        return photo;
    }

    private final TextView nameField;
    private final TextView itemHeld;
    private final TextView color;
    private final TextView costToProduce;
    private final TextView inStock;
    private final TextView listPrice;
    private final ImageView photo;
    public CreatureHolder(View itemView) {
        super(itemView);
        nameField = itemView.findViewById(R.id.nametag);
        itemHeld = itemView.findViewById(R.id.itemtag);
        color = itemView.findViewById(R.id.Color);
        costToProduce = itemView.findViewById(R.id.CostToProduce);
        inStock = itemView.findViewById(R.id.inStock);
        listPrice = itemView.findViewById(R.id.listPrice);
        photo = itemView.findViewById(R.id.itemimage);
    }

}
