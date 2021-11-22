package com.bentechprotv.android.benapianime.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class JikanResponse {
    @SerializedName("results")
    public List<Anime> animes = new ArrayList<>();
}
