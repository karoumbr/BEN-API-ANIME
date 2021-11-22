package com.bentechprotv.android.benapianime.model;

import com.google.gson.annotations.SerializedName;

public class Anime {
    @SerializedName("mal_id")
    public int id;
    public String title;
    public String url;
    @SerializedName("image_url")
    public String imageUrl;
    public String type;
    public double score;
}
