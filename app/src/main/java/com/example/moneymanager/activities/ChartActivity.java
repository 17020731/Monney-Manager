package com.example.moneymanager.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.models.Data;
import com.example.moneymanager.R;
import com.example.moneymanager.adapters.SpendAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private PieChart pieChart;
    private RecyclerView recyclerView;
    private SpendAdapter mSendAdapter;
    private ArrayList<Data>mListData;

    private ImageView btnBack;

    private String CATEGORY = "expense";

    private DatabaseReference mDatabase;
    private static String uID = "0945455387test";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btnBack);

        pieChart = findViewById(R.id.pieChart);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        setUpPieChart(pieChart, "expense", "11-2019");

        mListData = new ArrayList<>();
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));
        mListData.add(new Data("Car", 100, 60000));

        recyclerView = findViewById(R.id.recycleView);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mSendAdapter = new SpendAdapter(this, mListData);
        recyclerView.setAdapter(mSendAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setUpPieChart(final PieChart pieChart, final String category, String month){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);

        pieChart.setCenterText("Expenses\n62000");

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleRadius(85f);//Set độ dày của đường tròn bên trong
        final ArrayList<PieEntry> yValues = new ArrayList<>();

        mDatabase.child("histories").child(uID).child(month).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(count == 3){
                        break;
                    }
                    if(snapshot.child("category").getValue().equals(category)){
                        yValues.add(new PieEntry(1f, snapshot.child("name").getValue()));
                        count++;
                    }
                }
                //Todo: Chú thích
                PieDataSet dataSet = new PieDataSet(yValues, "");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData data = new PieData(dataSet);
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.RED);
                data.setDrawValues(false);

                pieChart.setData(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        yValues.add(new PieEntry(10f, "party1"));  //20f là phần được chia
//        yValues.add(new PieEntry(40f, "party2"));
//        yValues.add(new PieEntry(60f, "party3"));
//        yValues.add(new PieEntry(20f, "party4"));
//        yValues.add(new PieEntry(20f, "party5"));



    }
}
