package com.example.moneymanager.setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.models.Item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AddIncomeActivity extends AppCompatActivity {
    private ImageView btnBack;
    private TextView title1, title2, title3;
    private RecyclerView mRecyclerView1, mRecyclerView2, mRecyclerView3;
    private LinearLayout bgIcon;
    private ImageView icon, btnSubmit;
    private EditText edName;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private static String uID = "0945455387test";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        uID = mAuth.getCurrentUser().getUid();

        bgIcon = findViewById(R.id.bgIcon);
        icon = findViewById(R.id.icon);
        icon.setColorFilter(Color.parseColor("#ffffff"));
        edName = findViewById(R.id.edName);
        btnSubmit = findViewById(R.id.btnSubmit);

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

        showItemsByTopic("Food", title1, mRecyclerView1, getListIncome());
        showItemsByTopic("Health", title2, mRecyclerView2, getListIncome());
        showItemsByTopic("Shopping", title3, mRecyclerView3, getListIncome());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString().trim();
                if(!name.isEmpty()){
                    String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    mDatabase.child("categories").child(uID).child("income").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            edName.setText("");
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                if(snapshot.getKey().equals(capName)){
                                    Toasty.warning(AddIncomeActivity.this, "Category exists!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            mDatabase.child("categories").child(uID).child("income").child(capName).setValue(icon.getTag(R.string.key));
                            Toasty.success(AddIncomeActivity.this, "Add success!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddIncomeActivity.this, CategorySettingActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else{
                    Toasty.error(AddIncomeActivity.this, "Please enter name!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showItemsByTopic(String topic, TextView title, RecyclerView recyclerView, ArrayList<Item> mListItem){
        title.setText(topic);
        showItems(recyclerView, getListIncome());
    }
    private ArrayList<Item> getListIncome(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("hamburger"));
        mListItem.add(new Item("potato"));
        mListItem.add(new Item("noodle"));
        mListItem.add(new Item("pizza"));
        mListItem.add(new Item("bread"));
        mListItem.add(new Item("fish"));
        mListItem.add(new Item("apple"));
        mListItem.add(new Item("ice_cream"));
        mListItem.add(new Item("cake"));
        mListItem.add(new Item("tea"));
        mListItem.add(new Item("glass"));
        mListItem.add(new Item("soda"));
        return mListItem;
    }
    private void showItems(RecyclerView recyclerItems, ArrayList<Item> mListItem){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setNestedScrollingEnabled(false);
        recyclerItems.setItemAnimator(new DefaultItemAnimator());
        AddItemsAdapter adapter = new AddItemsAdapter(AddIncomeActivity.this, mListItem, icon, bgIcon);
        recyclerItems.setAdapter(adapter);
    }

}
