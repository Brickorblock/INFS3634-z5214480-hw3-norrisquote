package com.example.z5214480_hw3_norrisquote.entities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//interface for API call
public interface QuoteService {
    // uses random as the API ticker to generate random quote
    @GET("random")
    //accepts a parameter to query for specific category (i.e. Dev)
    Call<QuoteResponse> getQuote(@Query("category") String category);
}
