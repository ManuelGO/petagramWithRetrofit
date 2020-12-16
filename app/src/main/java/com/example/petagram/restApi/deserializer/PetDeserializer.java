package com.example.petagram.restApi.deserializer;

import com.example.petagram.pojo.Pet;
import com.example.petagram.restApi.JsonKeys;
import com.example.petagram.restApi.PetResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PetDeserializer implements JsonDeserializer<PetResponse> {
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        // TODO: revisar objeto y aplicar keys necesarios.
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petResponse.setPets(deserializePetFromJson(petResponseData));
        return null;
    }

    private ArrayList<Pet> deserializePetFromJson(JsonArray petResponseData ) {
        ArrayList<Pet> pets = new ArrayList<>();

        for (int i = 0; i < petResponseData.size(); i++) {
            JsonObject petResponseObj = petResponseData.get(i).getAsJsonObject();
            JsonObject imageId = petResponseObj.getAsJsonObject(JsonKeys.USER_ID);
            // TODO: Continuar dependiendo estructura del objeto.
        }


        return pets;
    }
}
