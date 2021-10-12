package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView txtItems;

    public ItemsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        txtItems = itemView.findViewById(R.id.txtItems);

    }

    public TextView getTxtItems() {
        return txtItems;
    }
}
