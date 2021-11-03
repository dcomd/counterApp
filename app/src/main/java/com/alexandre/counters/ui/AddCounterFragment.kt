package com.alexandre.counters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.counters.R
import com.example.counters.databinding.FragmentAddCounterBinding
import com.alexandre.counters.domain.model.Counters
import com.example.counters.utils.Navigate
import com.alexandre.counters.presentation.viewModel.CounterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddCounterFragment :  Fragment(R.layout.fragment_add_counter) {

    private lateinit var binding: FragmentAddCounterBinding
    private val viewModel: CounterViewModel by viewModel()
    private var param: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString("EXAMPLE")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddCounterBinding.bind(view)
        toolbarConfiguration()
        listerners()
    }

    private fun toolbarConfiguration(){
        binding.myToolbar.inflateMenu(R.menu.menu_add)
        binding.myToolbar.setNavigationIcon(R.drawable.ic_close)
    }

    private fun listerners() {
        binding.myToolbar.setNavigationOnClickListener {
            Navigate.navigateTo(CounterFragment(), fragmentManager!!)
        }


        binding.seeExemplos.setOnClickListener {
            Navigate.navigateTo(ExemplesCounterFragment(), fragmentManager!!)
        }
        binding.textCreate.setText(param,TextView.BufferType.EDITABLE)
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    if (binding.textCreate.text.toString() != "") {
                        val counter = Counters(0, binding.textCreate.text.toString(), 0)
                        viewModel.create(counter)
                        Navigate.navigateTo(CounterFragment(), fragmentManager!!)
                    }

                    true
                }

                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }
}