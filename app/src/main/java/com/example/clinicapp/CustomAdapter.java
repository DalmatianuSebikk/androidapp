package com.example.clinicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    ArrayList investigation_id, investigation_name, investigation_price;

    CustomAdapter(Context context, ArrayList investigation_id, ArrayList investigation_name, ArrayList investigation_price) {
        this.context = context;
        this.investigation_id = investigation_id;
        this.investigation_name = investigation_name;
        this.investigation_price = investigation_price;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.investigation_name_text.setText(String.valueOf(investigation_name.get(position)));
        holder.investigation_price_text.setText(String.valueOf(investigation_price.get(position)));
    }

    @Override
    public int getItemCount() {
        return investigation_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView investigation_name_text, investigation_price_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            investigation_name_text = itemView.findViewById(R.id.investigation_name);
            investigation_price_text = itemView.findViewById(R.id.investigation_price);
        }
    }
}
