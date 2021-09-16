package com.example.yelp_assign

import com.google.gson.annotations.SerializedName
import java.util.*

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>
)

data class YelpRestaurant (
    val name: String,
    val rating: Double,
    val price: String,
    @SerializedName("review_count") val numReviews:Int,
    @SerializedName("distance") val distance:Double,
    @SerializedName("image_url") val imageUrl: String,
    val categories: List<YelpCategory>,
    val location: YelpLocation
){
    fun showDistance():String{
        val milePerMeter = 0.00062137119
        val distanceMile = "%.2f".format(distance*milePerMeter)

        return "$distanceMile mi"
    }
}

data class YelpLocation (
    @SerializedName("address1") val address: String,
    @SerializedName("address2") val address2: String,
    @SerializedName("address3") val address3: String,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("zip_code") val zipcode: String,
)



data class YelpCategory (
    val title: String
    )



