package com.example.moneymanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.activities.AddExpenseActivity;
import com.example.moneymanager.activities.AddIncomeActivity;
import com.example.moneymanager.models.App;
import com.example.moneymanager.models.Item;

import java.util.ArrayList;

public class ShowItemsAdapter extends RecyclerView.Adapter<ShowItemsAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<Item> mListItem;

    private App app;
    public ShowItemsAdapter(Context mContext, ArrayList<Item>mListItem){
        this.mContext = mContext;
        this.mListItem = mListItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_show_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        app = new App();
        final Item item = mListItem.get(position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(item.getType().equals("Add") && item.isExpense()){
                    Intent intent = new Intent(mContext, AddExpenseActivity.class);
                    mContext.startActivity(intent);
                } else if(item.getType().equals("Add") && !item.isExpense()){
                    Intent intent = new Intent(mContext, AddIncomeActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
        holder.icon.setImageResource(app.getICons(item.getType()));
        holder.type.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item;

        private ImageView icon;

        private TextView type;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            icon = itemView.findViewById(R.id.icon);

            type = itemView.findViewById(R.id.type);

        }
    }
}