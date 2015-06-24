package com.example.c4q_ac35.justmygoogle;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by c4q-marbella on 6/23/15.
 */
public interface SportsAPI {

    @GET("v1/rss?partnerKey=zBaFxRyGKCfxBagJG9b8pqLyndmvo7UU")
    public void getFeed(Callback<List<SportFields>> response);
}
