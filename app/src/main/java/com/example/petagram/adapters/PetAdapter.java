package com.example.petagram.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.db.PetsConstructor;
import com.example.petagram.pojo.Pet;
import com.example.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PetAdapter extends  RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    ArrayList<Pet> pets;
    Activity activity;

    public PetAdapter(ArrayList<Pet> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet, parent, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PetViewHolder holder, int position) {
        final Pet pet = pets.get(position);
        // holder.imgPhoto.setImageResource(pet.getImage());
        Picasso.get().load(pet.getMedia_url()).into(holder.imgPhoto);
        holder.petCardName.setText(pet.getName());
        holder.petCardRating.setText(pet.getRating());

        holder.imgIconName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(activity, "Diste like a " + pet.getName(), Toast.LENGTH_SHORT).show();

            PetsConstructor petsConstructor = new PetsConstructor(activity);
            petsConstructor.putPetRating(pet);
            int rating = petsConstructor.getPetRating(pet);
            holder.petCardRating.setText(Integer.toString(rating));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhoto;
        private TextView petCardName;
        private TextView petCardRating;
        private ImageView imgIconRating;
        private ImageView imgIconName;


        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto        = (ImageView) itemView.findViewById(R.id.imgPhoto);
            petCardName     = (TextView) itemView.findViewById(R.id.pet_card_name);
            petCardRating   = (TextView) itemView.findViewById(R.id.pet_card_rating);
            imgIconRating   = (ImageView) itemView.findViewById(R.id.img_icon_rating);
            imgIconName     = (ImageView) itemView.findViewById(R.id.img_icon_name);
        }
    }
}
