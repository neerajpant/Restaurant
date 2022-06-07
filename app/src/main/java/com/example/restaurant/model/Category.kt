package com.example.restaurant.model

import com.google.gson.annotations.SerializedName


data class Category(val id:String,val name:String, @SerializedName("menu-items")  val menu_items:List<MenuItem>) {
}