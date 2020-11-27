package com.example.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private Context context;

    public DataBase(@Nullable Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
      // SQLiteDatabase db = this.getWritableDatabase(); // si no hago esto, no corre el onCreate.
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       // se crea la estructura de la base de datos.
        String queryCreatePetTable = "CREATE TABLE " +
                DbConstants.PET_TABLE + "(" +
                DbConstants.PET_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbConstants.PET_NAME + " TEXT, " +
                DbConstants.PET_IMAGE + " INTEGER" + ")";

        String queryCreatePetRating = "CREATE TABLE " + DbConstants.RATING_TABLE + "(" +
                DbConstants.RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbConstants.RATING_PET_ID + " INTEGER, " +
                DbConstants.RATING_NUMBER + " INTEGER, " +
                "FOREIGN KEY (" + DbConstants.RATING_PET_ID+ ")" +
                "REFERENCES " + DbConstants.PET_TABLE + "(" + DbConstants.PET_ID + ")"
                + ")";
        String queryCreateFavoritePetsTable = "CREATE TABLE " + DbConstants.FAVORITE_PETS_TABLE + "(" +
                DbConstants.FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbConstants.FAVORITE_PETS_ID + " INTEGER, " +
                "FOREIGN KEY (" + DbConstants.FAVORITE_PETS_ID + ")" +
                "REFERENCES " + DbConstants.PET_TABLE + "(" + DbConstants.PET_ID + ")" + ")";

        db.execSQL(queryCreatePetTable);
        db.execSQL(queryCreatePetRating);
        db.execSQL(queryCreateFavoritePetsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.PET_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.RATING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.FAVORITE_PETS_TABLE);
        onCreate(db);
    }

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.PET_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor response = db.rawQuery(query, null);

        while (response.moveToNext()) {
            Pet currentPet = new Pet();
            currentPet.setId(response.getInt(0));
            currentPet.setName(response.getString(1));
            currentPet.setImage(response.getInt(2));

            String ratingQuery = "SELECT COUNT(" + DbConstants.RATING_NUMBER +")" + " FROM " + DbConstants.RATING_TABLE +
                    " WHERE " + DbConstants.RATING_PET_ID + " = " + currentPet.getId();
            Cursor cursor = db.rawQuery(ratingQuery, null);

            if (cursor.moveToNext()) {
                currentPet.setRating(cursor.getString(0));
            } else {
                currentPet.setRating("0");
            }

            pets.add(currentPet);
        }
        db.close();
        return pets;
    }


    public void insertPet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbConstants.PET_TABLE, null, contentValues);
        db.close();
    }

    public void insertPetRating(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbConstants.RATING_TABLE, null, contentValues);
        db.close();
    }

    public int getPetRating(Pet pet) {
        int rating = 0;
        String query = "SELECT COUNT(" + DbConstants.RATING_NUMBER +")" + " FROM " + DbConstants.RATING_TABLE +
                " WHERE " + DbConstants.RATING_PET_ID + " = " + pet.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            rating = cursor.getInt(0);
        }
        System.out.print(rating);
        System.out.print(cursor);
        db.close();
        return rating;
    }
    public ArrayList<Integer> getFavoritePets() {
        ArrayList<Integer> favorites = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.FAVORITE_PETS_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor response = db.rawQuery(query, null);

        while (response.moveToNext()) {
            favorites.add(response.getInt(1));
        }
        db.close();
        return favorites;
    };

    public void insertFavoritePet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbConstants.FAVORITE_PETS_TABLE, null, contentValues);
        db.close();
    }

}
