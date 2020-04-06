package com.example.z5214480_hw3_norrisquote.entities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuoteService {
    @GET("random")
    Call<QuoteResponse> getQuote(@Query("category") String category);
}
