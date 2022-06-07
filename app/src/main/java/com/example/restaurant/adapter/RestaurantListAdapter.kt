package com.example.restaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.R

import com.example.restaurant.databinding.LayoutRestaurantBinding
import com.example.restaurant.model.Restaurant


class RestaurantListAdapter(private val restaurants: ArrayList<Restaurant>) :
    ListAdapter<Restaurant, RestaurantListAdapter.CurrencyViewHolder>(BreedsCompartor()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        val itemLayoutBinding = DataBindingUtil.inflate<LayoutRestaurantBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_restaurant, parent, false
        )
        return CurrencyViewHolder(itemLayoutBinding)

    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = restaurants[position]
        holder.bind(currentItem)



    }override fun getItemCount(): Int {
        return restaurants.size
    }
    fun addOption(restaurants: List<Restaurant>) {
        this.restaurants.apply {
            clear()
            addAll(restaurants)
            notifyDataSetChanged()
        }
    }


      class CurrencyViewHolder(private val binding: LayoutRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var restaurant: Restaurant? = null

          fun bind(restaurant: Restaurant) {
            this.restaurant = restaurant
            binding.apply {
                txtName.text=restaurant.name
                txtNeighbor.text=restaurant.neighborhood
                txtCusine.text=restaurant.cuisine_type

            }



        }


    }



    class BreedsCompartor : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {

            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }


    }


}