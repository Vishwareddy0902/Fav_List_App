package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCategoryNumber;
    private TextView txtCategoryName;

    public CategoryViewHolder(@NonNull View view) {
        super(view);

        txtCategoryNumber = view.findViewById(R.id.category_number_txt);
        txtCategoryName = view.findViewById(R.id.category_name_txt);

    }

    public TextView getTxtCategoryNumber() {
        return txtCategoryNumber;
    }

    public TextView getTxtCategoryName() {
        return txtCategoryName;
    }

}
