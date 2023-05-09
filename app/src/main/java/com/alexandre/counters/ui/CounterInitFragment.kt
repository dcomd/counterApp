package com.alexandre.counters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.counters.R
import com.example.counters.databinding.CounterInitBinding
import com.alexandre.counters.utils.Navigate

class CounterInitFragment : Fragment(R.layout.counter_init) {
    private lateinit var binding: CounterInitBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CounterInitBinding.bind(view)
        setOnclick()
    }

    private fun setOnclick(){
        binding.btnInit.setOnClickListener {
            Navigate.navigateTo(CounterFragment(), fragmentManager!!)
        }
    }
}