package com.example.moneymanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.adapters.ExpenseSettingAdapter;
import com.example.moneymanager.models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

public class ExpenseFragment extends Fragment {
    private RecyclerView recyclerExpense;
    private ExpenseSettingAdapter mAdapter;
    private ArrayList<Item> mListExpense;

    private NiceSpinner spinner;

    private DatabaseReference mDatabase;
    private static String uID = "0945455387test";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expense, container, false);

        recyclerExpense = v.findViewById(R.id.recycleExpense);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerExpense.setLayoutManager(layoutManager1);
        recyclerExpense.setItemAnimator(new DefaultItemAnimator());

        mListExpense = new ArrayList<>();
        mAdapter = new ExpenseSettingAdapter(getContext(), mListExpense);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("categories").child(uID).child("expense").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    System.out.println(snapshot.getKey());
                    mListExpense.add(new Item((String) snapshot.getValue(), snapshot.getKey()));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerExpense.setAdapter(mAdapter);
        return v;
    }
}
