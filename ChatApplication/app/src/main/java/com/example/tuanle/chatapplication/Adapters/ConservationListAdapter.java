package com.example.tuanle.chatapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tuanle.chatapplication.Activities.DetailConservationActivity;
import com.example.tuanle.chatapplication.R;
import com.example.tuanle.chatapplication.Response.ConvoResponse;
import com.example.tuanle.chatapplication.Utils.Constants.ExtraKey;

import java.util.ArrayList;

public class ConservationListAdapter extends RecyclerView.Adapter<ConservationListAdapter.ItemViewHolder>{
    private ArrayList<ConvoResponse> listConvo;
    private Context mContext;
    public ConservationListAdapter(Context context,ArrayList<ConvoResponse> mine) {
        mContext = context;
        listConvo = mine;
        //Log.d("listConvo", "Adapter" + listConvo.get(0).getRep_message());

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conservation, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final ConvoResponse curResponse = listConvo.get(position);
        holder.userName.setText(curResponse.getUser_name());
        holder.lastChat.setText(curResponse.getRep_message());
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start Activity to Detail Conservation
                Intent intent = new Intent(mContext, DetailConservationActivity.class);
                Log.d("DetailConvo", "This is convo Id " + curResponse.getRef_convo_id());
                intent.putExtra(ExtraKey.CONSERVATION_ID, curResponse.getRef_convo_id());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listConvo.size();
    }

    public void setListConvo(ArrayList<ConvoResponse> listConvo) {
        this.listConvo = listConvo;
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
