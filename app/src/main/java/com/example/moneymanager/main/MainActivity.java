package com.example.moneymanager.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.chart.ChartActivity;
import com.example.moneymanager.profile.ProfileActivity;
import com.example.moneymanager.additem.ShowItemActivity;
import com.example.moneymanager.models.History;
import com.example.moneymanager.models.HistoryChild;
import com.example.moneymanager.setting.CategorySettingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView name;
    private LinearLayout btnIncome, btnExpense;
    private FloatingActionButton btnAdd;
    private ImageView btnMenu, btnReload;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageView avatar;

    private RecyclerView recyclerView;
    private ArrayList<History>mListHistory;

    private HistoryAdapter mAdapter;
    private HistoryChildAdapter mAdapterChild;

    private ScrollView scrollView;


    private LinearLayout monthPicker;
    private LinearLayout emptyLinear;
    private DatabaseReference mDatabase;
    private static String uID = "0945455387test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        monthPicker = findViewById(R.id.monthPicker);

        emptyLinear = findViewById(R.id.emptyLinear);
        emptyLinear.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        drawer = findViewById(R.id.drawer);

        navigationView = findViewById(R.id.navigationView);
        name = navigationView.getHeaderView(0).findViewById(R.id.name);
        avatar = navigationView.getHeaderView(0).findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        scrollView = findViewById(R.id.scrollView);


        recyclerView = findViewById(R.id.recycleView);
        mListHistory = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mDatabase.child("histories").child(uID).child("11-2019").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String>times = new ArrayList<>();
                ArrayList<HistoryChild>mListHistoryChild = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    times.add(snapshot.getKey());

                    HistoryChild child = new HistoryChild();
                    child = snapshot.getValue(HistoryChild.class);
                    child.setTimestamp(snapshot.getKey());

                    mListHistoryChild.add(child);
                }

                for (int i = 0; i < times.size(); i++){
                    String date = convertDayMonth(Long.parseLong(times.get(i)));
                    ArrayList<HistoryChild>list = new ArrayList<>();
                    list.add(mListHistoryChild.get(i));
                    for(int j = i+1; j < times.size(); j++){
                        if(convertDayMonth(Long.parseLong(times.get(j))).equals(date)){
                            list.add(mListHistoryChild.get(j));
                            if(j == times.size()-1){
                                i = j;
                                break;
                            }
                            continue;
                        }else if(!convertDayMonth(Long.parseLong(times.get(j))).equals(date)){
                            i = j-1;
                            break;
                        }
                    }
                    mListHistory.add(new History(date, list));
                }

                if(mListHistory.size() == 0){
                    emptyLinear.setVisibility(View.VISIBLE);
                }else {
                    emptyLinear.setVisibility(View.INVISIBLE);
                }
                mAdapter = new HistoryAdapter(MainActivity.this, mListHistory);
                recyclerView = findViewById(R.id.recycleView);

                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        monthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthPicker.setVisibility(View.INVISIBLE);
            }
        });

        btnAdd= findViewById(R.id.btnAdd);
        btnExpense = findViewById(R.id.btnExpense);
        btnIncome = findViewById(R.id.btnIncome);
        btnMenu = findViewById(R.id.btnMenu);
        btnReload = findViewById(R.id.btnReload);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra("category", "expense");
                startActivity(intent);
            }
        });
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra("category", "income");
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
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
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
                        intent.putExtra("category", "expense");
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

    private String convertMonthYear (long timestamp){
        Date date = new java.util.Date(timestamp);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-yyyy");
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);

        return formattedDate;
    }

    private String convertDayMonth (long timestamp){
        Date date = new java.util.Date(timestamp);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM");
        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}
