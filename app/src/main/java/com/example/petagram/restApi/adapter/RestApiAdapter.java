package com.example.petagram.restApi.adapter;

import com.example.petagram.restApi.IEndpointsApi;
import com.example.petagram.restApi.PetResponse;
import com.example.petagram.restApi.RestApiConstants;
import com.example.petagram.restApi.deserializer.PetDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {
    public IEndpointsApi setConnectionWithIGApi(Gson gson) {
        System.out.println(RestApiConstants.ROOT_URL);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiConstants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IEndpointsApi.class);
    }

    public Gson makeGsonDeserializerForRecentMedia() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeserializer());

        return gsonBuilder.create();
    }

}
