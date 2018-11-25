package com.example.umer.userinterfacedesign;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

 private String[] data;
    public MyAdapter(String[] data) {
        this.data=data;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
       View view= layoutInflater.inflate(R.layout.list_item,viewGroup);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return this.data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
