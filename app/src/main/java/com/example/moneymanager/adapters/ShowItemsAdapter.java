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
    private ImageView icons;
    private LinearLayout keyboard;

    public ShowItemsAdapter(Context mContext, ArrayList<Item>mListItem, ImageView icons, LinearLayout keyboard){
        this.mContext = mContext;
        this.mListItem = mListItem;
        this.icons = icons;
        this.keyboard = keyboard;
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
        final Item an_item = mListItem.get(position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(an_item.getType().equals("Add") && an_item.isExpense()){
                    Intent intent = new Intent(mContext, AddExpenseActivity.class);
                    mContext.startActivity(intent);
                } else if(an_item.getType().equals("Add") && !an_item.isExpense()){
                    Intent intent = new Intent(mContext, AddIncomeActivity.class);
                    mContext.startActivity(intent);
                }
                else{
                    keyboard.setVisibility(View.VISIBLE);
                    icons.setImageResource(app.getICons(an_item.getType()));
                    icons.setTag(R.string.key, an_item.getType());
                }
            }
        });
        holder.icon.setImageResource(app.getICons(an_item.getType()));
        holder.type.setText(an_item.getType());
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