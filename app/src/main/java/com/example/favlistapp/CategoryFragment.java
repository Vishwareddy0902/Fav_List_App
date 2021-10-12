package com.example.favlistapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CategoryFragment extends Fragment implements CategoryRecyclerAdapter.CategoryIsClickedInterface {

    private RecyclerView categoryRecyclerView;
    private CategoryManager mCategoryManager;

    public CategoryManager getCategoryManager() {
        return mCategoryManager;
    }

    interface OnCategoryInteractionListener{
        void categoryIsTapped(Category category);
    }

    private OnCategoryInteractionListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context  instanceof OnCategoryInteractionListener) {
            mListener = (OnCategoryInteractionListener) context;
        }
        else{
            throw new RuntimeException("activity must implement the interface");
        }
        mCategoryManager = new CategoryManager(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView() != null) {
            categoryRecyclerView = getView().findViewById(R.id.category_recyclerview);
            ArrayList<Category> categories = mCategoryManager.retrieveCategory();
            categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));
            categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    public CategoryFragment() {
        // Required empty public constructor
    }


    public static CategoryFragment newInstance() {
       return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void categoryIsClicked(Category category){
        mListener.categoryIsTapped(category);
    }

    public void giveCategoryToManager(Category category) {
        mCategoryManager.saveCategory(category);
        CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) categoryRecyclerView.getAdapter();
        categoryRecyclerAdapter.addCategory(category);
    }

   public void saveCategory(Category category){
        mCategoryManager.saveCategory(category);
       updateCategories();
   }

    private void updateCategories() {

        ArrayList<Category> categories = mCategoryManager.retrieveCategory();
        categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories,this));

    }


}