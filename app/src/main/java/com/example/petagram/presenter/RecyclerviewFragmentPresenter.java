package com.example.petagram.presenter;


import android.content.Context;
import android.util.Log;

import com.example.petagram.db.PetsConstructor;
import com.example.petagram.fragments.IRecyclerviewFragmentView;
import com.example.petagram.pojo.Id;
import com.example.petagram.pojo.InstagramApiResponse;
import com.example.petagram.pojo.MediaResponse;
import com.example.petagram.pojo.Pet;
import com.example.petagram.restApi.IEndpointsApi;
import com.example.petagram.restApi.adapter.RestApiAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewFragmentPresenter implements IRecyclerviewFragmentPresenter {

    private PetsConstructor petsConstructor;
    ArrayList<Pet> pets = new ArrayList<Pet>();

    private IRecyclerviewFragmentView recyclerviewFragmentView;
    private Context context;

    public RecyclerviewFragmentPresenter(IRecyclerviewFragmentView recyclerviewFragmentView, Context context) {
        this.recyclerviewFragmentView = recyclerviewFragmentView;
        this.context = context;
       // getPetsDataBase();
        getRecentMedia();
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

    @Override
    public void getRecentMedia() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson recentMediaGson = restApiAdapter.makeGsonDeserializerForRecentMedia();
        IEndpointsApi endpointsApi = restApiAdapter.setConnectionWithIGApi(recentMediaGson);
        Call<InstagramApiResponse> petResponseCall = endpointsApi.getMedia();

        petResponseCall.enqueue(new Callback<InstagramApiResponse>() {
            @Override
            public void onResponse(Call<InstagramApiResponse> call, Response<InstagramApiResponse> response) {
                if (response != null) {
                    InstagramApiResponse data = response.body();

                    ArrayList<Id> mediaIds = data.getMedia().getData(); // listado de ids de los posts en IG.
                    String name = data.getUsername();
                    for (Id id: mediaIds) {
                        Pet pet = new Pet();
                        Call<MediaResponse> mediaResponse = endpointsApi.getMediaDetail(id.getId());
                        mediaResponse.enqueue(new Callback<MediaResponse>() {
                            @Override
                            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                                if (response != null) {
                                    MediaResponse info = response.body();
                                    pet.setName(name);
                                    pet.setMedia_url(info.getMedia_url());
                                    pet.setRating(String.valueOf(info.getLike_count()));
                                    pets.add(pet);
                                }
                                showPetsRV();
                            }

                            @Override
                            public void onFailure(Call<MediaResponse> call, Throwable t) {
                                Log.e("Conextion Fail", t.toString());
                            }
                        });
                    }
                } else {
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<InstagramApiResponse> call, Throwable t) {
                Log.e("Conextion Fail", t.toString());
            }
        });
    }

}
