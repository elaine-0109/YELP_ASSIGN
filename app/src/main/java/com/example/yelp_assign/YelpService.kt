package com.example.yelp_assign

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

public interface YelpService {

    @GET("businesses/search")

    fun searchRestaurant(
        @Header("Authorization") authHeader: String,
        @Query("term") searchTerm:String,
        @Query("location") location:String): Call<YelpSearchResult>

}