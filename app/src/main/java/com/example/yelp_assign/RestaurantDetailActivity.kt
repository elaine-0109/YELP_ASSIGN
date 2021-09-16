package com.example.yelp_assign


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.os.Parcelable
import android.widget.RatingBar


class RestaurantDetailActivity : AppCompatActivity() {


    companion object{
        const val EXTRA_REST = "extra_rest"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_detail)

        val intent = intent

        val imgSrc = findViewById<ImageView>(R.id.imageRest)
        val tvRestName = findViewById<TextView>(R.id.tvRestName)
        val tvRestAddress = findViewById<TextView>(R.id.tvRestAddress)


        val review = findViewById<TextView>(R.id.review)

        tvRestName.text = intent.getStringExtra("name")
        tvRestAddress.text = intent.getStringExtra("location")

        review.text = intent.getStringExtra("review")

        val bitmap = intent.getParcelableExtra<Parcelable>("img") as Bitmap?

        imgSrc.setImageBitmap(bitmap)


    }



}