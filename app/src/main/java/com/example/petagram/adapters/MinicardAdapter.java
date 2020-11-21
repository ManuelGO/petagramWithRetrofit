package com.example.petagram.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class MinicardAdapter extends RecyclerView.Adapter<MinicardAdapter.MinicardViewHolder> {
    ArrayList<Pet> pictures;
    Activity activity;

    public MinicardAdapter(ArrayList<Pet> pictures, Activity activity) {
        this.pictures = pictures;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MinicardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.minicard, parent, false);
        this.activity = activity;
        return new MinicardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MinicardViewHolder holder, int position) {
        final Pet pic = pictures.get(position);
        holder.picRate.setText(pic.getRating());

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    static class MinicardViewHolder extends RecyclerView.ViewHolder {
        private TextView picRate;

        public MinicardViewHolder(@NonNull View itemView) {
            super(itemView);
            picRate = (TextView) itemView.findViewById(R.id.pet_mini_card_rating);
        }
    }
}
