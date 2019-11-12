package com.example.moneymanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.adapters.ShowItemsAdapter;
import com.example.moneymanager.models.Item;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShowItemActivity extends AppCompatActivity {
    private RecyclerView recyclerItems;
    private ShowItemsAdapter mAdapter;
    private ArrayList<Item>mListExpenseItem;
    private ArrayList<Item>mListIncomeItem;

    private ImageView btnBack;
    private NiceSpinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btnBack);
        recyclerItems = findViewById(R.id.recycleItems);
        spinner = findViewById(R.id.spinner);

        showItems(recyclerItems, mAdapter, getListExpense());
        List<String> dataset = new LinkedList<>(Arrays.asList("Expense", "Income"));
        spinner.attachDataSource(dataset);
        spinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                if(position == 0){
                    mListExpenseItem = getListExpense();
                    showItems(recyclerItems, mAdapter, mListExpenseItem);
                }else{
                    mListIncomeItem = getListIncome();
                    showItems(recyclerItems, mAdapter, mListIncomeItem);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    
    private ArrayList<Item> getListExpense(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("Home"));
        mListItem.add(new Item("Bills"));
        mListItem.add(new Item("Transportation"));
        mListItem.add(new Item("Home"));
        mListItem.add(new Item("Car"));
        mListItem.add(new Item("Entertainment"));
        mListItem.add(new Item("Shopping"));
        mListItem.add(new Item("Clothing"));
        mListItem.add(new Item("Insurance"));
        mListItem.add(new Item("Tax"));
        mListItem.add(new Item("Telephone"));
        mListItem.add(new Item("Cigarette"));
        mListItem.add(new Item("Health"));
        mListItem.add(new Item("Sport"));
        mListItem.add(new Item("Baby"));
        mListItem.add(new Item("Pet"));
        mListItem.add(new Item("Add", true));
        return mListItem;
    }
    private ArrayList<Item> getListIncome(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("Food"));
        mListItem.add(new Item("Car"));
        mListItem.add(new Item("Sport"));
        mListItem.add(new Item("Health"));
        mListItem.add(new Item("Add", false));
        return mListItem;
    }
    private void showItems(RecyclerView recyclerItems, ShowItemsAdapter adapter, ArrayList<Item>mListItem){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setItemAnimator(new DefaultItemAnimator());
        adapter = new ShowItemsAdapter(ShowItemActivity.this, mListItem);
        recyclerItems.setAdapter(adapter);
    }
}
