package com.example.petagram.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    ArrayList<Pet> favoritePets;
    Activity activity;

    public FavoriteAdapter (ArrayList<Pet> favoritePets, Activity activity) {
        this.favoritePets = favoritePets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        final Pet pet = favoritePets.get(position);
        holder.imgPhoto.setImageResource(pet.getImage());
        holder.petCardName.setText(pet.getName());
        holder.petCardRating.setText(pet.getRating());
    }

    @Override
    public int getItemCount() {
        return favoritePets.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhoto;
        private TextView petCardName;
        private TextView petCardRating;
        private ImageView imgIconRating;
        private ImageView imgIconName;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto        = (ImageView) itemView.findViewById(R.id.imgPhoto);
            petCardName     = (TextView) itemView.findViewById(R.id.pet_card_name);
            petCardRating   = (TextView) itemView.findViewById(R.id.pet_card_rating);
            imgIconRating   = (ImageView) itemView.findViewById(R.id.img_icon_rating);
            imgIconName     = (ImageView) itemView.findViewById(R.id.img_icon_name);
        }
    }
}
