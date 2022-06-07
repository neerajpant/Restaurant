package com.example.restaurant.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurant.adapter.RestaurantListAdapter
import com.example.restaurant.databinding.FragmentRestaurantBinding
import com.example.restaurant.model.Restaurant
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RestaurantViewModel by activityViewModels()
    private val restaurantAdapter = RestaurantListAdapter(arrayListOf())
    private var restaurantList= emptyList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        fetchRestaurant()
    }

    private fun fetchRestaurant() {
        viewModel.getRestaurant()
        viewModel.getMenus()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          binding.searchView.clearFocus()

        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
               return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //filterList(newText)
               return true
            }

        })
        binding.searchView.setOnCloseListener {
            binding.searchView.clearFocus()
            if(!restaurantList.isNullOrEmpty())
            {
                restaurantAdapter.apply {
                    addOption(restaurantList)
                }
            }

            return@setOnCloseListener false
        }
        binding.apply {
            recycleRestaurant.apply {
                adapter=restaurantAdapter
                layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }
        }
        viewModel.restaurants.observe(requireActivity(), Observer {
            restaurantAdapter.apply {
                restaurantList=it
                addOption(it)

            }
        })
        viewModel.filterList.observe(requireActivity(), Observer {
            restaurantAdapter.apply {
                addOption(it)
            }
        })
    }

    private fun filterList(newText: String?) {
         viewModel.getResaturantOnSearch(newText)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}