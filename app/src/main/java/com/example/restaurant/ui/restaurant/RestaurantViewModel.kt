package com.example.restaurant.ui.restaurant

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData

import com.example.restaurant.model.Menu
import com.example.restaurant.model.Restaurant

import com.example.restaurant.repository.ResturantImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: ResturantImp
) : ViewModel() {



    private val restaurantMap=HashMap<String,Restaurant>()
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants get() = _restaurants
    private val _filterList = MutableLiveData<List<Restaurant>>()
    val filterList get() = _filterList
    private val _menus = MutableLiveData<List<Menu>>()
    val menus get() = _menus

    private val _searchString = MediatorLiveData<String>()
    val searchString get() = _searchString


    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("RestaurantViewModel", "handler ${exception.message}")
    }



    fun getRestaurant()
    {
        viewModelScope.launch(handler) {
            repository.getRestaurant().catch { exception->
                _restaurants.value= emptyList()
            }.collectLatest { restaurants->
                _restaurants.value=restaurants
               for(restaurant in restaurants)
               {
                   restaurantMap[restaurant.id]=restaurant
               }


            }
        }
    }
    fun getMenus()
    {
        viewModelScope.launch(handler) {
            repository.getMenu().catch { exception->
                _menus.value= emptyList()
            }.collectLatest { menus->
                _menus.value=menus
            }
        }
    }
    fun getResaturantOnSearch(searchText:String?)
    {
         viewModelScope.launch(handler+Dispatchers.Default) {
               var list= arrayListOf<Restaurant>()
             for(restaurant in _restaurants.value!!.iterator())
             {

                 val cusinies=restaurant.cuisine_type.split(",")
                 cusinies.forEach {
                     if(it.toLowerCase().contains(searchText!!.toLowerCase()))
                     {
                         list.add(restaurant)
                     }
                 }
                  if(restaurant.name.toLowerCase().contains(searchText!!.toLowerCase()))
                    {
                        list.add(restaurant)
                    }

             }
             for(menu in menus.value!!.iterator())
             {

                 menu.categories.forEach { category->
                     category.menu_items.forEach { menuItem ->
                        if(searchText?.toLowerCase()!!.contains(menuItem.name.toLowerCase()))
                        {
                          if(restaurantMap.containsKey(category.id))
                          {
                              list.add(restaurantMap[category.id]!!)
                          }

                        }
                     }
                 }


             }
             _filterList.postValue(list)
         }
    }
}
