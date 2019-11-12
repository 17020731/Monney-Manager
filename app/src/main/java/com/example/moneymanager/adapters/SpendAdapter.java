package com.example.moneymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.models.Data;

import java.util.ArrayList;

public class SpendAdapter extends RecyclerView.Adapter<SpendAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<Data>mListData;

    public SpendAdapter (Context mContext, ArrayList<Data>mListData){
        this.mContext = mContext;
        this.mListData = mListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendAdapter.ViewHolder holder, int position) {
        Data data = mListData.get(position);
        holder.name.setText(data.getName());
        holder.percent.setText(data.getPercent()+"%");
        holder.amount.setText(data.getAmount()+"");
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, percent, amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name);
            percent = itemView.findViewById(R.id.percent);
            amount = itemView.findViewById(R.id.amount);

        }
    }
}
