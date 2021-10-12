package com.example.favlistapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryFragment.OnCategoryInteractionListener {


    public final static String CATEGORY_ITEM_KEY = "CATEGORY_KEY";
    public final static int MAIN_ACTIVITY_REQUEST_CODE = 1000;

    private CategoryFragment mCategoryFragment;
    private CategoryListsFragment mCategoryListsFragment;
    private FloatingActionButton fab;
    private FrameLayout categoryItemsFrameLayout;
    private boolean isTablet=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        mCategoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentById(R.id.category_fragment);
        categoryItemsFrameLayout = findViewById(R.id.category_items_fragment_container);

        isTablet = (categoryItemsFrameLayout!=null);




        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                displayCreateCategoryDialog();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private  void displayCreateCategoryDialog(){

        String alertTitle = getString(R.string.category_alert_title);
        String positiveButtonTitle =getString(R.string.category_positive_button);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        EditText categoryEditText = new EditText(this);
        categoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        alertDialog.setTitle(alertTitle);
        alertDialog.setView(categoryEditText);
        alertDialog.setPositiveButton(positiveButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Category category = new Category(categoryEditText.getText().toString(),new ArrayList<String>());
                mCategoryFragment.giveCategoryToManager(category);


                dialog.dismiss();
                displayCategoryLists(category);
            }
        });

        alertDialog.create().show();

    }

    private  void displayCategoryLists(Category category){

        if(!isTablet){

            Intent categoryItemsIntent = new Intent(MainActivity.this, CategoryItemsActivity.class);
            categoryItemsIntent.putExtra(CATEGORY_ITEM_KEY, category);
            startActivityForResult(categoryItemsIntent, MAIN_ACTIVITY_REQUEST_CODE);

        }

        else{

            if(mCategoryListsFragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(mCategoryListsFragment)
                        .commit();

                mCategoryListsFragment = null;
            }

            setTitle(category.getName());
            mCategoryListsFragment = CategoryListsFragment.newInstance(category);

            if(mCategoryListsFragment !=null){

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.category_items_fragment_container,mCategoryListsFragment)
                        .addToBackStack(null)
                        .commit();

            }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayItemCreateDialog();
                }
            });

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MAIN_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data!=null){

                mCategoryFragment.saveCategory((Category) data.getSerializableExtra(CATEGORY_ITEM_KEY));

            }
        }

    }


    @Override
    public void categoryIsTapped(Category category) {
        displayCategoryLists(category);
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
                        mCategoryListsFragment.addItemCategory(itemName);
                        dialog.dismiss();
                    }
                })
                .create()
                .show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setTitle(R.string.app_name);

        if(mCategoryListsFragment != null){

            mCategoryFragment.getCategoryManager().saveCategory(mCategoryListsFragment.category);

        }

        if(mCategoryListsFragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mCategoryListsFragment)
                    .commit();

            mCategoryListsFragment = null;

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCreateCategoryDialog();
            }
        });

    }
}



