package com.example.petagram.restApi;

import com.example.petagram.pojo.InstagramApiResponse;
import com.example.petagram.pojo.MediaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IEndpointsApi {

    @GET(RestApiConstants.GET_USR_INFO)
    Call<InstagramApiResponse> getMedia();

    @GET("/{media_id}/" + RestApiConstants.GET_MEDIA_INFO)
    Call<MediaResponse> getMediaDetail(@Path("media_id") String media_id);


}
