package com.alexandre.counters.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counters.R
import com.example.counters.databinding.FragmentCounterBinding
import com.alexandre.counters.domain.model.Counters
import com.alexandre.counters.ui.adapter.CounterAdapter
import com.example.counters.utils.AlertConection
import com.alexandre.counters.utils.CounterCheckInternet
import com.alexandre.counters.utils.Navigate
import com.alexandre.counters.utils.ShareComponent
import com.alexandre.counters.presentation.viewModel.CounterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private lateinit var binding: FragmentCounterBinding
    private lateinit var adapter: CounterAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var currentPosition: Int = 0
    private var counterName : String =""
    private val viewModel: CounterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterBinding.bind(view)
        configureToolbar()
        viewModel.fetchList()
        initObservables()
        configureListerners()
    }


    private fun configureToolbar() {
        binding.myToolbar.inflateMenu(R.menu.menu_main)
        binding.myToolbar.setNavigationIcon(R.drawable.ic_close)
        binding.myToolbar.setNavigationOnClickListener {
            Navigate.navigateTo(CounterFragment(), fragmentManager!!)
        }
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_trash -> {
                    AlertConection.alertConfirm(requireContext(), adapter, currentPosition)
                    binding.inputSearch.visibility = View.VISIBLE
                    binding.myToolbar.visibility = View.INVISIBLE
                    true
                }
                R.id.action_share -> {
                    counterName = adapter.getItem(currentPosition)
                    if (counterName.isNotEmpty()){
                        ShareComponent.share(requireContext(),counterName)
                        binding.inputSearch.visibility = View.VISIBLE
                        binding.myToolbar.visibility = View.INVISIBLE
                    }

                      true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }


    private fun configureListerners() {
        binding.AddCounter.setOnClickListener {
            Navigate.navigateTo(AddCounterFragment(), fragmentManager!!)
        }
        binding.inputSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun initObservables() {
        viewModel.initProgress.observe(this, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.totalItens.observe(this, Observer {
            binding.totalItemCount.text = "${it} items"
        })
        viewModel.totalSubItens.observe(this, Observer {
            binding.totalTimesCount.text = "${it} times"
        })

        viewModel.fetchData.observe(this, Observer { list ->
            if (CounterCheckInternet.isNetworkAvailable(requireContext())) {
                if (list.isEmpty()) {
                    Navigate.navigateTo(WithoutCounterInternetFragment(), fragmentManager!!)
                } else {
                    adapter = CounterAdapter(list as MutableList<Counters>,viewModel)
                    adapter.onClickItem = { position ->
                        if (binding.inputSearch.visibility == View.VISIBLE) {
                            binding.inputSearch.visibility = View.GONE
                            binding.myToolbar.visibility = View.VISIBLE
                            currentPosition = position
                        } else {
                            binding.inputSearch.visibility = View.VISIBLE
                            binding.myToolbar.visibility = View.INVISIBLE
                        }
                    }
                    linearLayoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    binding.recycler.layoutManager = linearLayoutManager
                    binding.recycler.adapter = adapter
                }
            } else {
                Navigate.navigateTo(EmptyCounterFragment(), fragmentManager!!)
            }

        })
    }
}