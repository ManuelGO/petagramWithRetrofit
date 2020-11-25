package com.example.petagram.presenter;


import android.content.Context;

import com.example.petagram.db.PetsConstructor;
import com.example.petagram.fragments.IRecyclerviewFragmentView;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class RecyclerviewFragmentPresenter implements IRecyclerviewFragmentPresenter {

    private PetsConstructor petsConstructor;
    ArrayList<Pet> pets;

    private IRecyclerviewFragmentView recyclerviewFragmentView;
    private Context context;

    public RecyclerviewFragmentPresenter(IRecyclerviewFragmentView recyclerviewFragmentView, Context context) {
        this.recyclerviewFragmentView = recyclerviewFragmentView;
        this.context = context;
        getPetsDataBase();
    }

    @Override
    public void getPetsDataBase() {
        petsConstructor = new PetsConstructor(context);
        pets = petsConstructor.getData();
        showPetsRV();
    }

    @Override
    public void showPetsRV() {
        recyclerviewFragmentView.initRVAdapter(recyclerviewFragmentView.createAdapter(pets));
        recyclerviewFragmentView.generateLinearLayout();
    }
}
