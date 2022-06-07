package com.example.restaurant.repository

import com.example.restaurant.model.Menu
import com.example.restaurant.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface ResturantImp {

    suspend fun getRestaurant(): Flow<List<Restaurant>>
    suspend fun getMenu(): Flow<List<Menu>>
}