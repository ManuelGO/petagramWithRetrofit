package com.example.petagram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.adapters.PetAdapter;
import com.example.petagram.pojo.Pet;
import com.example.petagram.presenter.IRecyclerviewFragmentPresenter;
import com.example.petagram.presenter.RecyclerviewFragmentPresenter;

import java.util.ArrayList;


public class RecyclerviewFragment extends Fragment implements IRecyclerviewFragmentView {

    private RecyclerView recyclerView;
    private IRecyclerviewFragmentPresenter presenter;
    private PetAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // asociamos el fragment a un layout:
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.pet_recycler_view);
        presenter = new RecyclerviewFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generateLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pet> pets) {
        adapter = new PetAdapter(pets, getActivity());

        return adapter;
    }

    @Override
    public void initRVAdapter(PetAdapter petAdapter) {
        recyclerView.setAdapter(adapter);
    }
}
