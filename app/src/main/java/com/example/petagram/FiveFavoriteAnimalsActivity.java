package com.example.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.petagram.adapters.PetAdapter;
import com.example.petagram.db.PetsConstructor;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FiveFavoriteAnimalsActivity extends AppCompatActivity {
    ArrayList<Pet> favoritePets = new ArrayList<>();
    private RecyclerView recyclerView;
    Toolbar childActionbar;
    private PetsConstructor petsConstructor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_favorite_animals);

        childActionbar = (Toolbar) findViewById(R.id.action_bar2);
        setSupportActionBar(childActionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.favorites_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        initPetList();
        initAdapter();
    }

    public void initAdapter() {
        PetAdapter adapter = new PetAdapter(favoritePets, this);
        recyclerView.setAdapter(adapter);
    }

    public void initPetList() {
        petsConstructor = new PetsConstructor(this.getApplicationContext());
        ArrayList<Pet> pets = petsConstructor.getData();
        ArrayList<Integer> idFavs = petsConstructor.getFavoritePets();
        //eliminar dulicados:
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(idFavs);
        idFavs.clear();
        idFavs.addAll(set);

        System.out.println("ANIMALES FAVORITOS " + idFavs.size());
        ArrayList<Pet> result = new ArrayList<>();
        for (Integer id: idFavs) {
            for (Pet pet: pets) {
                if (pet.getId() == id) {
                    favoritePets.add((pet));
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.contact_item:
                Intent newIntent = new Intent(this, ContactActivity.class);
                startActivity(newIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
