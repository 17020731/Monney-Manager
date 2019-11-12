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

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExpenseFragment extends Fragment {
    private RecyclerView recyclerExpense;
    private ExpenseSettingAdapter mAdapter;
    private ArrayList<Item> mListExpense;

    private NiceSpinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expense, container, false);

        recyclerExpense = v.findViewById(R.id.recycleExpense);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerExpense.setLayoutManager(layoutManager1);
        recyclerExpense.setItemAnimator(new DefaultItemAnimator());

        mListExpense = new ArrayList<>();
        mListExpense.add(new Item("Home"));
        mListExpense.add(new Item("Bills"));
        mListExpense.add(new Item("Transportation"));
        mListExpense.add(new Item("Home"));
        mListExpense.add(new Item("Car"));
        mListExpense.add(new Item("Entertainment"));
        mListExpense.add(new Item("Shopping"));
        mListExpense.add(new Item("Clothing"));
        mListExpense.add(new Item("Insurance"));
        mListExpense.add(new Item("Tax"));
        mListExpense.add(new Item("Telephone"));
        mListExpense.add(new Item("Cigarette"));
        mListExpense.add(new Item("Health"));
        mListExpense.add(new Item("Sport"));
        mListExpense.add(new Item("Baby"));
        mListExpense.add(new Item("Pet"));

        mAdapter = new ExpenseSettingAdapter(getContext(), mListExpense);
        recyclerExpense.setAdapter(mAdapter);

        return v;
    }
}
