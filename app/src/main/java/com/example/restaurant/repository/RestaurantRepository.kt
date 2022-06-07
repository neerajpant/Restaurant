package com.example.restaurant.repository

import android.content.Context
import android.util.Log
import com.example.restaurant.api.NetworkApi

import com.example.restaurant.model.Menu
import com.example.restaurant.model.Restaurant
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: NetworkApi,
    private val context:Context

): ResturantImp{
    override suspend fun getRestaurant(): Flow<List<Restaurant>> {
     try {
         val json= context.resources.
         openRawResource(context.resources.getIdentifier("restaurant","raw",context.packageName))
             .bufferedReader()
             .use {
                 it.readText()
             }

         val jsonObject=JSONObject(json)
         val restaurants=  jsonObject.getJSONArray("restaurants") as JSONArray
         var list= arrayListOf<Restaurant>()

         for(i in 0 until restaurants.length())
         {
             val rest = Gson().fromJson(restaurants.getString(i), Restaurant::class.java)
           //  val restaurant =  restaurants.get(i)  as Restaurant
             list.add(rest)
             //list.add(restaurant)
         }
         return flow {
             emit(list)
         }.flowOn(Dispatchers.Default)
     }   catch (err:Exception)
     {
         Log.e("RestaurantRepo","err"+ " "+err.message)
         return flow {
             emit(emptyList<Restaurant>())
         }.flowOn(Dispatchers.Default)
     }

    }
    override suspend fun getMenu(): Flow<List<Menu>> {
        val json= context.resources.
        openRawResource(context.resources.getIdentifier("menu","raw",context.packageName))
            .bufferedReader()
            .use {
                it.readText()
            }

        val jsonObject=JSONObject(json)
        val restaurants=  jsonObject.getJSONArray("menus") as JSONArray
        var list= arrayListOf<Menu>()
        for(i in 0 until restaurants.length())
        {
            val menu = Gson().fromJson(restaurants.getString(i), Menu::class.java)
          //  val restaurant =  restaurants.get(i)  as Menu
            list.add(menu)
        }
        return flow {
            emit(list)
        }.flowOn(Dispatchers.Default)
    }

}