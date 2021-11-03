package com.alexandre.counters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.counters.R
import com.example.counters.databinding.FragmentEmptyCounterBinding
import com.example.counters.utils.Navigate

class EmptyCounterFragment : Fragment(R.layout.fragment_empty_counter) {

    private lateinit var binding: FragmentEmptyCounterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEmptyCounterBinding.bind(view)
        setOnclick()
    }

    private fun setOnclick() {
        binding.idRetry.setOnClickListener {
            Navigate.navigateTo(CounterFragment(), fragmentManager!!)
        }
        binding.AddButton.setOnClickListener {
            Navigate.navigateTo(AddCounterFragment(), fragmentManager!!)
        }
    }
}