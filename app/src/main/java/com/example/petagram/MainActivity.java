package com.example.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.petagram.adapters.PetAdapter;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pet> pets;
    private Toolbar actionbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionbar = (Toolbar) findViewById(R.id.action_bar_main);
        setSupportActionBar(actionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.pet_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);

        initPetList();
        initAdapter();
    }

    public void initAdapter() {
        PetAdapter adapter = new PetAdapter(pets, this);
        recyclerView.setAdapter(adapter);
    }

    public void initPetList() {
        pets = new ArrayList<Pet>();
        pets.add(new Pet("Kitty", "3", R.drawable.icons8_cat_head_96));
        pets.add(new Pet("Doggy", "1", R.drawable.icons8_pug_96));
        pets.add(new Pet("Pandy", "2", R.drawable.icons8_red_panda_96));
        pets.add(new Pet("Parrot", "2", R.drawable.icons8_parrot_96));
        pets.add(new Pet("Nemo", "4", R.drawable.icons8_fish_96));
        pets.add(new Pet("Fox", "1", R.drawable.icons8_fox_96));

    }

    public void takePicture(View view) {
        Toast.makeText(this, "Picture taked", Toast.LENGTH_SHORT).show();
    }

    public void goToNextActivity(View view) {
        Intent intent = new Intent(this, FiveFavoriteAnimalsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}