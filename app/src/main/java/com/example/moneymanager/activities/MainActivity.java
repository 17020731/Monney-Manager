package com.example.moneymanager.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avast.android.dialogs.fragment.DatePickerDialogFragment;
import com.example.moneymanager.R;
import com.example.moneymanager.adapters.HistoryAdapter;
import com.example.moneymanager.adapters.SpendAdapter;
import com.example.moneymanager.models.History;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnIncome, btnExpense;
    private FloatingActionButton btnAdd;
    private ImageView btnMenu;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private RecyclerView recyclerView;
    private ArrayList<History>mListHistory;
    private HistoryAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        recyclerView = findViewById(R.id.recycleView);
        mListHistory = new ArrayList<>();
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));

        mListHistory.add(new History("Car", "Oto", "2000"));

        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));
        mListHistory.add(new History("Car", "Oto", "2000"));



        recyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new HistoryAdapter(this, mListHistory);
        recyclerView.setAdapter(mAdapter);

        btnAdd= findViewById(R.id.btnAdd);
        btnExpense = findViewById(R.id.btnExpense);
        btnIncome = findViewById(R.id.btnIncome);
        btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowItemActivity.class);
                startActivity(intent);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Intent intent;
                switch (id){
                    case R.id.chart:
                        intent = new Intent(MainActivity.this, ChartActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.categories:
                        intent = new Intent(MainActivity.this, CategorySettingActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }
}
