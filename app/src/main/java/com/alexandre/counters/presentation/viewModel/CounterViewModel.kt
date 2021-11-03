package com.alexandre.counters.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.alexandre.counters.domain.model.Counters
import com.alexandre.counters.domain.useCase.CreateCounter
import com.alexandre.counters.domain.useCase.DeleteCounters
import com.alexandre.counters.domain.useCase.GetCounters
import com.alexandre.counters.domain.useCase.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class CounterViewModel : ViewModel() {
    abstract val fetchData: LiveData<List<Counters>>
    abstract val initProgress: LiveData<Boolean>
    abstract val totalItens: LiveData<Int>
    abstract val totalSubItens: LiveData<Int>
    abstract fun fetchList()
    abstract fun delete(countersToBeDeletedIds: Int)
    abstract fun update(counters: Counters)
    abstract fun create(counters: Counters)
    abstract fun getSumItens(counters: List<Counters>)
}

class CounterViewModelImp(
    private val getCounters: GetCounters,
    private val update: Update,
    private val deleteCounters: DeleteCounters,
    private val createCounter: CreateCounter
) : CounterViewModel() {

    override val fetchData = MutableLiveData<List<Counters>>()
    override val initProgress = MutableLiveData<Boolean>()
    override val totalItens = MutableLiveData<Int>()
    override val totalSubItens = MutableLiveData<Int>()

    override fun fetchList() {
        initProgress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repo = getCounters.invoke("")
                fetchData.postValue(repo)
                initProgress.postValue(false)
                getSumItens(repo)

            } catch (e: Throwable) {
                initProgress.postValue(false)
            }
        }
    }


    override fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteCounters.invoke(id)
                val repo = getCounters.invoke("")
                getSumItens(repo)
            } catch (e: Throwable) {
                Log.d("Wrong at delete", e.message.toString())
            }
        }
    }

    override fun getSumItens(list: List<Counters>) {
        val total = list.sumBy { it.count }
        totalItens.postValue(list.size)
        totalSubItens.postValue(total)
    }

    override fun update(counters: Counters) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countersResponse = CountersDTO(counters.id, counters.title, counters.count)
                update.invoke(countersResponse)
                val repo = getCounters.invoke("")
                 getSumItens(repo)
            } catch (e: Throwable) {
                Log.d("Wrong at update", e.message.toString())
            }
        }
    }

    override fun create(counters: Counters) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countersResponse = CountersDTO(counters.id, counters.title, counters.count)
                createCounter.invoke(countersResponse)

            } catch (e: Throwable) {
                Log.d("Wrong at update", e.message.toString())
            }
        }
    }
}