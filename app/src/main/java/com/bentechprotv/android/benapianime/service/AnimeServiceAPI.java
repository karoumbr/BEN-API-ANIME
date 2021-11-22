package com.bentechprotv.android.benapianime.service;

import com.bentechprotv.android.benapianime.model.JikanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimeServiceAPI {
    //https://api.jikan.moe/v3/search/anime?q=ippo
    @GET("search/anime")
    public abstract Call<JikanResponse> searchAnime(@Query("q") String query);
}
