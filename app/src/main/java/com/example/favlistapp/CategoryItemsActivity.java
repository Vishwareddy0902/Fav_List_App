package com.example.favlistapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FloatingActionButton mFloatingActionButton;

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_ITEM_KEY);
        setTitle(category.getName());

        itemsRecyclerView = findViewById(R.id.items_recyclerview);
        itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFloatingActionButton = findViewById(R.id.items_floatingActionButton);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayItemCreateDialog();

            }
        });



    }

    public void displayItemCreateDialog(){

        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Enter the name of item...")
                .setView(editText)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itemName = editText.getText().toString();
                        category.getLists().add(itemName);
                        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
                        itemsRecyclerAdapter.notifyItemInserted(category.getLists().size()-1);
                        dialog.dismiss();
                    }
                })
                .create()
                .show();

    }

    @Override
    public void onBackPressed() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.CATEGORY_ITEM_KEY,category);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);

        super.onBackPressed();
    }
}