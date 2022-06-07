package com.example.restaurant.model

data class Restaurant(val id:String,
                      val name:String,
                      val neighborhood:String,
                      val address:String,
                      val photograph:String,
                      val cuisine_type:String,
                      val operating_hours:Map<String,String>,
                      val reviews:List<Review>)
{

}