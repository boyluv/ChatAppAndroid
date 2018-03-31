package com.example.tuanle.chatapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tuanle.chatapplication.Activities.DetailConservationActivity;
import com.example.tuanle.chatapplication.R;

import java.util.ArrayList;

public class ConservationListAdapter extends RecyclerView.Adapter<ConservationListAdapter.ItemViewHolder>{
    private ArrayList<String> conservationList;
    private Context mContext;
    public ConservationListAdapter(Context context, ArrayList<String> list) {
        conservationList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conservation, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String username = "User " + position;
        holder.userName.setText(username);
        holder.lastChat.setText(conservationList.get(position));
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailConservationActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conservationList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView lastChat;
        public ItemViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.tv_user_name);
            lastChat =(TextView) itemView.findViewById(R.id.tv_last_message);
        }
    }
}
