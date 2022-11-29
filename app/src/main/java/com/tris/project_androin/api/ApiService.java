package com.tris.project_androin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://translate.googleapis.com/translate_a/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("single")
    Call<List> translate(@Query("client") String client,
                         @Query("sl") String sl,
                         @Query("tl") String tl,
                         @Query("dt") String t,
                         @Query("q") String q);
}