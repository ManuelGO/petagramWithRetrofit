package com.example.petagram.restApi;

import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

public class PetResponse {
    ArrayList<Pet> pets;

    public PetResponse(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
