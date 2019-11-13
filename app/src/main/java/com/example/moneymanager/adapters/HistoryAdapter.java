package com.example.moneymanager.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.models.App;
import com.example.moneymanager.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<History> mListHistory;
    private App app;

    public HistoryAdapter(Context mContext, ArrayList<History>mListHistory){
        this.mContext = mContext;
        this.mListHistory = mListHistory;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        app = new App();
        History history = mListHistory.get(position);
        holder.icon.setImageResource(app.getICons(history.getType()));
        holder.name.setText(history.getName());
        holder.amount.setText(history.getAmount());
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
        private ImageView icon;
        private TextView name, amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
