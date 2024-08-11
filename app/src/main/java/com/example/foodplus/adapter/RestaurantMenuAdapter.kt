package com.example.foodplus.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodplus.R
import com.example.foodplus.models.MenuData

class RestaurantMenuAdapter(private val context: Context, private val MenuData : List<MenuData>, val listener: (MenuData) -> Unit)
    : RecyclerView.Adapter<RestaurantMenuAdapter.MenuViewHolder>(){


    class MenuViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name : TextView = view.findViewById(R.id.tvMenuName)
        val stock : TextView = view.findViewById(R.id.tvStockAmount)
        val price : TextView = view.findViewById(R.id.tvPrice)
        val normalPrice: TextView = view.findViewById(R.id.tvNormalPrice)
        val image : ImageView = view.findViewById(R.id.ivMenuImage)

        fun bindView(MenuData: MenuData, listener: (MenuData) -> Unit) {
            name.text = MenuData.name
            stock.text = "${MenuData.stock} left"
            price.text = "Rs${MenuData.price}"
            normalPrice.paintFlags = normalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            normalPrice.text = "Rs${MenuData.normalPrice}"
            image.setImageResource(MenuData.image)

            itemView.setOnClickListener {
                listener(MenuData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_restaurant_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int = MenuData.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
       holder.bindView(MenuData[position], listener)
    }
}