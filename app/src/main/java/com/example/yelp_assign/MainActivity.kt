package com.example.yelp_assign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_YELP_KEY = "EHquGhEJiWtmZW47FPXnUZ6cBga3HGmh8xYfo1PhoLbZNEsvTzngjvDUT6DXl2Aecs9BMk5SgFMj_8-MlbkXCt-DiTa-Ib6BR49EZOC3cvtXPpZaiKUQaIMylUxCYXYx"
private const val TAG = "MainActivity"



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurants = mutableListOf<YelpRestaurant>()

        val adapter = RestaurantAdapter(this,restaurants)

        rvRest.adapter = adapter
        rvRest.layoutManager = GridLayoutManager(this,2)



        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurant("Bearer $API_YELP_KEY","rice","Toronto").enqueue(object : Callback<YelpSearchResult>{
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG,"onResponse $response")

                val body = response.body()

                if(body == null){
                    Log.w(TAG,"Did not receive body from YELP API")
                    return
                }
                restaurants.addAll(body.restaurants)

                restaurants.sortBy { it.name }


                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }


        })


    }



}