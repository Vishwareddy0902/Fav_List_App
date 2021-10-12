package com.example.favlistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryListsFragment extends Fragment {

    private RecyclerView itemsRecyclerView;
    private static final String CATEGORY_ARGS ="category_args";
    Category category;


    public CategoryListsFragment() {
        // Required empty public constructor
    }


    public static CategoryListsFragment newInstance(Category category) {

        CategoryListsFragment categoryListsFragment = new CategoryListsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_ARGS,category);
        categoryListsFragment.setArguments(bundle);
        return categoryListsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        category = (Category) getArguments().getSerializable(CATEGORY_ARGS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_lists, container, false);
        if(view!=null) {
            itemsRecyclerView = view.findViewById(R.id.items_recyclerview);
            itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
            itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }
        return  view;
    }

    public void addItemCategory(String itemName) {

        category.getLists().add(itemName);
        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
        itemsRecyclerAdapter.setCategory(category);
        itemsRecyclerAdapter.notifyDataSetChanged();

    }


}