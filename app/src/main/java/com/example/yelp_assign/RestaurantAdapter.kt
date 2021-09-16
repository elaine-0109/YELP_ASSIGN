package com.example.yelp_assign

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_restaurant.view.*
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.restaurant_detail.view.*


class RestaurantAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount() = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(restaurant: YelpRestaurant) {
            itemView.tvName.text = restaurant.name
            itemView.ratingBar.rating = restaurant.rating.toFloat()
            itemView.tvReview.text = "${restaurant.numReviews} Reviews"
            itemView.tvAddress.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            itemView.tvDistance.text = restaurant.showDistance()
            itemView.tvPrice.text = restaurant.price

            itemView.setOnClickListener{
                val intent = Intent()
                intent.setClass(it.context,RestaurantDetailActivity::class.java)
                intent.putExtra("name",itemView.tvName.text.toString())
                intent.putExtra("location","${restaurant.location.address} , " +
                        "${restaurant.location.city} , ${restaurant.location.state} , " +
                        "${restaurant.location.country} , ${restaurant.location.zipcode} ")
                intent.putExtra("review", itemView.tvReview.text.toString())
                itemView.imageView.buildDrawingCache()
                val bmp: Bitmap = itemView.imageView.getDrawingCache()
                intent.putExtra("img", bmp)
                it.context.startActivity(intent)
            }

            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions()
                .transform(CenterCrop(),RoundedCorners(20))).into(itemView.imageView)
        }




    }


}
