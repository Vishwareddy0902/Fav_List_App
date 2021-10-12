package com.example.favlistapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<Category> categories;

    interface CategoryIsClickedInterface{
        void categoryIsClicked(Category category);
    }

    private CategoryIsClickedInterface mCategoryIsClickedInterface;

    public CategoryRecyclerAdapter(ArrayList<Category> categories, CategoryIsClickedInterface categoryIsClickedInterface) {
        this.categories = categories;
        mCategoryIsClickedInterface = categoryIsClickedInterface;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_viewholder,parent,false);

        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.getTxtCategoryNumber().setText(Integer.toString(position+1)+".");
        holder.getTxtCategoryName().setText(categories.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryIsClickedInterface.categoryIsClicked(categories.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addCategory(Category category) {
        categories.add(category);
        notifyItemInserted(categories.size()-1);
    }
}
