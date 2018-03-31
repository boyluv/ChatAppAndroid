package com.example.tuanle.chatapplication.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tuanle.chatapplication.R;

import java.util.ArrayList;

public class DetailConservationAdapter extends RecyclerView.Adapter<DetailConservationAdapter.ItemHolder>{
    private ArrayList<String> conservation;
    private int number;
    public DetailConservationAdapter(ArrayList<String> listMsg) {
        this.conservation = listMsg;
        number = 0;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_conservation,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.message.setText(conservation.get(position));
        if(number%2 == 0)
        {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

// Add all the rules you need
            params.addRule(RelativeLayout.ALIGN_PARENT_END);

            holder.message.setLayoutParams(params);
        }
        else {
            {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

// Add all the rules you need
                params.addRule(RelativeLayout.ALIGN_PARENT_START);

                holder.message.setLayoutParams(params);
            }
        }
        number ++;
    }

    @Override
    public int getItemCount() {
        return conservation.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        private TextView message;
        public ItemHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
