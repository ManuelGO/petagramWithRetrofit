package com.example.petagram.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petagram.R;
import com.example.petagram.adapters.MinicardAdapter;
import com.example.petagram.adapters.PetAdapter;
import com.example.petagram.pojo.Pet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Pet> pics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) v.findViewById(R.id.mini_cards_recyclerview);
            //        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 12, GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 7 is the sum of items in one repeated section
                switch (position % 7) {
                    // first three items span 3 columns each
                    case 0:
                    case 1:
                    case 2:
                        return 4;
                    // next four items span 2 columns each

                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 4;
                }
                throw new IllegalStateException("internal error");
            }
        });
        recyclerView.setLayoutManager(manager);

        initPetList();
        initAdapter();
        return v;
    }

    public void initAdapter() {
        MinicardAdapter adapter = new MinicardAdapter(pics, getActivity());
        recyclerView.setAdapter(adapter);
    }

    public void initPetList() {
        pics = new ArrayList<Pet>();
        pics.add(new Pet("Doggy", "3", 0, null));
        pics.add(new Pet("Doggy", "5", 0, null));
        pics.add(new Pet("Doggy", "3", 0, null));
        pics.add(new Pet("Doggy", "5", 0, null));
        pics.add(new Pet("Doggy", "0", 0, null));
        pics.add(new Pet("Doggy", "9", 0, null));
        pics.add(new Pet("Doggy", "8", 0, null));
        pics.add(new Pet("Doggy", "2", 0, null));
        pics.add(new Pet("Doggy", "3", 0, null));
        pics.add(new Pet("Doggy", "5", 0, null));
        pics.add(new Pet("Doggy", "1", 0, null));
        pics.add(new Pet("Doggy", "1", 0, null));

    }
}