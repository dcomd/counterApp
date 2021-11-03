package com.alexandre.counters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.counters.R
import com.example.counters.databinding.FragmentWithoutCounterInternetBinding
import com.example.counters.utils.Navigate

class WithoutCounterInternetFragment : Fragment(R.layout.fragment_without_counter_internet) {

    private lateinit var binding: FragmentWithoutCounterInternetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWithoutCounterInternetBinding.bind(view)
        setOnclick()
    }

    private fun setOnclick() {
        binding.btncreate.setOnClickListener {
            Navigate.navigateTo(AddCounterFragment(), fragmentManager!!)
        }
    }
}