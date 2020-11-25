package com.example.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.petagram.R;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class PetsConstructor {

    private static final int LIKE = 1;
    private Context context;

    public PetsConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getData() {
       DataBase db = new DataBase(context);
       insertPets(db);
       return db.getAllPets();
    }
    public void insertPets(DataBase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Kitty");
        contentValues.put(DbConstants.PET_IMAGE, R.drawable.icons8_cat_head_96);

        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Doggy");
        contentValues.put(DbConstants.PET_IMAGE, R.drawable.icons8_pug_96);

        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Pandy");
        contentValues.put(DbConstants.PET_IMAGE, R.drawable.icons8_red_panda_96);

        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Parrot");
        contentValues.put(DbConstants.PET_IMAGE, R.drawable.icons8_parrot_96);

        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Nemo");
        contentValues.put(DbConstants.PET_IMAGE, R.drawable.icons8_fish_96);

        db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.PET_NAME, "Fox");
        contentValues.put(DbConstants.PET_IMAGE,  R.drawable.icons8_fox_96);

        db.insertPet(contentValues);
    }

    public void putPetRating(Pet pet) {
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.RATING_PET_ID, pet.getId());
        contentValues.put(DbConstants.RATING_NUMBER, LIKE);
        db.insertPetRating(contentValues);
    }

    public int getPetRating(Pet pet) {
        DataBase db = new DataBase(context);
        return db.getPetRating(pet);
    }
}
