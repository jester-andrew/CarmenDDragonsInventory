package com.dragons.carmenddragonsinventory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class CreatureHolder extends RecyclerView.ViewHolder {
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
        costToProduce = itemView.findViewById(R.id.costProduce);
        inStock = itemView.findViewById(R.id.stock);
        listPrice = itemView.findViewById(R.id.listPrice);
        photo = itemView.findViewById(R.id.itemimage);
    }

}
