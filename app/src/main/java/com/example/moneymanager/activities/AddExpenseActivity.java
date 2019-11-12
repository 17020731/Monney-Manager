package com.example.moneymanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.adapters.ShowItemsAdapter;
import com.example.moneymanager.models.Item;

import java.util.ArrayList;

public class AddExpenseActivity extends AppCompatActivity {
    private ImageView btnBack;
    private TextView title1, title2, title3;
    private RecyclerView mRecyclerView1, mRecyclerView2, mRecyclerView3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        title1 =findViewById(R.id.title1);
        title2 =findViewById(R.id.title2);
        title3 =findViewById(R.id.title3);

        mRecyclerView1 = findViewById(R.id.mRecycleView1);
        mRecyclerView2 = findViewById(R.id.mRecycleView2);
        mRecyclerView3 = findViewById(R.id.mRecycleView3);

        showItemsByTopic("Food", title1, mRecyclerView1, getListExpense());
        showItemsByTopic("Health", title2, mRecyclerView2, getListExpense());
        showItemsByTopic("Shopping", title3, mRecyclerView3, getListExpense());

    }

    private void showItemsByTopic(String topic, TextView title, RecyclerView recyclerView, ArrayList<Item> mListItem){
        title.setText(topic);
        showItems(recyclerView, getListExpense());
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
        return mListItem;
    }
    private void showItems(RecyclerView recyclerItems, ArrayList<Item> mListItem){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setNestedScrollingEnabled(false);
        recyclerItems.setItemAnimator(new DefaultItemAnimator());
        ShowItemsAdapter adapter = new ShowItemsAdapter(AddExpenseActivity.this, mListItem);
        recyclerItems.setAdapter(adapter);
    }

}
