package com.alexandre.counters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counters.R
import com.example.counters.databinding.FragmentExemplosCounterBinding
import com.alexandre.counters.ui.adapter.ExemplosCounterAdpater
import com.example.counters.utils.Navigate

class ExemplesCounterFragment : Fragment(R.layout.fragment_exemplos_counter) {

    private lateinit var binding: FragmentExemplosCounterBinding
    private lateinit var adapter: ExemplosCounterAdpater
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExemplosCounterBinding.bind(view)

        binding.myToolbar.setNavigationIcon(R.drawable.ic_close)
        binding.myToolbar.setNavigationOnClickListener {
            Navigate.navigateTo(AddCounterFragment(), fragmentManager!!)
        }

        initRecyclerDrinks()
        initRecyclerFood()
        initRecyclerMisc()

    }


    private fun initRecyclerDrinks(){
        adapter = ExemplosCounterAdpater(listDrinks(), fragmentManager!!)
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerDrinks.layoutManager = linearLayoutManager
        binding.recyclerDrinks.adapter = adapter
    }

    private fun initRecyclerFood(){
        adapter = ExemplosCounterAdpater(listFood(),fragmentManager!!)
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerFood.layoutManager = linearLayoutManager
        binding.recyclerFood.adapter = adapter
    }

    private fun initRecyclerMisc(){
        adapter = ExemplosCounterAdpater(listMisc(),fragmentManager!!)
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerMisc.layoutManager = linearLayoutManager
        binding.recyclerMisc.adapter = adapter
    }

    private fun listDrinks() = listOf("Cups of coffee","Glasses of water","Juice","Tea","Milk")
    private fun listFood() = listOf("Hot-dogs","Cupcakes eaten","Chicken sandwich","Hamburguer","sandwich")
    private fun listMisc() = listOf("Times sneezed","Naps","Day dreaming")
}