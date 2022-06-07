package com.example.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.FragmentTransaction
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.ui.restaurant.RestaurantFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(savedInstanceState== null)
        {
            val fragment=RestaurantFragment()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(binding?.frame!!.id, fragment, "RestaurantFragment")
            ft.addToBackStack("RestaurantFragment")
            ft.commit()
        }


    }

}