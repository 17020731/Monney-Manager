package com.example.moneymanager.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class AddExpenseActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
//        uID = mAuth.getCurrentUser().getUid();

        bgIcon = findViewById(R.id.bgIcon);
        icon = findViewById(R.id.icon);
        icon.setTag(R.string.key, "food");
        edName = findViewById(R.id.edName);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        title3 = findViewById(R.id.title3);

        mRecyclerView1 = findViewById(R.id.mRecycleView1);
        mRecyclerView2 = findViewById(R.id.mRecycleView2);
        mRecyclerView3 = findViewById(R.id.mRecycleView3);

        showItemsByTopic("Food", title1, mRecyclerView1, getListExpense());
        showItemsByTopic("Health", title2, mRecyclerView2, getListExpense());
        showItemsByTopic("Shopping", title3, mRecyclerView3, getListExpense());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = edName.getText().toString().trim();
                if(!name.isEmpty()){
                    mDatabase.child("categories").child(uID).child("expense").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            edName.setText("");
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                if(snapshot.getKey().equals(name)){
                                    return;
                                }
                            }
                            mDatabase.child("categories").child(uID).child("expense").child(name).setValue(icon.getTag(R.string.key));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }

    private void showItemsByTopic(String topic, TextView title, RecyclerView recyclerView, ArrayList<Item> mListItem){
        title.setText(topic);
        showItems(recyclerView, getListExpense());
    }
    private ArrayList<Item> getListExpense(){
        ArrayList<Item>mListItem = new ArrayList<>();
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));
        mListItem.add(new Item("home"));

        return mListItem;
    }
    private void showItems(RecyclerView recyclerItems, ArrayList<Item> mListItem){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setNestedScrollingEnabled(false);
        recyclerItems.setItemAnimator(new DefaultItemAnimator());
        AddItemsAdapter adapter = new AddItemsAdapter(AddExpenseActivity.this, mListItem, icon, bgIcon);
        recyclerItems.setAdapter(adapter);
    }

}
