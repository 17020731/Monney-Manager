package com.example.moneymanager.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymanager.R;

public class SettingActivity extends AppCompatActivity {
    private ImageView btnBack;
    private LinearLayout btnLang;
    private TextView tvLang;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_setting);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(e -> onBackPressed());

        btnLang = findViewById(R.id.btnLang);
        btnLang.setOnClickListener(e -> {
            Intent intent = new Intent(this, LanguageActivity.class);
            startActivity(intent);
        });
        tvLang = findViewById(R.id.tvLang);

        sp = getSharedPreferences("language", MODE_PRIVATE);
        if(sp.getString("lang", "en").equals("vi")){
            tvLang.setText(getString(R.string.vietnam));
        } else {
            tvLang.setText(getString(R.string.english));
        }
    }
}
