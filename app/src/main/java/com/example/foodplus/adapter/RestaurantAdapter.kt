package com.example.foodplus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplus.R
import com.example.foodplus.models.RestaurantData

class RestaurantAdapter (private val context: Context, private val restaurantData: List<RestaurantData>, val listener : (RestaurantData) -> Unit)
    : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>(){

        class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view){

            val name = view.findViewById<TextView>(R.id.tvRestaurantName)
            val hilghlight = view.findViewById<TextView>(R.id.tvRestaurantHighlight)
            val image = view.findViewById<ImageView>(R.id.ivRestaurantImage)

            fun bindView(restaurantData: RestaurantData, listener: (RestaurantData) -> Unit) {
                name.text = restaurantData.name
                hilghlight.text = restaurantData.highlights
                image.setImageResource(restaurantData.image)

                itemView.setOnClickListener {
                    listener(restaurantData)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_restaurant_list_discover, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int
    ) {
       holder.bindView(restaurantData[position], listener)
    }

    override fun getItemCount(): Int = restaurantData.size

}