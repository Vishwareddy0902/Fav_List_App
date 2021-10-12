package com.example.favlistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerViewHolder> {

    private Category category;

    public ItemsRecyclerAdapter(Category category) {
        this.category = category;
    }

    @NonNull
    @Override
    public ItemsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_viewholder,parent,false);
        return new ItemsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsRecyclerViewHolder holder, int position) {

        holder.getTxtItems().setText(String.valueOf(category.getLists().get(position)));

    }

    @Override
    public int getItemCount() {
        return category.getLists().size();
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
