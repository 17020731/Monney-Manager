package com.example.moneymanager.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.models.App;
import com.example.moneymanager.models.HistoryChild;

import java.util.ArrayList;

public class HistoryChildAdapter extends RecyclerView.Adapter<HistoryChildAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<HistoryChild> mListHistory;
    private App app;

    public HistoryChildAdapter(Context mContext, ArrayList<HistoryChild>mListHistory){
        this.mContext = mContext;
        this.mListHistory = mListHistory;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_history_child, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        app = new App();
        HistoryChild history = mListHistory.get(position);
        int bgIcon = app.getICons(history.getType()).second;
        holder.icon.setImageResource(app.getICons(history.getType()).first);
        holder.icon.setColorFilter(Color.parseColor("#ffffff"));
        holder.bgIcon.setBackgroundResource(bgIcon);
        if(!history.getName().isEmpty()){
            holder.name.setText(history.getName());
        }
        else
            holder.name.setText(history.getType());

        if(history.getCategory().equals("expense")){
            holder.amount.setText("-"+history.getAmount());
        }else{
            holder.amount.setText(""+history.getAmount());
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout container;
        private LinearLayout bgIcon;
        private ImageView icon;
        private TextView name, amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            bgIcon = itemView.findViewById(R.id.bgIcon);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
