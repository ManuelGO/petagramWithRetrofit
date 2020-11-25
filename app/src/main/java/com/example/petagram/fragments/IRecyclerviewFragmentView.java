package com.example.petagram.fragments;

import com.example.petagram.adapters.PetAdapter;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public interface IRecyclerviewFragmentView {

    public void generateLinearLayout();
    public PetAdapter createAdapter(ArrayList<Pet> pets);
    public void initRVAdapter(PetAdapter petAdapter);
}
