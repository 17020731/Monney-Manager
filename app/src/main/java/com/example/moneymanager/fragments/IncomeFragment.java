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
import com.example.moneymanager.adapters.IncomeSettingAdapter;
import com.example.moneymanager.models.Item;

import java.util.ArrayList;

public class IncomeFragment extends Fragment {
    private RecyclerView recyclerIncome;
    private IncomeSettingAdapter mAdapter;
    private ArrayList<Item> mListIncome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_income, container, false);
        recyclerIncome = v.findViewById(R.id.recycleIncome);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerIncome.setLayoutManager(layoutManager1);
        recyclerIncome.setItemAnimator(new DefaultItemAnimator());

        mListIncome = new ArrayList<>();
        mListIncome.add(new Item("Food"));
        mListIncome.add(new Item("Car"));
        mListIncome.add(new Item("Sport"));
        mListIncome.add(new Item("Health"));

        mAdapter = new IncomeSettingAdapter(getContext(), mListIncome);
        recyclerIncome.setAdapter(mAdapter);
        return v;
    }
}
