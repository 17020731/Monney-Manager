package com.example.moneymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.models.App;
import com.example.moneymanager.models.Item;

import java.util.ArrayList;

public class IncomeSettingAdapter extends RecyclerView.Adapter<IncomeSettingAdapter.ViewHolder>{

    Context mContext;
    private ArrayList<Item> mListItem;
    private App app;

    public IncomeSettingAdapter (Context mContext, ArrayList<Item>mListItem){
        this.mContext = mContext;
        this.mListItem = mListItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_income_setting, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        app = new App();
        Item incomeSetting = mListItem.get(position);
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItem.remove(position);
                notifyItemChanged(position);
                notifyDataSetChanged();
            }
        });
        holder.icon.setImageResource(app.getICons(incomeSetting.getType()));
        holder.type.setText(incomeSetting.getType());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView btnDel;
        private ImageView icon;
        private TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDel = itemView.findViewById(R.id.btnDel);
            icon = itemView.findViewById(R.id.icon);
            type = itemView.findViewById(R.id.type);
        }
    }



}