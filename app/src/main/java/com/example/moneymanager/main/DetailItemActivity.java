package com.example.moneymanager.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymanager.R;
import com.example.moneymanager.models.App;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailItemActivity extends AppCompatActivity {

    private ImageView btnBack, btnDel;
    private FloatingActionButton btnEdit;

    private LinearLayout bgIcon;
    private ImageView icon;
    private TextView nameDetail, tvCategory, tvTimes, tvMoney, tvMemo;

    private App app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        app = new App();

        btnBack = findViewById(R.id.btnBack);
        btnDel = findViewById(R.id.btnDel);
        btnEdit = findViewById(R.id.btnEdit);

        bgIcon = findViewById(R.id.bgIcon);
        icon = findViewById(R.id.icon);
        nameDetail = findViewById(R.id.nameDetail);
        tvCategory = findViewById(R.id.category);
        tvMoney = findViewById(R.id.money);
        tvTimes = findViewById(R.id.times);
        tvMemo = findViewById(R.id.memo);


        long timestamp = getIntent().getExtras().getLong("timestamp");
        String category = getIntent().getExtras().getString("category");
        String type = getIntent().getExtras().getString("type");
        String name = getIntent().getExtras().getString("name");
        long amount = getIntent().getExtras().getLong("amount");

        icon.setColorFilter(Color.parseColor("#ffffff"));
        bgIcon.setBackgroundResource(app.getICons(type).second);
        nameDetail.setText(name);
        tvCategory.setText(category);
        tvMoney.setText(String.valueOf(amount));
        tvTimes.setText(convertTimes(timestamp));
        tvMemo.setText(type);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private String convertTimes (long timestamp){
        Date date = new java.util.Date(timestamp);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy EEE");
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);

        return formattedDate;
    }
}
